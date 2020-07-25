
package acme.features.authenticated.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBookkeeperShowService implements AbstractShowService<Authenticated, Bookkeeper> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBookkeeperRepository repository;


	@Override
	public boolean authorise(final Request<Bookkeeper> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Bookkeeper> request, final Bookkeeper entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibility");
	}

	@Override
	public Bookkeeper findOne(final Request<Bookkeeper> request) {
		assert request != null;

		Bookkeeper result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneBookkeeperByUserAccountId(id);

		return result;
	}
}
