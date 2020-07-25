
package acme.features.entrepreneur.round;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.roles.Entrepreneur;
import acme.entities.rounds.Round;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurRoundUpdateService implements AbstractUpdateService<Entrepreneur, Round> {

	@Autowired
	EntrepreneurRoundRepository repository;


	@Override
	public boolean authorise(final Request<Round> request) {
		assert request != null;

		Integer id = request.getModel().getInteger("id");
		Round r = this.repository.findOneRoundById(id);

		Entrepreneur entrepreneur = r.getEntrepreneur();
		Principal principal = request.getPrincipal();
		boolean iAmPrincipal = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return !r.isStatus() && iAmPrincipal;
	}

	@Override
	public void bind(final Request<Round> request, final Round entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Round> request, final Round entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String direccion = "../accounting/list?id=" + entity.getId();
		model.setAttribute("accountingList", direccion);

		String direccion2 = "../activity/list_by_round?id=" + entity.getId();
		model.setAttribute("roundListActivities", direccion2);

		String direccion3 = "../activity/create?id=" + entity.getId();
		model.setAttribute("roundCreateActivity", direccion3);

		boolean iAmPrincipal;
		int roundId;
		Round round;
		Entrepreneur entrepreneur;
		Principal principal;

		roundId = entity.getId();
		round = this.repository.findOneRoundById(roundId);
		entrepreneur = round.getEntrepreneur();
		principal = request.getPrincipal();
		iAmPrincipal = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		model.setAttribute("iAmPrincipal", iAmPrincipal);

		Double budgetSum = this.repository.findManyActivitiesByRound(roundId).stream().map(b -> b.getBudget()).mapToDouble(m -> m.getAmount()).sum();
		boolean budgetFullfilled = budgetSum >= entity.getMoney().getAmount();
		model.setAttribute("budgetFulfilled", budgetFullfilled);

		request.unbind(entity, model, "ticker", "creation", "kind", "title", "description", "money", "information", "status", "entrepreneur");
	}

	@Override
	public Round findOne(final Request<Round> request) {
		assert request != null;

		Round result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneRoundById(id);

		return result;
	}

	@Override
	public void validate(final Request<Round> request, final Round entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer year = Calendar.getInstance().get(Calendar.YEAR);

		if (!entity.getKind().isEmpty()) {
			errors.state(request, entity.getKind().equals("SEED") || entity.getKind().equals("ANGEL") || entity.getKind().equals("SERIES-A") || entity.getKind().equals("SERIES-B") || entity.getKind().equals("SERIES-C") || entity.getKind().equals("BRIDGE"),
				"kind", "entrepreneur.round.incorrectKind");
		}
		if (!entity.getTicker().isEmpty() && entity.getTicker().trim().split("-").length > 1) {
			String shortYear = year.toString().substring(2);
			String shortTickerYear = entity.getTicker().trim().split("-")[1];
			errors.state(request, shortTickerYear.equals(shortYear), "ticker", "entrepreneur.round.incorrectYearOfTicker");
		}
		if (entity.getMoney() != null) {
			boolean isCurrencyCorrect = entity.getMoney().getCurrency().equals("EUR") || entity.getMoney().getCurrency().equals("€");
			errors.state(request, isCurrencyCorrect, "money", "entrepreneur.round.incorrect-currency");
		}
		if (entity.getMoney() != null) {
			Double activitiesBudgetSum = this.repository.findManyActivitiesByRound(entity.getId()).stream().mapToDouble(a -> a.getBudget().getAmount()).sum();
			errors.state(request, !entity.isStatus() || activitiesBudgetSum.equals(entity.getMoney().getAmount()) && entity.isStatus(), "status", "entrepreneur.round.activitiesBudgetNotMatchRoundMoney");
		}

		if (!entity.getTicker().isEmpty() && !entity.getTitle().isEmpty() && !entity.getDescription().isEmpty() && !entity.getInformation().isEmpty()) {
			//Spam
			String[] tickerSplitted = entity.getTicker().split(" ");
			String[] titleSplitted = entity.getTitle().split(" ");
			String[] descriptionSplitted = entity.getDescription().split(" ");
			String[] informationSplitted = entity.getInformation().split(" ");

			Customization customisation = this.repository.findCustomization();

			String[] spamWords = customisation.getSpam().split(",");
			Double spamThreshold = customisation.getThreshold();

			List<String> spamWordsInList = IntStream.range(0, spamWords.length).boxed().map(x -> spamWords[x].trim()).collect(Collectors.toList());

			Integer tickerNumSpam = (int) IntStream.range(0, tickerSplitted.length).boxed().map(x -> tickerSplitted[x].trim()).filter(i -> spamWordsInList.contains(i)).count();
			Integer titleNumSpam = (int) IntStream.range(0, titleSplitted.length).boxed().map(x -> titleSplitted[x].trim()).filter(i -> spamWordsInList.contains(i)).count();
			Integer descriptionNumSpam = (int) IntStream.range(0, descriptionSplitted.length).boxed().map(x -> descriptionSplitted[x].trim()).filter(i -> spamWordsInList.contains(i)).count();
			Integer informationNumSpam = (int) IntStream.range(0, informationSplitted.length).boxed().map(x -> informationSplitted[x].trim()).filter(i -> spamWordsInList.contains(i)).count();

			boolean isTickerFreeOfSpam = true;
			boolean isTitleFreeOfSpam = true;
			boolean isDescriptionFreeOfSpam = true;
			boolean isInformationFreeOfSpam = true;

			if (tickerNumSpam != 0) {
				isTickerFreeOfSpam = 100 * tickerNumSpam / tickerSplitted.length < spamThreshold;
			}
			if (titleNumSpam != 0) {
				isTitleFreeOfSpam = 100 * titleNumSpam / titleSplitted.length < spamThreshold;
			}
			if (descriptionNumSpam != 0) {
				isDescriptionFreeOfSpam = 100 * descriptionNumSpam / descriptionSplitted.length < spamThreshold;
			}
			if (informationNumSpam != 0) {
				isInformationFreeOfSpam = 100 * informationNumSpam / informationSplitted.length < spamThreshold;
			}
			errors.state(request, !entity.isStatus() || isTickerFreeOfSpam && entity.isStatus(), "ticker", "entrepreneur.round.spamInText");
			errors.state(request, !entity.isStatus() || isTitleFreeOfSpam && entity.isStatus(), "title", "entrepreneur.round.spamInText");
			errors.state(request, !entity.isStatus() || isDescriptionFreeOfSpam && entity.isStatus(), "description", "entrepreneur.round.spamInText");
			errors.state(request, !entity.isStatus() || isInformationFreeOfSpam && entity.isStatus(), "information", "entrepreneur.round.spamInText");
		}
	}

	@Override
	public void update(final Request<Round> request, final Round entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
