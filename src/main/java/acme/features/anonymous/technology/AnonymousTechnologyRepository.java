/*
 * AnonymousUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.technology;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologies.Technology;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTechnologyRepository extends AbstractRepository {

	@Query("select n from Technology n")
	Collection<Technology> findMany();

	@Query("select n from Technology n where n.id = ?1")
	Technology findOneById(int id);

	@Query("select n from Technology n group by n.sector")
	Collection<Technology> findBySector();

	@Query("select n from Technology n group by n.stars")
	Collection<Technology> findByStars();
}
