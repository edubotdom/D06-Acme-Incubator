
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class PatronBannerShowService implements AbstractShowService<Patron, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private PatronBannerRepository repository;


	// AbstractShowService<Administrator, Announcement>interface --------------
	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		Integer bannerId = request.getModel().getInteger("id");
		Banner banner = this.repository.findOneById(bannerId);

		return banner.getPatron().getUserAccount().getId() == request.getPrincipal().getAccountId();
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		boolean isCardEmpty = entity.getCard() == null;
		model.setAttribute("isCardEmpty", isCardEmpty);

		if (isCardEmpty) {
			String createCC = "../card/create?id=" + entity.getId();
			model.setAttribute("linkCreateCC", createCC);
		}

		if (!isCardEmpty) {
			Integer idCard = entity.getCard().getId();
			String showCC = "../card/show?id=" + idCard;
			model.setAttribute("linkShowCC", showCC);
		}

		request.unbind(entity, model, "picture", "slogan", "url");
	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
