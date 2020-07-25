/*
 * AuthenticatedAuthorizedUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Authorization;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedAuthorizationUpdateService implements AbstractUpdateService<Authenticated, Authorization> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuthorizationRepository repository;


	// AbstractUpdateService<Authenticated, Authorized> interface -----------------

	@Override
	public boolean authorise(final Request<Authorization> request) {
		assert request != null;

		Authorization result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneAuthorizationById(userAccountId);

		return result != null;
	}

	@Override
	public void validate(final Request<Authorization> request, final Authorization entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Authorization> request, final Authorization entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Authorization> request, final Authorization entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.isAccepted()) {
			model.setAttribute("status", "Accepted");
		} else {
			model.setAttribute("status", "Pending");
		}

		request.unbind(entity, model, "body");
	}

	@Override
	public Authorization findOne(final Request<Authorization> request) {
		assert request != null;

		Authorization result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneAuthorizationById(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Authorization> request, final Authorization entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Authorization> request, final Response<Authorization> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
