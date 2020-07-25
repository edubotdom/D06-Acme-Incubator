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
	<acme:form-textbox code="administrator.tool.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.tool.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.tool.form.label.inventor" path="inventor"/>
	<acme:form-textarea code="administrator.tool.form.label.description" path="description"/>
	<acme:form-url code="administrator.tool.form.label.website" path="website"/>
	<acme:form-url code="administrator.tool.form.label.contact" path="contact"/>
	
	<jstl:if test="${command == 'create' && source==null || source=='open-source'}">
 	<acme:form-select code="administrator.tool.form.label.source" path="source"> 
 		<acme:form-option code="Open source" value="open-source" selected="true"/> 
		<acme:form-option code="Closed source" value="closed-source"/> 
	</acme:form-select>
	</jstl:if>
	<jstl:if test="${source=='closed-source'}">
 	<acme:form-select code="administrator.tool.form.label.source" path="source"> 
 		<acme:form-option code="Open source" value="open-source"/> 
		<acme:form-option code="Closed source" value="closed-source" selected="true"/> 
	</acme:form-select>
	</jstl:if>

	
	<acme:form-double code="administrator.tool.form.label.stars" path="stars"/>
	
	<acme:form-submit test="${command == 'create'}" code="administrator.tool.form.button.create"
		action="/administrator/tool/create"/>	
		
	<acme:form-submit test="${command!= 'create'}" code="administrator.tool.form.button.update"
		action="/administrator/tool/update" />
		
	<acme:form-submit test="${command!= 'create'}" code="administrator.tool.form.button.delete"
		action="/administrator/tool/delete" />
	
  	<acme:form-return code="administrator.tool.form.button.return"/>
</acme:form>
