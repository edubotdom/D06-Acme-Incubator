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

	<acme:form-textbox code="entrepreneur.accounting.form.label.title" path="title" />
	<acme:form-textarea code="entrepreneur.accounting.form.label.body" path="body" />
	<acme:form-moment code="entrepreneur.accounting.form.label.creation" path="creation" readonly="true"/>
	<acme:form-textbox code="entrepreneur.accounting.form.label.entrepreneur"  path="bookkeeperUsername" readonly="true" />
	<acme:form-textbox code="entrepreneur.accounting.form.label.round" path="roundTicker" readonly="true"/>
	
	
	<jstl:if test="${status}">
	<acme:form-select code="entrepreneur.accounting.form.label.status" path="status" >
		<acme:form-option code="entrepreneur.accounting.form.label.status.published" value="1"/>
		<acme:form-option code="entrepreneur.accounting.form.label.status.draft" value="0"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${!status}">
	<acme:form-select code="entrepreneur.accounting.form.label.status" path="status" >
		<acme:form-option code="entrepreneur.accounting.form.label.status.draft" value="0"/>
		<acme:form-option code="entrepreneur.accounting.form.label.status.published" value="1"/>
	</acme:form-select>
	</jstl:if>

	
		
	<acme:form-return code="entrepreneur.accounting.form.button.return" />
</acme:form>
