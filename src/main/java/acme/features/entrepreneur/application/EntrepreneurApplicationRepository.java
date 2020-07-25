
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.round.entrepreneur.id = ?1")
	Collection<Application> findManyByEntrepreneurId(int id);

	@Query("select a from Application a where a.round.entrepreneur.id = ?1 group by a.ticker")
	Collection<Application> findManyByEntrepreneurIdByTicker(int id);

	@Query("select a from Application a where a.round.entrepreneur.id = ?1 group by a.creation")
	Collection<Application> findManyByEntrepreneurIdByCreationDate(int id);
}
