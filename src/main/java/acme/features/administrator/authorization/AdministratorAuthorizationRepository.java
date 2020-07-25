/*
 * AuthenticatedEmployerRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.authorization;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Authorization;
import acme.entities.roles.Bookkeeper;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuthorizationRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select e from Authorization e where e.userAccount.id = ?1")
	Bookkeeper findOneBookkeeperByUserAccountId(int id);

	@Query("select e from Authorization e where e.id = ?1")
	Authorization findOneAuthorizationById(int id);

	@Query("select e from Authorization e")
	Collection<Authorization> findManyAll();
}
