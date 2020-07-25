/*
 * AnonymousUserAccountController.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.tools.Tool;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/tool/")
public class AuthenticatedToolController extends AbstractController<Authenticated, Tool> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedToolListService			listService;

	@Autowired
	private AuthenticatedToolListBySectorService	listBySectorService;

	@Autowired
	private AuthenticatedToolListByStarsService		listByStarsService;

	@Autowired
	private AuthenticatedToolShowService			showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_BY_SECTOR, BasicCommand.LIST, this.listBySectorService);
		super.addCustomCommand(CustomCommand.LIST_BY_STARS, BasicCommand.LIST, this.listByStarsService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
