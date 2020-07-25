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

package acme.features.anonymous.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tools.Tool;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousToolRepository extends AbstractRepository {

	@Query("select n from Tool n")
	Collection<Tool> findMany();

	@Query("select n from Tool n where n.id = ?1")
	Tool findOneById(int id);

	@Query("select n from Tool n group by n.sector")
	Collection<Tool> findBySector();

	@Query("select n from Tool n group by n.stars")
	Collection<Tool> findByStars();
}
