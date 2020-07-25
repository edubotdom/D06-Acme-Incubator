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
	<acme:form-url code="anonymous.notice.form.label.header" path="header"/>
	<acme:form-textbox code="anonymous.notice.form.label.title" path="title"/>
	<acme:form-moment code="anonymous.notice.form.label.creation" path="creation"/>
	<acme:form-moment code="anonymous.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="anonymous.notice.form.label.body" path="body"/>
	<acme:form-url code="anonymous.notice.form.label.related" path="related"/>
	
  	<acme:form-return code="anonymous.notice.form.button.return"/>
</acme:form>
