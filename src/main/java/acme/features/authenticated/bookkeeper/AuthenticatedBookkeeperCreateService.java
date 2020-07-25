/*
 * AuthenticatedBookkeeperCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Authorization;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookkeeperCreateService implements AbstractCreateService<Authenticated, Bookkeeper> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBookkeeperRepository repository;

	// AbstractCreateService<Authenticated, Bookkeeper> ---------------------------


	@Override
	public boolean authorise(final Request<Bookkeeper> request) {
		assert request != null;

		Bookkeeper result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		Authorization authorization = this.repository.findOneAuthorizationByUserAccountId(userAccountId);
		boolean authorized = authorization != null && authorization.isAccepted();

		result = this.repository.findOneBookkeeperByUserAccountId(userAccountId);

		return result == null && authorized;
	}

	@Override
	public void validate(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Bookkeeper> request, final Bookkeeper entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Bookkeeper> request, final Bookkeeper entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibility");
	}

	@Override
	public Bookkeeper instantiate(final Request<Bookkeeper> request) {
		assert request != null;

		Bookkeeper result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Bookkeeper();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void create(final Request<Bookkeeper> request, final Bookkeeper entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Bookkeeper> request, final Response<Bookkeeper> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
