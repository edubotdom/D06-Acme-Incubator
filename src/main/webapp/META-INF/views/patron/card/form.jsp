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
	<acme:form-textbox code="patron.card.form.label.holder" path="holder"/>
	<acme:form-textbox code="patron.card.form.label.number" path="number"/>
	<acme:form-textbox code="patron.card.form.label.brand" path="brand"/>
	<acme:form-textbox code="patron.card.form.label.cvv" path="cvv"/>
	
	<acme:form-hidden path="id_banner"/>
	<acme:form-hidden path="id_patron"/>	
	
	<acme:form-submit test="${command == 'create'}" code="patron.card.form.button.create"
		action="/patron/card/create"/>	
		
	<acme:form-submit test="${command == 'associate'}" code="patron.card.form.button.create"
		action="/patron/card/associate"/>		
		
	<acme:form-submit test="${command!= 'create' && command!= 'associate'}" code="patron.card.form.button.update"
		action="/patron/card/update" />
		
	<acme:form-submit test="${command!= 'create' && command!= 'associate'}" code="patron.card.form.button.delete"
		action="/patron/card/delete" />
	
  	<acme:form-return code="patron.card.form.button.return"/>
</acme:form>
