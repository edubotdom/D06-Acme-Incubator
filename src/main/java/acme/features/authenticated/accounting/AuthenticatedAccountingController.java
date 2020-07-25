
package acme.features.authenticated.accounting;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.accountings.Accounting;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/accounting/")
public class AuthenticatedAccountingController extends AbstractController<Authenticated, Accounting> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAccountingListService	listAccountedService;

	@Autowired
	private AuthenticatedAccountingShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listAccountedService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
