
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedParticipantDeleteService implements AbstractDeleteService<Authenticated, Participant> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		Integer idParticipant = request.getModel().getInteger("id");
		Participant participant = this.repository.findOneById(idParticipant);
		boolean result = participant.getForum().getCreator().getUserAccount().getId() == request.getPrincipal().getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		Authenticated authenticated;
		Principal principal;
		int principalId;
		principal = request.getPrincipal();
		principalId = principal.getAccountId();
		authenticated = entity.getUser();
		boolean notMe = authenticated.getUserAccount().getId() != principalId;

		model.setAttribute("notMe", notMe);

		model.setAttribute("username", entity.getUser().getUserAccount().getUsername());
		request.unbind(entity, model);

	}

	@Override
	public Participant findOne(final Request<Participant> request) {
		assert request != null;

		Participant result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Participant> request, final Participant entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
