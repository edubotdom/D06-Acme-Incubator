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

	<acme:form-moment code="anonymous.botiaBulletin.form.label.expiring_date" path="expiring_date"/>
	<acme:form-textbox code="anonymous.botiaBulletin.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.botiaBulletin.form.label.title" path="title"/>
	<acme:form-textarea code="anonymous.botiaBulletin.form.label.text_body" path="text_body"/>
	
	<acme:form-submit code="anonymous.botiaBulletin.form.button.create" action="/anonymous/botia_bulletin/create"/>
  	<acme:form-return code="anonymous.botiaBulletin.form.button.return"/>
</acme:form>
