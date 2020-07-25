<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it i
s not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	
	<acme:form-moment code="entrepreneur.round.form.label.creation" path="creation" readonly="true"/>
	<acme:form-textbox code="entrepreneur.round.form.label.title" path="title" />
	<acme:form-textarea code="entrepreneur.round.form.label.description" path="description" />
	<acme:form-money code="entrepreneur.round.form.label.money" path="money" />
	<acme:form-textbox code="entrepreneur.round.form.label.information" path="information" />

	<jstl:if test="${command == 'show' || command == 'update' || command == 'delete'}">
		<acme:form-textbox code="entrepreneur.round.form.label.kind" path="kind" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${(command == 'show' || command == 'update' || command == 'delete') && isFinalMode == 'true'}">
			<acme:form-textbox code="entrepreneur.round.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${(command == 'show' || command == 'update' || command == 'delete') && isFinalMode == 'false'}">
		<acme:form-select code="entrepreneur.round.form.label.status" path="status">
			<acme:form-option code="entrepreneur.round.form.label.draft" value="0"/>
			<acme:form-option code="entrepreneur.round.form.label.published" value="1"/>
		</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
	<acme:form-textbox code="entrepreneur.round.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" />
			<acme:form-select code="entrepreneur.round.form.label.kind" path="kind">
				<acme:form-option code="entrepreneur.round.form.label.kind0" value="SEED"/>
				<acme:form-option code="entrepreneur.round.form.label.kind1" value="ANGEL"/>
				<acme:form-option code="entrepreneur.round.form.label.kind2" value="SERIES-A"/>
				<acme:form-option code="entrepreneur.round.form.label.kind3" value="SERIES-B"/>
				<acme:form-option code="entrepreneur.round.form.label.kind4" value="SERIES-C"/>
				<acme:form-option code="entrepreneur.round.form.label.kind5" value="BRIDGE"/>
			</acme:form-select>
	</jstl:if>


	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="entrepreneur.round.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" readonly='true'/>
		<acme:form-return code="entrepreneur.round.form.button.accountingList" action="${accountingList}" />
		<jstl:if test="${isFinalMode=='false'}">
			<acme:form-return code="entrepreneur.round.form.button.createActivities" action="${roundCreateActivity}" />
		</jstl:if>
		<acme:form-return code="entrepreneur.round.form.button.activitiesList" action="${roundListActivities}" />
		
	</jstl:if>

	<jstl:if test="${isFinalMode=='false' && iAmPrincipal=='true'}">
		<acme:form-submit test="${command == 'show' || command == 'update'}" code="entrepreneur.round.form.button.update"
			action="/entrepreneur/round/update" />
	</jstl:if>

	<jstl:if test="${iAmPrincipal=='true' && roundApplied=='true'}">
		<acme:form-submit test="${command == 'show' || command == 'update' || command == 'delete'}" code="entrepreneur.round.form.button.delete"
			action="/entrepreneur/round/delete" />
	</jstl:if>

	<acme:form-submit test="${command == 'create'}" code="entrepreneur.round.form.button.create" action="/entrepreneur/round/create" />

	<acme:form-hidden path="budgetFulfilled"/>
	<acme:form-hidden path="iAmPrincipal"/>
	<acme:form-hidden path="roundApplied"/>
	<acme:form-hidden path="isFinalMode"/>

	<acme:form-return code="entrepreneur.round.form.button.return" />
</acme:form>
