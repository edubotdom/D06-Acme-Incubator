
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedForumListService implements AbstractListService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String creator = entity.getCreator().getUserAccount().getUsername();
		String title = entity.getRound().getTitle();
		model.setAttribute("creatorName", creator);
		model.setAttribute("titleName", title);

		request.unbind(entity, model, "creator", "round");
	}

	@Override
	public Collection<Forum> findMany(final Request<Forum> request) {
		assert request != null;

		Collection<Forum> result;

		Principal principal = request.getPrincipal();

		result = this.repository.findManyForumsInParticipantByUserId(principal.getActiveRoleId());

		return result;
	}
}
