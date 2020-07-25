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

package acme.features.patron.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.cards.Card;
import acme.entities.customization.Customization;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronBannerRepository extends AbstractRepository {

	@Query("select n from Banner n")
	Collection<Banner> findMany();

	@Query("select n from Banner n where n.patron.userAccount.id = ?1")
	Collection<Banner> findManyByPatronUserAccountId(int id);

	@Query("select n from Banner n where n.id = ?1")
	Banner findOneById(int id);

	@Query("select n from Patron n where n.userAccount.id = ?1")
	Patron findOnePatronById(int id);

	@Query("select n from Card n where exists(select b from Banner b where b.card.id= n.id and b.id = ?1)")
	Card findCardByBannerId(int id);

	@Query("select c from Customization c")
	Customization findCustomization();
}
