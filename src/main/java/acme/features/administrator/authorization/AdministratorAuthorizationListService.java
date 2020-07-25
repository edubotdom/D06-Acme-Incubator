
package acme.features.administrator.authorization;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Authorization;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAuthorizationListService implements AbstractListService<Administrator, Authorization> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorAuthorizationRepository repository;


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
	public Collection<Authorization> findMany(final Request<Authorization> request) {
		assert request != null;

		Collection<Authorization> result;

		result = this.repository.findManyAll();
		return result;
	}
}
