
<%--
- list.jsp
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

<acme:list readonly="true">
	<acme:list-column code="anonymous.botiaBulletin.list.label.author" path="author" width="15%"/>
	<acme:list-column code="anonymous.botiaBulletin.list.label.expiring_date" path="expiring_date" width="15%"/>
	<acme:list-column code="anonymous.botiaBulletin.list.label.title" path="title" width="15%"/>
	<acme:list-column code="anonymous.botiaBulletin.list.label.text_body" path="text_body" width="55%"/>	
</acme:list>


