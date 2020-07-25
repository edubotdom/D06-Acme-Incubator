
package acme.features.investor.activity;

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
public class InvestorActivityShowService implements AbstractShowService<Investor, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InvestorActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean result;
		Activity activity;
		Round round;
		Date date = new Date();
		int activityId = request.getModel().getInteger("id");
		activity = this.repository.findOneById(activityId);
		round = activity.getRound();
		result = this.repository.findManyByRound(round.getId()).stream().map(m -> m.getEnd()).anyMatch(f -> f.compareTo(date) > 0);

		return result;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "start", "end", "budget");
	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
