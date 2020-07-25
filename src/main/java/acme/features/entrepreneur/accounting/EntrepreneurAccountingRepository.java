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

package acme.features.entrepreneur.accounting;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountings.Accounting;
import acme.entities.roles.Bookkeeper;
import acme.entities.rounds.Round;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurAccountingRepository extends AbstractRepository {

	@Query("select j from Round j where j.id = ?1")
	Round findOneRoundById(int id);

	@Query("select j from Accounting j where j.id = ?1")
	Accounting findOneAccountingById(int id);

	@Query("select a from Accounting a where a.round.id=?1 and a.status = 1")
	Collection<Accounting> findManyAccountsByRound(int id);

	@Query("select b from Bookkeeper b where b.userAccount.id= ?1")
	Bookkeeper findOneBookkeeperByUserAccountId(int entrepreneurId);

}
