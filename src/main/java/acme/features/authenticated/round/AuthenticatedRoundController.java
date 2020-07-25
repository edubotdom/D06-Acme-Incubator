
package acme.features.authenticated.round;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.rounds.Round;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/round/")
public class AuthenticatedRoundController extends AbstractController<Authenticated, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedRoundListService	listMineService;

	@Autowired
	private AuthenticatedRoundShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
