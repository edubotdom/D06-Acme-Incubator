
package acme.features.authenticated.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedForumShowService implements AbstractShowService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		Boolean result;
		int countUser;
		int forumId;

		Principal principal;
		int principalId;

		forumId = request.getModel().getInteger("id");

		principal = request.getPrincipal();
		principalId = principal.getAccountId();
		countUser = this.repository.countAuthenticatedByForumId(principalId, forumId);

		result = countUser != 0;			// si suma 1 significa que dicho forum pertenece a dicho Authenticated

		return result;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String direccion = "../message/list_by_forum?id=" + entity.getId();
		model.setAttribute("direccion", direccion);

		String direccion2 = "../message/create?id=" + entity.getId();
		model.setAttribute("forumCreateMessage", direccion2);

		model.setAttribute("forumProppetary", entity.getCreator().getUserAccount().getId() == request.getPrincipal().getAccountId());

		int idForum = entity.getId();
		String direccionAnadirUsuario = "../participant/create?forumid=" + idForum;
		model.setAttribute("direccionAnadirUsuario", direccionAnadirUsuario);

		String direccionListarUsuario = "../participant/list?forumid=" + idForum;
		model.setAttribute("direccionListarUsuario", direccionListarUsuario);

		String creator = entity.getCreator().getUserAccount().getUsername();
		String title = entity.getRound().getTitle();
		model.setAttribute("creatorName", creator);
		model.setAttribute("titleName", title);

		request.unbind(entity, model);
	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneForumById(id);

		return result;
	}
}
