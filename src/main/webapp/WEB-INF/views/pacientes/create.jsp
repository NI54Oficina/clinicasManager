<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	$(function(){
		$('#pacienteForm').validate();
		
		$('#obraSocialDiv').hide();
		
		$('#cobertura').change(function() {
			if($('#cobertura').val() == 'OS') {
				$('#obraSocialDiv').show();
			}
			else{
				$('#obraSocialDiv').hide();
			}
		});
	})
</script>

<spring:url value="/pacientes" var="form_url"/>

<c:choose>
	<c:when test="${empty paciente.id }">
		<c:set var="title" value="Nuevo paciente" />
		
		<div class="alert alert-warning">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <h4>Atención!</h4>
		  El DNI ingresado no corresponde a ningún paciente existente. Se procederá a la creación del mismo...
		</div>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Editar paciente" />
	</c:otherwise>
</c:choose>

<div class="page-header">
	<h1>${title}</h1>
</div>

<div class="col-xs-12">
	<form:form action="${form_url }" id="pacienteForm" method="post" commandName="paciente" role="form" class="form-horizontal">
		
		<form:hidden path="id"/>
	
		<div class="form-group">
			<label for="dni" class="col-xs-2 control-label">DNI</label>
			<div class="col-xs-4">
				<form:input path="dni" id="dni" class="form-control required digits"/>
			</div>
		</div>
		<div class="form-group">
			<label for="nombre" class="col-xs-2 control-label">Nombre</label>
			<div class="col-xs-4">
				<form:input path="nombre" id="nombre" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label for="edad" class="col-xs-2 control-label">Edad</label>
			<div class="col-xs-4">
				<form:input path="edad" id="edad" class="form-control required digits"/>
			</div>	
		</div>
		<div class="form-group">
			<label for="sexo" class="col-xs-2 control-label">Sexo</label>
			<div class="col-xs-4">
				<form:select path="sexo" id="sexo" items="${sexos}" itemLabel="name" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="telefono" class="col-xs-2 control-label">Teléfono</label>
			<div class="col-xs-4">
				<form:input path="telefono" id="telefono" class="form-control required"/>
			</div>
		</div>		
		<div class="form-group">
			<label for="cobertura" class="col-xs-2 control-label">Cobertura</label>
			<div class="col-xs-4">
				<form:select path="cobertura" id="cobertura" items="${coberturas}" itemLabel="name" class="form-control"/>
			</div>
		</div>
		<div class="form-group" id="obraSocialDiv">
			<label for="obraSocial" class="col-xs-2 control-label">Obra Social</label>
			<div class="col-xs-4">
				<form:input path="obraSocial" id="obraSocial" class="form-control"/>
			</div>
		</div>		
		<div class="form-group">
			<label for="numeroSocio" class="col-xs-2 control-label">Número de asociado</label>
			<div class="col-xs-4">
				<form:input path="numeroSocio" id="numeroSocio" class="form-control digits"/>
			</div>
		</div>
		<div class="form-group">
			<label for="ocupacion" class="col-xs-2 control-label">Ocupación</label>
			<div class="col-xs-4">
				<form:select path="ocupacion" id="ocupacion" items="${ocupaciones}" itemLabel="name" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-10">
				<input type="submit" value="Guardar" class="btn btn-primary" />
				<a href="<%= request.getHeader("referer") %>" class="btn btn-default">Volver</a>
			</div>
		</div>
	</form:form>
</div>
