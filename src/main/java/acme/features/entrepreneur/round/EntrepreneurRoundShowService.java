
package acme.features.entrepreneur.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Entrepreneur;
import acme.entities.rounds.Round;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurRoundShowService implements AbstractShowService<Entrepreneur, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurRoundRepository repository;


	@Override
	public boolean authorise(final Request<Round> request) {
		assert request != null;

		boolean result;
		int roundId;
		Round round;
		Entrepreneur entrepreneur;
		Principal principal;

		roundId = request.getModel().getInteger("id");
		round = this.repository.findOneRoundById(roundId);
		entrepreneur = round.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
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

		boolean roundApplied = this.repository.findManyApplicationsByRound(request.getModel().getInteger("id")).size() == 0;
		model.setAttribute("roundApplied", roundApplied);

		model.setAttribute("isFinalMode", entity.isStatus());

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
}
