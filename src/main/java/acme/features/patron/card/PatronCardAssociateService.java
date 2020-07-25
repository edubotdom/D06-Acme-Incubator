
package acme.features.patron.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cards.Card;
import acme.entities.roles.Patron;
import acme.features.authenticated.patron.AuthenticatedPatronRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronCardAssociateService implements AbstractCreateService<Patron, Card> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronCardRepository			repository;

	@Autowired
	AuthenticatedPatronRepository	patronRepository;


	@Override
	public boolean authorise(final Request<Card> request) {
		assert request != null;
		Integer patronId = request.getModel().getInteger("id");
		if (patronId == null || patronId.equals(0)) {
			patronId = request.getModel().getInteger("id_patron");
		}
		Patron patron = this.patronRepository.findOnePatronById(patronId);

		boolean emptyCard = patron.getCard() == null;

		return emptyCard;
	}

	@Override
	public void bind(final Request<Card> request, final Card entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Card> request, final Card entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer patronId = request.getModel().getInteger("id");
		model.setAttribute("id_patron", patronId);

		request.unbind(entity, model, "holder", "number", "brand", "cvv");
	}

	@Override
	public Card instantiate(final Request<Card> request) {
		Card result;
		result = new Card();

		return result;
	}

	@Override
	public void validate(final Request<Card> request, final Card entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Card> request, final Card entity) {

		Integer patronId = request.getModel().getInteger("id_patron").intValue();
		Patron patron = this.patronRepository.findOnePatronById(patronId);
		patron.setCard(entity);

		this.repository.save(entity);
		this.patronRepository.save(patron);

	}

}
