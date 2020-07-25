
package acme.features.entrepreneur.round;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Entrepreneur;
import acme.entities.rounds.Round;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/round/")
public class EntrepreneurRoundController extends AbstractController<Entrepreneur, Round> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurRoundListService	listMineService;

	@Autowired
	private EntrepreneurRoundShowService	showService;

	@Autowired
	private EntrepreneurRoundCreateService	createService;

	@Autowired
	private EntrepreneurRoundUpdateService	updateService;

	@Autowired
	private EntrepreneurRoundDeleteService	deleteService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}
}
