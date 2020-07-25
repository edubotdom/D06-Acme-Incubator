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
	<acme:form-textbox code="administrator.inquiry.form.label.title" path="title"/>
	
	<jstl:if test="${command != 'create' }">
	<acme:form-moment code="administrator.inquiry.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	
	<acme:form-moment code="administrator.inquiry.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.inquiry.form.label.description" path="description"/>
	<acme:form-money code="administrator.inquiry.form.label.money" path="money"/>
	<acme:form-email code="administrator.inquiry.form.label.contact" path="contact"/>
	
		<acme:form-submit test="${command == 'create'}" code="administrator.inquiry.form.button.create"
		action="/administrator/inquiry/create"/>	
		
		<acme:form-submit test="${command!= 'create'}" code="administrator.inquiry.form.button.update"
		action="/administrator/inquiry/update" />
		
		<acme:form-submit test="${command!= 'create'}" code="administrator.inquiry.form.button.delete"
		action="/administrator/inquiry/delete" />
	
  	<acme:form-return code="administrator.inquiry.form.button.return"/>
</acme:form>
