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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.technology.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.technology.form.label.sector" path="sector"/>
	<acme:form-textbox code="authenticated.technology.form.label.inventor" path="inventor"/>
	<acme:form-textarea code="authenticated.technology.form.label.description" path="description"/>
	<acme:form-url code="authenticated.technology.form.label.website" path="website"/>
	<acme:form-url code="authenticated.technology.form.label.contact" path="contact"/>
	<acme:form-textbox code="authenticated.technology.form.label.source" path="source"/>
<%-- 	<acme:form-select code="authenticated.technology.form.label.source" path="source"> --%>
<%-- 		<acme:form-option code="Open source" value="open-source"/> --%>
<%-- 		<acme:form-option code="Closed source" value="closed-source"/> --%>
<%-- 	</acme:form-select> --%>
	<acme:form-double code="authenticated.technology.form.label.stars" path="stars"/>
	
  	<acme:form-return code="authenticated.technology.form.button.return"/>
</acme:form>