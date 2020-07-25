
package acme.features.anonymous.botiaBulletin;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.botia_bulletins.Botia_bulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousBotiaBulletinCreateService implements AbstractCreateService<Anonymous, Botia_bulletin> {

	//Internal state
	@Autowired
	AnonymousBotiaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<Botia_bulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Botia_bulletin instantiate(final Request<Botia_bulletin> request) {
		assert request != null;

		Botia_bulletin result;
		Date moment;
		Date expiring_date;

		moment = new Date(System.currentTimeMillis() - 1);
		expiring_date = new Date(System.currentTimeMillis() + 100000000);

		result = new Botia_bulletin();
		result.setMoment(moment);
		result.setExpiring_date(expiring_date);
		result.setAuthor("Default author");
		result.setTitle("Default title");
		result.setText_body("Default text message");

		return result;
	}

	@Override
	public void unbind(final Request<Botia_bulletin> request, final Botia_bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "expiring_date", "author", "title", "text_body");
	}

	@Override
	public void bind(final Request<Botia_bulletin> request, final Botia_bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Botia_bulletin> request, final Botia_bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Fecha expiración en futuro
		errors.state(request, entity.getExpiring_date().after(Date.from(Instant.now())), "expiring_date", "anonymous.botiaBulletin.form.label.expiring_date.past_expired");

	}

	@Override
	public void create(final Request<Botia_bulletin> request, final Botia_bulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
