/*
 * AuthenticatedMessageCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.message;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageRepository repository;

	// AbstractCreateService<Authenticated, Message> ---------------------------


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		Boolean result;
		int countUser;
		int threadId;

		Principal principal;
		int principalId;

		threadId = request.getModel().getInteger("id");

		principal = request.getPrincipal();
		principalId = principal.getAccountId();
		countUser = this.repository.countAuthenticatedByForumId(principalId, threadId);

		result = countUser != 0;			// si suma 1 significa que dicho thread pertenece a dicho Authenticated
		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.message.must-accept");

		if (!entity.getTitle().isEmpty() || !entity.getBody().isEmpty()) {
			//Spam
			String[] titleSplitted = entity.getTitle().split(" ");

			String[] bodySplitted = entity.getBody().split(" ");

			Customization customisation = this.repository.findCustomization();

			String[] spamWords = customisation.getSpam().split(",");
			Double spamThreshold = customisation.getThreshold();

			List<String> spamWordsInList = IntStream.range(0, spamWords.length).boxed().map(x -> spamWords[x].trim()).collect(Collectors.toList());

			Integer titleNumSpam = (int) IntStream.range(0, titleSplitted.length).boxed().map(x -> titleSplitted[x].trim()).filter(i -> spamWordsInList.contains(i)).count();
			Integer bodyNumSpam = (int) IntStream.range(0, bodySplitted.length).boxed().map(x -> bodySplitted[x].trim()).filter(i -> spamWordsInList.contains(i)).count();

			boolean isTitleFreeOfSpam = true;

			boolean isBodyFreeOfSpam = true;

			if (titleNumSpam != 0) {
				isTitleFreeOfSpam = 100 * titleNumSpam / titleSplitted.length < spamThreshold;
			}

			if (bodyNumSpam != 0) {
				isBodyFreeOfSpam = 100 * bodyNumSpam / bodySplitted.length < spamThreshold;
			}
			errors.state(request, isTitleFreeOfSpam, "title", "authenticated.message.spamWords");

			errors.state(request, isBodyFreeOfSpam, "body", "authenticated.message.spamWords");
		}
		if (!entity.getTags().isEmpty()) {
			String[] tagsSplitted = entity.getTags().split(" ");

			Customization customisation = this.repository.findCustomization();
			String[] spamWords = customisation.getSpam().split(",");
			Double spamThreshold = customisation.getThreshold();

			List<String> spamWordsInList = IntStream.range(0, spamWords.length).boxed().map(x -> spamWords[x].trim()).collect(Collectors.toList());
			Integer tagsNumSpam = (int) IntStream.range(0, tagsSplitted.length).boxed().map(x -> tagsSplitted[x].trim()).filter(i -> spamWordsInList.contains(i)).count();
			boolean isTagFreeOfSpam = true;
			if (tagsNumSpam != 0) {
				isTagFreeOfSpam = 100 * tagsNumSpam / tagsSplitted.length < spamThreshold;
			}
			errors.state(request, isTagFreeOfSpam, "tags", "authenticated.message.spamWords");
		}

	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

		int idForum = request.getModel().getInteger("id");
		String direccionForum = "../message/create?id=" + idForum;
		model.setAttribute("direccionForum", direccionForum);

		request.unbind(entity, model, "title", "moment", "tags", "body", "user", "forum");
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		Principal principal = request.getPrincipal();
		int authenticatedId = principal.getAccountId();
		Authenticated authenticated = this.repository.findOneAuthenticatedByUserAccountId(authenticatedId);

		int idthread = request.getModel().getInteger("id");
		Forum thread = this.repository.findForumById(idthread);

		Message result;
		result = new Message();
		result.setMoment(moment);
		result.setUser(authenticated);
		result.setForum(thread);

		return result;
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
