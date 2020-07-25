
package acme.features.bookkeeper.accounting;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountings.Accounting;
import acme.entities.roles.Bookkeeper;
import acme.entities.rounds.Round;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingCreateService implements AbstractCreateService<Bookkeeper, Accounting> {

	@Autowired
	BookkeeperAccountingRepository repository;


	@Override
	public boolean authorise(final Request<Accounting> request) {
		assert request != null;

		Round round = this.repository.findOneRoundById(request.getModel().getInteger("id"));

		return round.isStatus();
	}

	@Override
	public void bind(final Request<Accounting> request, final Accounting entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Accounting> request, final Accounting entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String createAccounting = "../accounting/create?id=" + request.getModel().getInteger("id");
		model.setAttribute("createAccounting", createAccounting);

		request.unbind(entity, model, "title", "creation", "status", "body", "bookkeeper", "round");
	}

	@Override
	public Accounting instantiate(final Request<Accounting> request) {
		assert request != null;

		Accounting result;

		int bookkeeperId = request.getPrincipal().getAccountId();
		Bookkeeper bookkeeper = this.repository.findOneBookkeeperByUserAccountId(bookkeeperId);
		Round round = this.repository.findOneRoundById(request.getModel().getInteger("id"));
		Date date = new Date(System.currentTimeMillis() - 1);

		result = new Accounting();
		result.setCreation(date);
		result.setBookkeeper(bookkeeper);
		result.setRound(round);
		return result;
	}

	@Override
	public void validate(final Request<Accounting> request, final Accounting entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Accounting> request, final Accounting entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
