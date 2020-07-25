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

package acme.features.anonymous.botiaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.botia_bulletins.Botia_bulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousBotiaBulletinRepository extends AbstractRepository {

	@Query("select b from Botia_bulletin b")
	Collection<Botia_bulletin> findMany();

	@Query("select b from Botia_bulletin b where b.expiring_date >= current_date()")
	Collection<Botia_bulletin> findManyFuture();
}
