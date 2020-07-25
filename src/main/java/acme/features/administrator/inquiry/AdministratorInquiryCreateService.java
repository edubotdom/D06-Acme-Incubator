
package acme.features.administrator.inquiry;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquiryCreateService implements AbstractCreateService<Administrator, Inquiry> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);

		request.bind(entity, errors, "creation");

	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "money", "contact");

	}

	@Override
	public Inquiry instantiate(final Request<Inquiry> request) {
		Inquiry result;
		result = new Inquiry();

		return result;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.getDeadline() == null) {
			errors.state(request, false, "deadline", "administrator.inquiry.null-deadline");
		} else {
			boolean isDeadlineDateDuture = entity.getDeadline().after(new GregorianCalendar().getTime());
			errors.state(request, isDeadlineDateDuture, "deadline", "administrator.inquiry.incorrect-deadline");
		}

		if (entity.getMoney() == null) {
			errors.state(request, false, "money", "administrator.inquiry.null-money");
		} else {
			boolean isCurrencyCorrect = entity.getMoney().getCurrency().equals("EUR") || entity.getMoney().getCurrency().equals("€");
			errors.state(request, isCurrencyCorrect, "money", "administrator.inquiry.incorrect-currency");
		}

	}

	@Override
	public void create(final Request<Inquiry> request, final Inquiry entity) {

		this.repository.save(entity);

	}

}
