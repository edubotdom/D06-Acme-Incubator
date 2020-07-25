
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialization identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------------------------

	//Numbers
	Integer						totalNumberOfNotices;
	Integer						totalNumberOfTechnologyRecords;
	Integer						totalNumberOfToolRecords;
	//Inquiries
	Double						minimumMoneyInquiries;
	Double						maximumMoneyInquiries;
	Double						averageMoneyInquiries;
	Double						standardDesviationMoneyInquiries;
	//Overtures
	Double						minimumMoneyOvertures;
	Double						maximumMoneyOvertures;
	Double						averageMoneyOvertures;
	Double						standardDesviationMoneyOvertures;

	//Applications and investment rounds
	Double						averageRoundsPerEntrepreneur;
	Double						averageApplicationsPerEntrepreneur;
	Double						averageApplicationsPerInvestor;

	// Chart atts.
	List<String>				technologySectors;
	List<Long>					numberTechnologiesBySector;

	List<String>				toolSectors;
	List<Long>					numberToolsBySector;

	Double						openSourceRatioTechnologies;
	Double						closedSourceRatioTechnologies;

	Double						openSourceRatioTools;
	Double						closedSourceRatioTools;

	Double						ratioSeedInvestmentRounds;
	Double						ratioAngelInvestmentRounds;
	Double						ratioSeriesAInvestmentRounds;
	Double						ratioSeriesBInvestmentRounds;
	Double						ratioSeriesCInvestmentRounds;
	Double						ratioBridgeInvestmentRounds;

	Double						ratioAcceptedApplications;
	Double						ratioRejectedApplications;
	Double						ratioPendingApplications;

	// Round applications' time series --------------------------------------------------

	List<Integer>				numberListOfPendingApplications;
	List<String>				dateListOfPendingApplications;

	List<Integer>				numberListOfAcceptedApplications;
	List<String>				dateListOfAcceptedApplications;

	List<Integer>				numberListOfRejectedApplications;
	List<String>				dateListOfRejectedApplications;

	// Derived attributes ------------------------------------------------------

	// Relationships -----------------------------------------------------------
}
