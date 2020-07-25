
package acme.features.patron.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.cards.Card;
import acme.entities.roles.Patron;
import acme.features.patron.banner.PatronBannerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronCardCreateService implements AbstractCreateService<Patron, Card> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronCardRepository	repository;

	@Autowired
	PatronBannerRepository	bannerRepository;


	@Override
	public boolean authorise(final Request<Card> request) {
		assert request != null;
		Integer bannerId = request.getModel().getInteger("id");
		if (bannerId == null || bannerId.equals(0)) {
			bannerId = request.getModel().getInteger("id_banner");
		}
		Banner banner = this.bannerRepository.findOneById(bannerId);

		return banner.getPatron().getUserAccount().getId() == request.getPrincipal().getAccountId();
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

		Integer id_banner = request.getModel().getInteger("id");
		model.setAttribute("id_banner", id_banner);

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

		Integer id_banner = request.getModel().getInteger("id_banner").intValue();
		Banner banner = this.repository.findOneBannerById(id_banner);
		errors.state(request, banner.getCard() == null, "holder", "administrator.card.bannerHasPreviousCard");

	}

	@Override
	public void create(final Request<Card> request, final Card entity) {

		Integer id_banner = request.getModel().getInteger("id_banner").intValue();
		Banner banner = this.repository.findOneBannerById(id_banner);
		banner.setCard(entity);

		this.repository.save(entity);
		this.bannerRepository.save(banner);

	}

}
