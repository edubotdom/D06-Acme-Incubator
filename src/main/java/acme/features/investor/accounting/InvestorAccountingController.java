
package acme.features.investor.accounting;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.accountings.Accounting;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/accounting/")
public class InvestorAccountingController extends AbstractController<Investor, Accounting> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InvestorAccountingListService	listAccountedService;

	@Autowired
	private InvestorAccountingShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listAccountedService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
