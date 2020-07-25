/*
 * AuthenticatedUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Notice a")
	Integer countNumberOfNotices();

	@Query("select count(cr) from Technology cr")
	Integer countTechnologyRecords();

	@Query("select count(ir) from Tool ir")
	Integer countToolRecords();

	//Minimum, maximum, average, and standard deviation of the active inquiries

	@Query("select min(ar.money.amount) from Inquiry ar where ar.deadline >= current_date()")
	Double minimumInquiry();

	@Query("select max(ar.money.amount) from Inquiry ar where ar.deadline >= current_date()")
	Double maximumInquiry();

	@Query("select avg(ar.money.amount) from Inquiry ar where ar.deadline >= current_date()")
	Double averageInquiry();

	@Query("select stddev(ar.money.amount) from Inquiry ar where ar.deadline >= current_date()")
	Double standardDesviationInquiry();

	//Minimum, maximum, average, and standard deviation of the active overtures

	@Query("select min(ao.money.amount) from Overture ao where ao.deadline >= current_date()")
	Double minimumOverture();

	@Query("select max(ao.money.amount) from Overture ao where ao.deadline >= current_date()")
	Double maximumOverture();

	@Query("select avg(ao.money.amount) from Overture ao where ao.deadline >= current_date()")
	Double averageOverture();

	@Query("select stddev(ao.money.amount) from Overture ao where ao.deadline >= current_date()")
	Double standardDesviationOverture();

	@Query("select count(cr), cr.sector from Technology cr group by cr.sector order by cr.sector")
	Collection<Object[]> getNumTechBySector();

	@Query("select count(ir), ir.sector from Tool ir group by ir.sector order by ir.sector")
	Collection<Object[]> getNumToolBySector();

	@Query("select 1.0 * count(a) from Technology a where a.source = 'closed-source'")
	Double openSourceRatioTechnologiesRatio();

	@Query("select 1.0 * count(a) from Technology a where a.source = 'open-source'")
	Double closedSourceRatioTechnologiesRatio();

	@Query("select 1.0 * count(a) from Tool a where a.source = 'open-source'")
	Double openSourceRatioToolsRatio();

	@Query("select 1.0 * count(a) from Tool a where a.source = 'closed-source'")
	Double closedSourceRatioToolsRatio();

	@Query("select avg(select count(r) from Round r where r.entrepreneur.id = e.id) from Entrepreneur e")
	Double getAverageRoundsPerEntrepreneur();

	@Query("select avg(select count(a) from Application a where exists(select r from Round r where r.entrepreneur.id = e.id and a.round.id = r.id)) from Entrepreneur e")
	Double getAverageApplicationsPerEntrepreneur();

	@Query("select avg(select count(a) from Application a where a.investor.id = i.id) from Investor i")
	Double getAverageApplicationsPerInvestor();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'pending'")
	Double ratioPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'accepted'")
	Double ratioAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'rejected'")
	Double ratioRejectedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Round b) from Round a where a.kind = 'SEED'")
	Double ratioSeedInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from Round b) from Round a where a.kind = 'ANGEL'")
	Double ratioAngelInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from Round b) from Round a where a.kind = 'SERIES-A'")
	Double ratioSeriesAInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from Round b) from Round a where a.kind = 'SERIES-B'")
	Double ratioSeriesBInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from Round b) from Round a where a.kind = 'SERIES-C'")
	Double ratioSeriesCInvestmentRounds();

	@Query("select 1.0 * count(a) / (select count(b) from Round b) from Round a where a.kind = 'BRIDGE'")
	Double ratioBridgeInvestmentRounds();

	@Query("select count(a) from Application a where a.status = 'pending' and date(a.creation) >= ?1 group by day(a.creation) order by date(a.creation) asc")
	List<Integer> getNumberListOfPendingApplications(Date date);

	@Query("select date(a.creation) from Application a where a.status = 'pending' and date(a.creation) >= ?1 group by day(a.creation) order by date(a.creation) asc")
	List<String> getDateListOfPendingApplications(Date date);

	@Query("select count(a) from Application a where a.status = 'accepted' and date(a.creation) >= ?1 group by day(a.creation) order by date(a.creation) asc")
	List<Integer> getNumberListOfAcceptedApplications(Date date);

	@Query("select date(a.creation) from Application a where a.status = 'accepted' and date(a.creation) >= ?1 group by day(a.creation) order by date(a.creation) asc")
	List<String> getDateListOfAcceptedApplications(Date date);

	@Query("select count(a) from Application a where a.status = 'rejected' and date(a.creation) >= ?1 group by day(a.creation) order by date(a.creation) asc")
	List<Integer> getNumberListOfRejectedApplications(Date date);

	@Query("select date(a.creation) from Application a where a.status = 'rejected' and date(a.creation) >= ?1 group by day(a.creation) order by date(a.creation) asc")
	List<String> getDateListOfRejectedApplications(Date date);

}
