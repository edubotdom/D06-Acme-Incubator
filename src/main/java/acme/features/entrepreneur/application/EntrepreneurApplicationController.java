
package acme.features.entrepreneur.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/application/")
public class EntrepreneurApplicationController extends AbstractController<Entrepreneur, Application> {

	@Autowired
	private EntrepreneurApplicationListMineService					listMineService;

	@Autowired
	private EntrepreneurApplicationListMineByCreationDateService	listMineByCreationDateService;

	@Autowired
	private EntrepreneurApplicationListMineByTickerService			listMineByTickerService;

	@Autowired
	private EntrepreneurApplicationShowService						showService;

	@Autowired
	private EntrepreneurApplicationUpdateService					updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_MINE_BY_CREATION, BasicCommand.LIST, this.listMineByCreationDateService);
		super.addCustomCommand(CustomCommand.LIST_MINE_BY_TICKER, BasicCommand.LIST, this.listMineByTickerService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
