
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipantListService implements AbstractListService<Authenticated, Participant> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		int idForum;
		idForum = request.getModel().getInteger("forumid");
		Forum forum = this.repository.findOneForumById(idForum);
		boolean result = forum.getCreator().getUserAccount().getId() == request.getPrincipal().getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("username", entity.getUser().getUserAccount().getUsername());
		request.unbind(entity, model, "user");
	}

	@Override
	public Collection<Participant> findMany(final Request<Participant> request) {
		assert request != null;
		Collection<Participant> result;
		int idForum;
		idForum = request.getModel().getInteger("forumid");
		result = this.repository.findManyUsersByForum(idForum);
		return result;
	}
}
