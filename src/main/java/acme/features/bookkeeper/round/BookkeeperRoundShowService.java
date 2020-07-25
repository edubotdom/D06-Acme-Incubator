
package acme.features.bookkeeper.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Bookkeeper;
import acme.entities.rounds.Round;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookkeeperRoundShowService implements AbstractShowService<Bookkeeper, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookkeeperRoundRepository repository;


	@Override
	public boolean authorise(final Request<Round> request) {
		assert request != null;

		Round round = this.repository.findOneRoundById(request.getModel().getInteger("id"));

		return round.isStatus();
	}

	@Override
	public void unbind(final Request<Round> request, final Round entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String direccion = "../accounting/list?id=" + entity.getId();
		model.setAttribute("accountingList", direccion);

		String direccion2 = "../activity/list_by_round?id=" + entity.getId();
		model.setAttribute("roundListActivities", direccion2);

		String direccion3 = "../accounting/create?id=" + entity.getId();
		model.setAttribute("createAccounting", direccion3);

		request.unbind(entity, model, "ticker", "creation", "kind", "title", "description", "money", "information", "entrepreneur");
	}

	@Override
	public Round findOne(final Request<Round> request) {
		assert request != null;

		Round result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneRoundById(id);

		return result;
	}
}
