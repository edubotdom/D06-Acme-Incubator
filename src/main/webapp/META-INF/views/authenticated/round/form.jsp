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

	<acme:form-textbox code="authenticated.round.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" />
	<acme:form-moment code="authenticated.round.form.label.creation" path="creation"/>
	<acme:form-textbox code="authenticated.round.form.label.title" path="title" />
	<acme:form-textarea code="authenticated.round.form.label.description" path="description" />
	<acme:form-double code="authenticated.round.form.label.money" path="money" />
	<acme:form-textbox code="authenticated.round.form.label.information" path="information" />
	<acme:form-textbox code="authenticated.round.form.label.kind" path="kind" />
	
		<acme:form-return code="authenticated.round.form.button.accountingList" action="${accountingList}" />
		<acme:form-return code="authenticated.round.form.button.activitiesList" action="${roundListActivities}" />
		<acme:form-return code="authenticated.round.form.button.createForum" action="${createForum}" />
		
	<acme:form-return code="authenticated.round.form.button.return" />
</acme:form>
