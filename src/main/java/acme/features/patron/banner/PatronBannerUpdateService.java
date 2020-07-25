
package acme.features.patron.banner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.customization.Customization;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class PatronBannerUpdateService implements AbstractUpdateService<Patron, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		Integer bannerId = request.getModel().getInteger("id");
		Banner banner = this.repository.findOneById(bannerId);

		return banner.getPatron().getUserAccount().getId() == request.getPrincipal().getAccountId();
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

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

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!entity.getSlogan().isEmpty()) {
			//Spam
			String[] sloganSplitted = entity.getSlogan().split(" ");

			Customization customisation = this.repository.findCustomization();

			String[] spamWords = customisation.getSpam().split(",");
			Double spamThreshold = customisation.getThreshold();

			List<String> spamWordsInList = IntStream.range(0, spamWords.length).boxed().map(x -> spamWords[x].trim()).collect(Collectors.toList());

			Integer sloganNumSpam = (int) IntStream.range(0, sloganSplitted.length).boxed().map(x -> sloganSplitted[x].trim()).filter(i -> spamWordsInList.contains(i)).count();

			boolean isSloganFreeOfSpam = true;

			if (sloganNumSpam != 0) {
				isSloganFreeOfSpam = 100 * sloganNumSpam / sloganSplitted.length < spamThreshold;
			}

			errors.state(request, isSloganFreeOfSpam, "slogan", "patron.banner.spamWords");
		}

	}

	@Override
	public void update(final Request<Banner> request, final Banner entity) {

		this.repository.save(entity);

	}

}
