
package acme.features.investor.round;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.roles.Investor;
import acme.entities.rounds.Round;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorRoundShowService implements AbstractShowService<Investor, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InvestorRoundRepository repository;


	@Override
	public boolean authorise(final Request<Round> request) {
		assert request != null;

		boolean result;
		int roundId;
		Collection<Activity> activities;
		Date date = new Date();
		roundId = request.getModel().getInteger("id");
		activities = this.repository.findManyActivitiesByRound(roundId);
		result = activities.stream().map(m -> m.getEnd()).anyMatch(f -> f.compareTo(date) > 0);

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

		String createApplication = "../application/create?id=" + request.getModel().getInteger("id");
		model.setAttribute("createApplication", createApplication);

		request.unbind(entity, model, "ticker", "creation", "kind", "title", "description", "money", "information", "entrepreneur");
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
