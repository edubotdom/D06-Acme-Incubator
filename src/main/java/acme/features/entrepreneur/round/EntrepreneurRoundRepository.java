/*
 * ConsumerAcmeRequestRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.round;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountings.Accounting;
import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.customization.Customization;
import acme.entities.roles.Entrepreneur;
import acme.entities.rounds.Round;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurRoundRepository extends AbstractRepository {

	@Query("select j from Round j where j.id = ?1")
	Round findOneRoundById(int id);

	@Query("select j from Round j where j.entrepreneur.id = ?1")
	Collection<Round> findManyByEntrepreneurId(int entrepreneurId);

	@Query("select e from Entrepreneur e where e.userAccount.id= ?1")
	Entrepreneur findOneEntrepreneurByUserAccountId(int entrepreneurId);

	@Query("select d from Activity d where d.round.id = ?1")
	Collection<Activity> findManyActivitiesByRound(int id);

	@Query("select j from Round j where j.ticker = ?1")
	Round findOneRoundByTicker(String ticker);

	@Query("select c from Customization c")
	Customization findCustomization();

	@Query("select a from Application a where a.round.id = ?1")
	Collection<Application> findManyApplicationsByRound(int id);

	@Query("select a from Accounting a where a.round.id = ?1")
	Collection<Accounting> findManyAccountingsByRound(int id);
	/*
	 * @Query("select f from Forum f where f.round.id = ?1")
	 * Collection<Forum> findManyForumByRound(int id);
	 */
}
