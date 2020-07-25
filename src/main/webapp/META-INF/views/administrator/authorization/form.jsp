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

<link rel="stylesheet" href="libraries/acme/css/challenge.css" />

<acme:form>
	
	<jstl:if test="${status=='Accepted'}">
	<acme:form-select code="administrator.authorization.form.label.status" path="status" readonly="true">
		<acme:form-option code="administrator.authorization.form.label.status1" value="Accepted" selected="true"/>
		<acme:form-option code="administrator.authorization.form.label.status0" value="Pending"/>
	</acme:form-select>
	</jstl:if>
	<jstl:if test="${status=='Pending'}">
	<acme:form-select code="administrator.authorization.form.label.status" path="status">
		<acme:form-option code="administrator.authorization.form.label.status1" value="Accepted"/>
		<acme:form-option code="administrator.authorization.form.label.status0" value="Pending" selected="true"/>
	</acme:form-select>
	</jstl:if>
	<acme:form-textarea code="administrator.authorization.form.label.body" path="body" readonly="true" />
		
	<jstl:if test="${status!='Accepted'}">
	
	<acme:form-submit test="${command== 'show'}" code="administrator.authorization.form.button.update"
		action="/administrator/authorization/update" />
		
	<acme:form-submit test="${command == 'update'}" code="administrator.authorization.form.button.update"
		action="/administrator/authorization/update"/>
	</jstl:if>
	<acme:form-return code="administrator.authorization.form.button.return" />
</acme:form>
