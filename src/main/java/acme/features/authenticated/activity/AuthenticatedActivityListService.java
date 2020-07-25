
package acme.features.authenticated.activity;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.rounds.Round;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedActivityListService implements AbstractListService<Authenticated, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean result;
		int roundId;
		Collection<Activity> activities;
		Date date = new Date();
		roundId = request.getModel().getInteger("id");
		activities = this.repository.findManyByRound(roundId);
		result = activities.stream().map(m -> m.getEnd()).anyMatch(f -> f.compareTo(date) > 0);

		return result;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "budget");
	}

	@Override
	public Collection<Activity> findMany(final Request<Activity> request) {
		assert request != null;

		Integer id = request.getModel().getInteger("id");
		Round round = this.repository.findRoundById(id);
		return this.repository.findManyByRound(round.getId());
	}
}
