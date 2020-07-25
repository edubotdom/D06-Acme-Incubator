
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.participants.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipantCreateService implements AbstractCreateService<Authenticated, Participant> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;
		boolean result;
		boolean result2;
		int countUser;
		int forumId;

		Principal principal;
		int principalId;

		forumId = request.getModel().getInteger("forumid");

		principal = request.getPrincipal();
		principalId = principal.getAccountId();
		countUser = this.repository.countAuthenticatedByForumId(principalId, forumId);

		result = countUser != 0;			// si suma 1 significa que dicho forum pertenece a dicho Authenticated

		int idForum = request.getModel().getInteger("forumid");
		Forum forum = this.repository.findOneForumById(idForum);
		result2 = forum.getCreator().getUserAccount().getId() == request.getPrincipal().getAccountId();

		return result && result2;
	}

	@Override
	public void bind(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated user;

		int username = request.getModel().getInteger("usuarioelegido");
		user = this.repository.findOneAutheticatedById(username);
		entity.setUser(user);

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int idForum = request.getModel().getInteger("forumid");

		String direccionParticipant = "../participant/create?forumid=" + idForum;
		model.setAttribute("direccionParticipant", direccionParticipant);

		Collection<Authenticated> usuarios = this.repository.findManyAuthenticatedNotInForum(idForum);
		//Aqui hay que hacer la interseccion del conjunto de todos los usuarios con el conjunto de usuarios del hilo

		model.setAttribute("usuarios", usuarios);
		model.setAttribute("usuarioelegido", "");
		request.unbind(entity, model);

	}

	@Override
	public Participant instantiate(final Request<Participant> request) {
		Participant result;
		result = new Participant();
		int idForum = request.getModel().getInteger("forumid");
		Forum t = this.repository.findOneForumById(idForum);
		result.setForum(t);

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Participant> request, final Participant entity) {

		this.repository.save(entity);

	}

}
