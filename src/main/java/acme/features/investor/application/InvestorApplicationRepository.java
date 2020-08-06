
package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.customization.Customization;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.investor.id = ?1")
	Collection<Application> findManyByInvestorId(int id);

	@Query("select i from Investor i where i.userAccount.id = ?1")
	Investor findInvestorById(int id);

	@Query("select d from Activity d where d.round.id = ?1")
	Collection<Activity> findManyActivitiesByRound(int id);

	@Query("select c from Customization c")
	Customization findCustomization();

	@Query("select j from Application j where j.ticker = ?1")
	Application findOneApplicationByTicker(String ticker);
}
