
package acme.features.patron.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.cards.Card;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class PatronCardShowService implements AbstractShowService<Patron, Card> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private PatronCardRepository repository;


	// AbstractShowService<Administrator, Announcement>interface --------------
	@Override
	public boolean authorise(final Request<Card> request) {
		assert request != null;

		int idCard = request.getModel().getInteger("id");
		//Si Patron quiere mostrar una tarjeta de un Banner
		List<Banner> banners = new ArrayList<>(this.repository.findBannersByCard(idCard));
		boolean bannerCard = banners.stream().map(b -> b.getPatron().getUserAccount().getId()).anyMatch(i -> i.equals(request.getPrincipal().getAccountId()));
		//Si Patron quiere mostrar una tarjeta suya
		List<Patron> patrons = new ArrayList<>(this.repository.findPatronsByCard(idCard));
		boolean patronCard = patrons.stream().map(b -> b.getUserAccount().getId()).anyMatch(i -> i.equals(request.getPrincipal().getAccountId()));

		return bannerCard || patronCard;
	}

	@Override
	public void unbind(final Request<Card> request, final Card entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holder", "number", "brand", "cvv");
	}

	@Override
	public Card findOne(final Request<Card> request) {
		assert request != null;

		Card result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
