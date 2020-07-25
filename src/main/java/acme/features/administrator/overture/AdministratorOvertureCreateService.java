
package acme.features.administrator.overture;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorOvertureCreateService implements AbstractCreateService<Administrator, Overture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);

		request.bind(entity, errors, "creation");

	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "money", "contact");

	}

	@Override
	public Overture instantiate(final Request<Overture> request) {
		Overture result;
		result = new Overture();

		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.getDeadline() == null) {
			errors.state(request, false, "deadline", "administrator.overture.null-deadline");
		} else {
			boolean isDeadlineDateDuture = entity.getDeadline().after(new GregorianCalendar().getTime());
			errors.state(request, isDeadlineDateDuture, "deadline", "administrator.overture.incorrect-deadline");
		}

		if (entity.getMoney() == null) {
			errors.state(request, false, "money", "administrator.overture.null-money");
		} else {
			boolean isCurrencyCorrect = entity.getMoney().getCurrency().equals("EUR") || entity.getMoney().getCurrency().equals("€");
			errors.state(request, isCurrencyCorrect, "money", "administrator.overture.incorrect-currency");
		}

	}

	@Override
	public void create(final Request<Overture> request, final Overture entity) {

		this.repository.save(entity);

	}

}
