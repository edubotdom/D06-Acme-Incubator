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

package acme.features.bookkeeper.round;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountings.Accounting;
import acme.entities.activities.Activity;
import acme.entities.roles.Entrepreneur;
import acme.entities.rounds.Round;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperRoundRepository extends AbstractRepository {

	@Query("select j from Round j where j.id = ?1")
	Round findOneRoundById(int id);

	@Query("select j from Round j where exists(select a from Activity a where(a.round.id=j.id AND a.end>CURRENT_TIMESTAMP) AND j.status=1)")
	Collection<Round> findManyActive();

	@Query("select j from Round j where j.status=1 AND exists(select a from Accounting a where(a.round.id=j.id AND a.bookkeeper.userAccount.id=?1))")
	Collection<Round> findManyAccounted(int id);

	@Query("select j from Round j where j.status=1 AND not exists(select a from Accounting a where(a.round.id=j.id AND a.bookkeeper.userAccount.id=?1))")
	Collection<Round> findManyNotAccounted(int id);

	@Query("select a from Accounting a where a.round.id=?1")
	Collection<Accounting> findManyAccountsByRound(int id);

	@Query("select e from Entrepreneur e where e.userAccount.id= ?1")
	Entrepreneur findOneEntrepreneurByUserAccountId(int entrepreneurId);

	@Query("select d from Activity d where d.round.id = ?1")
	Collection<Activity> findManyActivitiesByRound(int id);

}
