
package acme.features.bookkeeper.round;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Bookkeeper;
import acme.entities.rounds.Round;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/round/")
public class BookkeeperRoundController extends AbstractController<Bookkeeper, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookkeeperRoundListAccountedService		listAccountedService;

	@Autowired
	private BookkeeperRoundListNotAccountedService	listNotAccountedService;

	@Autowired
	private BookkeeperRoundShowService				showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ACCOUNTED, BasicCommand.LIST, this.listAccountedService);
		super.addCustomCommand(CustomCommand.LIST_NOT_ACCOUNTED, BasicCommand.LIST, this.listNotAccountedService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
