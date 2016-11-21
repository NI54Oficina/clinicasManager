<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="form_url">
	<c:url value="/pacientes/nuevoPaciente" />
</c:set>

<div style="position: absolute; left: 45%; top: 20%">
	<form action="${form_url}" method="get" role="form" class="form-inline">
		<div class="form-group">
			<label for="dni" class="sr-only" >Ingrese DNI del paciente:</label>
			<input name="dni" class="form-control" placeholder="Ingrese dni..." />
		</div>
		<input type="submit" class="btn btn-default" value="Siguiente"> 
	</form>
</div>