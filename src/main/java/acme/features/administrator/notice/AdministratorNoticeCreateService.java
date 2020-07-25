
package acme.features.administrator.notice;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);

		request.bind(entity, errors, "creation");

	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		boolean confirmation = false;
		model.setAttribute("confirmation", confirmation);

		request.unbind(entity, model, "header", "title", "deadline", "body", "related");

	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		Notice result;
		result = new Notice();

		return result;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.getDeadline() == null) {
			errors.state(request, false, "deadline", "administrator.notice.null-deadline");
		} else {
			boolean isDeadlineDateDuture = entity.getDeadline().after(new GregorianCalendar().getTime());
			errors.state(request, isDeadlineDateDuture, "deadline", "administrator.notice.incorrect-deadline");
		}

		errors.state(request, request.getModel().getBoolean("confirmation") == true, "confirmation", "administrator.notice.confirmation-not-checked");

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {

		this.repository.save(entity);

	}

}
