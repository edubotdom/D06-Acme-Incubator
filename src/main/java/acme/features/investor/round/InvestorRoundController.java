
package acme.features.investor.round;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Investor;
import acme.entities.rounds.Round;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/round/")
public class InvestorRoundController extends AbstractController<Investor, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InvestorRoundListService	listMineService;

	@Autowired
	private InvestorRoundShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
