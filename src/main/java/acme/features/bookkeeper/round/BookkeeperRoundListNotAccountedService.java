
package acme.features.bookkeeper.round;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Bookkeeper;
import acme.entities.rounds.Round;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookkeeperRoundListNotAccountedService implements AbstractListService<Bookkeeper, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookkeeperRoundRepository repository;


	@Override
	public boolean authorise(final Request<Round> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Round> request, final Round entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creation");
	}

	@Override
	public Collection<Round> findMany(final Request<Round> request) {
		assert request != null;

		Collection<Round> result;
		result = this.repository.findManyNotAccounted(request.getPrincipal().getAccountId());

		return result;
	}
}
