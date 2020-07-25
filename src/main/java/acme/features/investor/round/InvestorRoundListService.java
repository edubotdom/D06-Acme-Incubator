
package acme.features.investor.round;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Investor;
import acme.entities.rounds.Round;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class InvestorRoundListService implements AbstractListService<Investor, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	InvestorRoundRepository repository;


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
		result = this.repository.findManyActive();

		return result;
	}
}
