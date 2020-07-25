
package acme.features.bookkeeper.accounting;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.accountings.Accounting;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/accounting/")
public class BookkeeperAccountingController extends AbstractController<Bookkeeper, Accounting> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookkeeperAccountingListService		listAccountedService;

	@Autowired
	private BookkeeperAccountingShowService		showService;

	@Autowired
	private BookkeeperAccountingCreateService	createService;

	@Autowired
	private BookkeeperAccountingUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listAccountedService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
