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
	
	<jstl:if test="${command != 'create' }">
 	<acme:form-textbox code="authenticated.forum.form.label.titleName" path="titleName" readonly = "true"/>
	<acme:form-textbox code="authenticated.forum.form.label.creatorName" path="creatorName" readonly = "true"/>
	
	
	<acme:form-return code="authenticated.forum.form.button.messageList" action= "${direccion}"/>
	<acme:form-return code="authenticated.forum.form.button.message.create" action="${forumCreateMessage}" />
	<jstl:if test="${forumProppetary}">
	<acme:form-return code="authenticated.forum.form.button.message.listarUsuario" action="${direccionListarUsuario}" />
	<acme:form-return code="authenticated.forum.form.button.message.anadirUsuario" action="${direccionAnadirUsuario}" />
	</jstl:if>
	</jstl:if>
	
	<acme:form-hidden path="roundId"/>
	<acme:form-hidden path="forumProppetary"/>
	
	<acme:form-submit test="${command=='create'}" code="authenticated.forum.form.button.create" action="${createSubmit}" />

	<acme:form-return code="authenticated.forum.form.button.return" />
</acme:form>
