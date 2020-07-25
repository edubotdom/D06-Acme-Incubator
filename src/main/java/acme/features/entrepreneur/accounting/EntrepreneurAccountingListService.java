
package acme.features.entrepreneur.accounting;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountings.Accounting;
import acme.entities.roles.Entrepreneur;
import acme.entities.rounds.Round;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurAccountingListService implements AbstractListService<Entrepreneur, Accounting> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurAccountingRepository repository;


	@Override
	public boolean authorise(final Request<Accounting> request) {
		assert request != null;

		boolean result;
		int roundId;
		Round round;
		Entrepreneur entrepreneur;
		Principal principal;

		roundId = request.getModel().getInteger("id");
		round = this.repository.findOneRoundById(roundId);
		entrepreneur = round.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Accounting> request, final Accounting entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation");
	}

	@Override
	public Collection<Accounting> findMany(final Request<Accounting> request) {
		assert request != null;

		Collection<Accounting> result;
		result = this.repository.findManyAccountsByRound(request.getModel().getInteger("id"));

		return result;
	}
}
