
package acme.features.authenticated.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Authorization;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuthorizationShowService implements AbstractShowService<Authenticated, Authorization> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuthorizationRepository repository;


	@Override
	public boolean authorise(final Request<Authorization> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Authorization> request, final Authorization entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.isAccepted()) {
			model.setAttribute("status", "Accepted");
		} else {
			model.setAttribute("status", "Pending");
		}

		request.unbind(entity, model, "body");
	}

	@Override
	public Authorization findOne(final Request<Authorization> request) {
		assert request != null;

		Authorization result;
		int id;

		id = request.getPrincipal().getAccountId();
		result = this.repository.findOneAuthorizationByUserAccountId(id);

		return result;
	}
}
