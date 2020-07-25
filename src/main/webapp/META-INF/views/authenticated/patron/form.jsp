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
	<acme:form-textbox code="authenticated.patron.form.label.organisation" path="organisation"/>

	<acme:form-hidden path="showCard"/>
	<acme:form-hidden path="associateCard"/>
	<acme:form-hidden path="hasCard"/>

	<jstl:if test="${command == 'update' && hasCard}">
	<acme:form-return code="authenticated.patron.form.button.showCard" action="${showCard}"/>
	</jstl:if>

	<jstl:if test="${command == 'update' && !hasCard}">	
		<acme:form-return code="authenticated.patron.form.button.associateCard" action="${associateCard}"/>	
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.patron.form.button.create" action="/authenticated/patron/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.patron.form.button.update" action="/authenticated/patron/update"/>
	<acme:form-return code="authenticated.patron.form.button.return"/>
</acme:form>
