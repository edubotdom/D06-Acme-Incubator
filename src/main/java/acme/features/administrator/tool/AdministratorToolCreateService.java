
package acme.features.administrator.tool;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tools.Tool;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorToolCreateService implements AbstractCreateService<Administrator, Tool> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorToolRepository repository;


	@Override
	public boolean authorise(final Request<Tool> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Tool> request, final Tool entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Tool> request, final Tool entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "sector", "inventor", "description", "website", "contact", "source", "stars");
	}

	@Override
	public Tool instantiate(final Request<Tool> request) {
		Tool result;
		result = new Tool();

		return result;
	}

	@Override
	public void validate(final Request<Tool> request, final Tool entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String[] sectorWords = this.repository.findCustomization().getSectors().trim().split(",");
		List<String> sectors = IntStream.range(0, sectorWords.length).boxed().map(x -> sectorWords[x].trim()).collect(Collectors.toList());

		errors.state(request, sectors.contains(entity.getSector()), "sector", "administrator.tool.incorrectSector");

	}

	@Override
	public void create(final Request<Tool> request, final Tool entity) {

		this.repository.save(entity);

	}

}
