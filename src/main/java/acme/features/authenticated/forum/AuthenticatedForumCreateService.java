/*
 * AuthenticatedForumCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.participants.Participant;
import acme.entities.rounds.Round;
import acme.features.authenticated.participant.AuthenticatedParticipantRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedForumRepository		repository;

	@Autowired
	private AuthenticatedParticipantRepository	repositoryParticipant;

	// AbstractCreateService<Authenticated, Forum> ---------------------------


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("createSubmit", "/authenticated/forum/create?roundId=" + request.getModel().getInteger("roundId"));

		request.unbind(entity, model, "creator", "round");
	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		assert request != null;

		Forum result;
		result = new Forum();
		result.setCreator(this.repository.findOneAuthenticatedByUserAccountId(request.getPrincipal().getAccountId()));

		Integer roundId = request.getModel().getInteger("roundId");
		Round round = this.repository.findOneRoundById(roundId);
		result.setRound(round);
		return result;
	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Participant participant = new Participant();
		participant.setForum(entity);
		Integer principalId = request.getPrincipal().getAccountId();
		Authenticated authenticated = this.repository.findOneAuthenticatedByUserAccountId(principalId);
		participant.setUser(authenticated);

		this.repository.save(entity);

		this.repositoryParticipant.save(participant);
	}

}
