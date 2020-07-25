
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "expertGoal", "expertReward", "averageGoal", "averageReward", "rookieGoal", "rookieReward");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;
		result = new Challenge();
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// BUSCA QUE LA FECHA SEA FUTURA
		Calendar calendar;
		Date minimumDeadline;

		if (entity.getDeadline() != null && !errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.challenges.incorrect-date");
		} else if (entity.getDeadline() == null) {
			errors.state(request, false, "deadline", "administrator.challenge.must-be-filled");
		}

		// CHECK IF REWARDS ARE IN EURO CURRENCY
		if (entity.getExpertReward() == null) {
			errors.state(request, false, "expertReward", "administrator.challenge.null-money");
		} else {
			boolean isCurrencyCorrect = entity.getExpertReward().getCurrency().equals("EUR") || entity.getExpertReward().getCurrency().equals("€");
			errors.state(request, isCurrencyCorrect, "expertReward", "administrator.overture.incorrect-currency");
		}

		if (entity.getAverageReward() == null) {
			errors.state(request, false, "averageReward", "administrator.challenge.null-money");
		} else {
			boolean isCurrencyCorrect = entity.getAverageReward().getCurrency().equals("EUR") || entity.getAverageReward().getCurrency().equals("€");
			errors.state(request, isCurrencyCorrect, "averageReward", "administrator.challenge.incorrect-currency");
		}

		if (entity.getRookieReward() == null) {
			errors.state(request, false, "rookieReward", "administrator.challenge.null-money");
		} else {
			boolean isCurrencyCorrect = entity.getRookieReward().getCurrency().equals("EUR") || entity.getRookieReward().getCurrency().equals("€");
			errors.state(request, isCurrencyCorrect, "rookieReward", "administrator.challenge.incorrect-currency");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		this.repository.save(entity);
	}

}
