
package acme.features.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {

	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Entrepreneur entrepreneur;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(applicationId);
		entrepreneur = application.getRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result && application.getStatus().equals("pending");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String roundTicker = entity.getRound().getTicker();
		model.setAttribute("roundTicker", roundTicker);
		String roundCreator = entity.getRound().getEntrepreneur().getUserAccount().getUsername();
		model.setAttribute("roundCreator", roundCreator);
		String investor = entity.getInvestor().getUserAccount().getUsername();
		model.setAttribute("investorName", investor);

		int applicationId = request.getModel().getInteger("id");
		Application application = this.repository.findOneApplicationById(applicationId);
		boolean isPending = application.getStatus().equals("pending");
		model.setAttribute("isPending", isPending);

		request.unbind(entity, model, "ticker", "creation", "statement", "offer", "status", "justification");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		if (!entity.getStatus().isEmpty()) {
			boolean justificationRestrictionPassed = true;
			if (entity.getStatus().equals("rejected") && entity.getJustification().isEmpty()) {
				justificationRestrictionPassed = false;
			}
			errors.state(request, justificationRestrictionPassed, "justification", "investor.application.provideJustification");

		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
