
package acme.features.entrepreneur.round;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Entrepreneur;
import acme.entities.rounds.Round;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurRoundCreateService implements AbstractCreateService<Entrepreneur, Round> {

	@Autowired
	EntrepreneurRoundRepository repository;
	/*
	 * @Autowired
	 * AuthenticatedForumRepository forumRepository;
	 */


	@Override
	public boolean authorise(final Request<Round> request) {
		assert request != null;
		return true;
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

		request.unbind(entity, model, "ticker", "creation", "kind", "title", "description", "money", "information", "status", "entrepreneur");
	}

	@Override
	public Round instantiate(final Request<Round> request) {
		assert request != null;

		Round result;

		int entrepreneurId = request.getPrincipal().getAccountId();
		Entrepreneur entrepreneur = this.repository.findOneEntrepreneurByUserAccountId(entrepreneurId);

		Date date = new Date(System.currentTimeMillis() - 1);

		result = new Round();
		result.setEntrepreneur(entrepreneur);
		result.setCreation(date);
		result.setStatus(false);
		return result;
	}

	@Override
	public void validate(final Request<Round> request, final Round entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer year = Calendar.getInstance().get(Calendar.YEAR);

		if (!entity.getTicker().isEmpty()) {
			Round sameTicker = this.repository.findOneRoundByTicker(entity.getTicker());
			errors.state(request, sameTicker == null, "ticker", "entrepreneur.round.repeatedTicker");
		}

		if (!entity.getKind().isEmpty()) {
			errors.state(request, entity.getKind().equals("SEED") || entity.getKind().equals("ANGEL") || entity.getKind().equals("SERIES-A") || entity.getKind().equals("SERIES-B") || entity.getKind().equals("SERIES-C") || entity.getKind().equals("BRIDGE"),
				"kind", "entrepreneur.round.incorrectKind");
		}
		if (!entity.getTicker().isEmpty() && entity.getTicker().trim().split("-").length > 1) {
			String shortYear = year.toString().substring(2);
			String shortTickerYear = entity.getTicker().trim().split("-")[1];
			errors.state(request, shortTickerYear.equals(shortYear), "ticker", "entrepreneur.round.incorrectYearOfTicker");

			boolean isTickerSectorCorrect = false;
			if (entity.getTicker().trim().split("-")[0].length() >= 3) {
				String tickerSector = entity.getTicker().trim().split("-")[0].substring(0, 3);
				String[] sectorWords = this.repository.findCustomization().getSectors().trim().split(",");
				List<String> sectors = IntStream.range(0, sectorWords.length).boxed().map(x -> sectorWords[x].trim()).map(s -> s.substring(0, 3).toUpperCase()).collect(Collectors.toList());
				isTickerSectorCorrect = sectors.contains(tickerSector);
			}
			errors.state(request, isTickerSectorCorrect, "ticker", "entrepreneur.round.incorrectSector");

		}
		if (entity.getMoney() != null) {
			boolean isCurrencyCorrect = entity.getMoney().getCurrency().equals("EUR") || entity.getMoney().getCurrency().equals("€");
			errors.state(request, isCurrencyCorrect, "money", "entrepreneur.round.incorrect-currency");
		}
	}

	@Override
	public void create(final Request<Round> request, final Round entity) {
		assert request != null;
		assert entity != null;

		Round round = this.repository.save(entity);
		/*
		 * Forum forum = new Forum();
		 * forum.setRound(round);
		 * this.forumRepository.save(forum);
		 */
	}

}
