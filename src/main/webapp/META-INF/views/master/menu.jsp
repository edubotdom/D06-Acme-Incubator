<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Patron,acme.entities.roles.Bookkeeper,acme.entities.roles.Investor,acme.entities.roles.Entrepreneur"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.edubotdom-favourite-link" action="https://www.formula1.com/"/>
			<!-- BotiaBulletin links -->
			<acme:menu-suboption code="master.menu.anonymous.createBotiaBulletin" action="/anonymous/botia_bulletin/create"/>
			<acme:menu-suboption code="master.menu.anonymous.listBotiaBulletin" action="/anonymous/botia_bulletin/list"/>
			<!-- Notice links -->
			<acme:menu-suboption code="master.menu.anonymous.listNotice" action="/anonymous/notice/list"/>
			<!-- Technologies' links -->
			<acme:menu-suboption code="master.menu.anonymous.listTechnology" action="/anonymous/technology/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listTechnologyBySector" action="/anonymous/technology/list_by_sector"/>			
			<acme:menu-suboption code="master.menu.anonymous.listTechnologyByStars" action="/anonymous/technology/list_by_stars"/>
			<!-- Tools' links -->
			<acme:menu-suboption code="master.menu.anonymous.listTool" action="/anonymous/tool/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listToolBySector" action="/anonymous/tool/list_by_sector"/>			
			<acme:menu-suboption code="master.menu.anonymous.listToolByStars" action="/anonymous/tool/list_by_stars"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<!-- Notice links -->
			<acme:menu-suboption code="master.menu.administrator.listNotice" action="/administrator/notice/list"/>
			<acme:menu-suboption code="master.menu.administrator.createNotice" action="/administrator/notice/create"/>
			<!-- Customizarion links -->
			<acme:menu-suboption code="master.menu.administrator.customization" action="/administrator/customization/list"/>
			<!-- Dashboard link -->
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"/>
			<!-- Inquiry links -->
			<acme:menu-suboption code="master.menu.administrator.listInquiry" action="/administrator/inquiry/list"/>
			<acme:menu-suboption code="master.menu.administrator.createInquiry" action="/administrator/inquiry/create"/>
			<!-- Overture links -->
			<acme:menu-suboption code="master.menu.administrator.listOverture" action="/administrator/overture/list"/>
			<acme:menu-suboption code="master.menu.administrator.createOverture" action="/administrator/overture/create"/>
			<!-- Technology links -->
			<acme:menu-suboption code="master.menu.administrator.listTechnology" action="/administrator/technology/list"/>
			<acme:menu-suboption code="master.menu.administrator.createTechnology" action="/administrator/technology/create"/>
			<!-- Tool links -->
			<acme:menu-suboption code="master.menu.administrator.listTool" action="/administrator/tool/list"/>
			<acme:menu-suboption code="master.menu.administrator.createTool" action="/administrator/tool/create"/>
			<!-- Challenge links -->
			<acme:menu-suboption code="master.menu.administrator.listChallenge" action="/administrator/challenge/list"/>
			<acme:menu-suboption code="master.menu.administrator.createChallenge" action="/administrator/challenge/create"/>
			<!-- Bookkeeper links -->
			<acme:menu-suboption code="master.menu.administrator.listAuthorizations" action="/administrator/authorization/list"/>
			

			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
	</acme:menu-left>

		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">

					<!-- Banner links -->
			<acme:menu-suboption code="master.menu.patron.listBanner" action="/patron/banner/list"/>
			<acme:menu-suboption code="master.menu.patron.createBanner" action="/patron/banner/create"/>
		</acme:menu-option>		

		<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<!-- Investment rounds' links -->
			<acme:menu-suboption code="master.menu.bookkeeper.listRoundsAccounted" action="/bookkeeper/round/list_accounted"/>
			<acme:menu-suboption code="master.menu.bookkeeper.listRoundsNotAccounted" action="/bookkeeper/round/list_not_accounted"/>

		</acme:menu-option>

		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<!-- Investment rounds' links -->
			<acme:menu-suboption code="master.menu.investor.listRounds" action="/investor/round/list"/>
			<!-- Application to investment rounds' links -->
			<acme:menu-suboption code="master.menu.investor.listApplications" action="/investor/application/list_mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<!-- Investment rounds' links -->
			<acme:menu-suboption code="master.menu.entrepreneur.listRounds" action="/entrepreneur/round/list_mine"/>
			<acme:menu-suboption code="master.menu.entrepreneur.createRound" action="/entrepreneur/round/create"/>			
			<!-- Application to investment rounds' links -->
			<acme:menu-suboption code="master.menu.entrepreneur.listApplications" action="/entrepreneur/application/list_mine"/>
			<acme:menu-suboption code="master.menu.entrepreneur.listApplicationsByCreation" action="/entrepreneur/application/list_mine_by_creation"/>
			<acme:menu-suboption code="master.menu.entrepreneur.listApplicationsByTicker" action="/entrepreneur/application/list_mine_by_ticker"/>
		</acme:menu-option>
		
		
		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<!-- Notice links -->
			<acme:menu-suboption code="master.menu.authenticated.listNotice" action="/authenticated/notice/list"/>
			<!-- Inquiry links -->
			<acme:menu-suboption code="master.menu.authenticated.listInquiry" action="/authenticated/inquiry/list"/>
			<!-- Overture links -->
			<acme:menu-suboption code="master.menu.authenticated.listOverture" action="/authenticated/overture/list"/>	
			<!-- Notice links -->
			<acme:menu-suboption code="master.menu.authenticated.listNotice" action="/authenticated/notice/list"/>
			<!-- Technologies' links -->
			<acme:menu-suboption code="master.menu.authenticated.listTechnology" action="/authenticated/technology/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listTechnologyBySector" action="/authenticated/technology/list_by_sector"/>			
			<acme:menu-suboption code="master.menu.authenticated.listTechnologyByStars" action="/authenticated/technology/list_by_stars"/>						
			<!-- Tools' links -->
			<acme:menu-suboption code="master.menu.authenticated.listTool" action="/authenticated/tool/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listToolBySector" action="/authenticated/tool/list_by_sector"/>			
			<acme:menu-suboption code="master.menu.authenticated.listToolByStars" action="/authenticated/tool/list_by_stars"/>
			<!-- Challenges' links -->
			<acme:menu-suboption code="master.menu.authenticated.listChallenges" action="/authenticated/challenge/list"/>
			<!-- Investment rounds' links -->
			<acme:menu-suboption code="master.menu.authenticated.listRounds" action="/authenticated/round/list"/>
			
			<!-- Forum' links -->
			<acme:menu-suboption code="master.menu.authenticated.listForums" action="/authenticated/forum/list_mine"/>


		</acme:menu-option>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create" access="!hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update" access="hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create" access="!hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update" access="hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.request-become-bookkeeper" action="/authenticated/authorization/create"
				access="!hasRole('Authorization')" />
			<acme:menu-suboption code="master.menu.user-account.become-bookkeeper" action="/authenticated/authorization/show"
			access="!hasRole('Bookkeeper') && hasRole('Authorization')" />
			<acme:menu-suboption code="master.menu.user-account.bookkeeper" action="/authenticated/bookkeeper/update" access="hasRole('Bookkeeper')" />
			

		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

