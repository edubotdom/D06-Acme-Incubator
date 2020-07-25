
package acme.features.patron.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.cards.Card;
import acme.entities.roles.Patron;
import acme.features.authenticated.patron.AuthenticatedPatronRepository;
import acme.features.patron.banner.PatronBannerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class PatronCardDeleteService implements AbstractDeleteService<Patron, Card> {

	@Autowired
	PatronCardRepository			repository;
	@Autowired
	PatronBannerRepository			bannerRepository;
	@Autowired
	AuthenticatedPatronRepository	patronRepository;


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

	@Override
	public void validate(final Request<Card> request, final Card entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Card> request, final Card entity) {
		assert request != null;
		assert entity != null;

		List<Banner> banners = new ArrayList<>(this.repository.findBannersByCard(entity.getId()));
		List<Patron> patrons = new ArrayList<>(this.repository.findPatronsByCard(entity.getId()));

		for (Banner b : banners) {
			b.setCard(null);
			this.bannerRepository.save(b);
		}

		for (Patron p : patrons) {
			p.setCard(null);
			this.patronRepository.save(p);
		}

		this.repository.delete(entity);
	}

}
