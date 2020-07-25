
package acme.features.entrepreneur.round;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountings.Accounting;
import acme.entities.activities.Activity;
import acme.entities.roles.Entrepreneur;
import acme.entities.rounds.Round;
import acme.features.entrepreneur.accounting.EntrepreneurAccountingRepository;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurRoundDeleteService implements AbstractDeleteService<Entrepreneur, Round> {

	@Autowired
	EntrepreneurRoundRepository			repository;
	@Autowired
	EntrepreneurActivityRepository		activityRepository;
	@Autowired
	EntrepreneurAccountingRepository	accountingRepository;
	/*
	 * @Autowired
	 * AuthenticatedForumRepository forumRepository;
	 */


	@Override
	public boolean authorise(final Request<Round> request) {
		assert request != null;

		Round r = this.repository.findOneRoundById(request.getModel().getInteger("id"));

		Entrepreneur entrepreneur = r.getEntrepreneur();
		Principal principal = request.getPrincipal();
		boolean iAmPrincipal = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return this.repository.findManyApplicationsByRound(request.getModel().getInteger("id")).size() == 0 && iAmPrincipal;
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

		boolean isRoundNotApplicated = this.repository.findManyApplicationsByRound(entity.getId()).size() == 0;
		errors.state(request, isRoundNotApplicated, "ticker", "entrepreneur.round.roundWithApplicationsCantBeDeleted");

	}

	@Override
	public void delete(final Request<Round> request, final Round entity) {
		assert request != null;
		assert entity != null;

		List<Activity> activities = new ArrayList<>(this.repository.findManyActivitiesByRound(entity.getId()));

		for (Activity a : activities) {
			this.activityRepository.delete(a);
		}

		List<Accounting> accountings = new ArrayList<>(this.repository.findManyAccountingsByRound(entity.getId()));

		for (Accounting a : accountings) {
			this.accountingRepository.delete(a);
		}
		/*
		 * List<Forum> forums = new ArrayList<>(this.repository.findManyForumByRound(entity.getId()));
		 * for (Forum a : forums) {
		 * this.forumRepository.delete(a);
		 * }
		 */
		this.repository.delete(entity);
	}

}
