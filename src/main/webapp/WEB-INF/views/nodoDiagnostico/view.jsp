<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="page-header">
	<h2>Editar nodo</h2>
</div>

<div>
	<form:form commandName="nodo" method="POST">
		<form:input path="id" readonly="true"/>
		
		<form:input path="label"/>
		
		<form:input path="displayInSumamry"/>

		<form:button>Guardar</form:button>		
	</form:form>
</div>