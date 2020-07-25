<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN"/>
		<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="investor.application.form.label.roundTicker" path="roundTicker" readonly="true" />
		<acme:form-textbox code="investor.application.form.label.investor" path="investorName" readonly="true" />
		<acme:form-textbox code="investor.application.form.label.roundCreator" path="roundCreator" readonly="true" />
		<acme:form-moment code="investor.application.form.label.creation" path="creation"/>
		<acme:form-textarea code="investor.application.form.label.justification" path="justification" readonly="true" />
		
		</jstl:if>		
		<acme:form-textarea code="investor.application.form.label.statement" path="statement"/>
		<acme:form-money code="investor.application.form.label.offer" path="offer" />

		<jstl:if test="${status=='accepted' && command == 'show'}">
		<acme:form-select code="investor.application.form.label.status" path="status" readonly="true">
			<acme:form-option code="investor.application.form.label.status.accepted" value="accepted" selected="true"/>
			<acme:form-option code="investor.application.form.label.status.rejected" value="rejected"/>
			<acme:form-option code="investor.application.form.label.status.pending" value="pending"/>
		</acme:form-select>
		</jstl:if>

		<jstl:if test="${status=='rejected' && command == 'show'}">
		<acme:form-select code="investor.application.form.label.status" path="status" readonly="true">
			<acme:form-option code="investor.application.form.label.status.accepted" value="accepted"/>
			<acme:form-option code="investor.application.form.label.status.rejected" value="rejected" selected="true"/>
			<acme:form-option code="investor.application.form.label.status.pending" value="pending"/>
		</acme:form-select>
		</jstl:if>
		
		<jstl:if test="${status=='pending' && command == 'show'}">
		<acme:form-select code="investor.application.form.label.status" path="status" readonly="true">
			<acme:form-option code="investor.application.form.label.status.accepted" value="accepted"/>
			<acme:form-option code="investor.application.form.label.status.rejected" value="rejected"/>
			<acme:form-option code="investor.application.form.label.status.pending" value="pending" selected="true"/>
		</acme:form-select>
		</jstl:if>

	<acme:form-hidden path="direccionApplication"/>

	<acme:form-submit test="${command == 'create'}" code="investor.application.form.button.create" action="${direccionApplication}" />
	<acme:form-return code="investor.application.form.button.return" />

</acme:form>
