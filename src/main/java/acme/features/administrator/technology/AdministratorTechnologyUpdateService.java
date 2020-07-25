
package acme.features.administrator.technology;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologies.Technology;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorTechnologyUpdateService implements AbstractUpdateService<Administrator, Technology> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorTechnologyRepository repository;


	@Override
	public boolean authorise(final Request<Technology> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Technology> request, final Technology entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Technology> request, final Technology entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "sector", "inventor", "description", "website", "contact", "source", "stars");
	}

	@Override
	public Technology findOne(final Request<Technology> request) {
		assert request != null;

		Technology result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Technology> request, final Technology entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String[] sectorWords = this.repository.findCustomization().getSectors().trim().split(",");
		List<String> sectors = IntStream.range(0, sectorWords.length).boxed().map(x -> sectorWords[x].trim()).collect(Collectors.toList());

		errors.state(request, sectors.contains(entity.getSector()), "sector", "administrator.technology.incorrectSector");

	}

	@Override
	public void update(final Request<Technology> request, final Technology entity) {

		this.repository.save(entity);

	}

}
