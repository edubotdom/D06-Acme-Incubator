
package acme.features.entrepreneur.accounting;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.accountings.Accounting;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/accounting/")
public class EntrepreneurAccountingController extends AbstractController<Entrepreneur, Accounting> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurAccountingListService	listAccountedService;

	@Autowired
	private EntrepreneurAccountingShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listAccountedService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
