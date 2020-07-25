/*
 * AuthenticatedAcmeRequestRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.customization.Customization;
import acme.entities.rounds.Round;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurActivityRepository extends AbstractRepository {

	@Query("select d from Activity d where d.id = ?1")
	Activity findOneById(int id);

	@Query("select d from Round d where d.id = ?1")
	Round findRoundById(int id);

	@Query("select j from Round j where j.id = ?1")
	Round findOneRoundById(int id);

	@Query("select d from Activity d where d.round.id = ?1")
	Collection<Activity> findManyByRound(int id);

	@Query("select c from Customization c")
	Customization findCustomization();

}
