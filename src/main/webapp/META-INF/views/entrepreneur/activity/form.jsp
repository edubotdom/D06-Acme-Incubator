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
		<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title"/>
		<acme:form-moment code="entrepreneur.activity.form.label.start" path="start"/>
		<acme:form-moment code="entrepreneur.activity.form.label.end" path="end" />
		<acme:form-money code="entrepreneur.activity.form.label.budget" path="budget"/>		
	
	<acme:form-hidden path="direccionActivity" />
	<acme:form-hidden path="isFinalMode" />
	
	
	<jstl:if test="${command == 'create' }">
		<acme:form-submit code="entrepreneur.activity.form.button.create" action="${direccionActivity}" />
	</jstl:if>

	<jstl:if test="${isFinalMode=='false'}">
		<acme:form-submit test="${command == 'show' || command == 'update'}" code="entrepreneur.round.form.button.update"
			action="/entrepreneur/activity/update" />
	</jstl:if>

	<jstl:if test="${isFinalMode=='false'}">
		<acme:form-submit test="${command == 'show' || command == 'update' || command == 'delete'}" code="entrepreneur.round.form.button.delete"
			action="/entrepreneur/activity/delete" />
	</jstl:if>

	<acme:form-return code="entrepreneur.activity.form.button.return" />
</acme:form>
