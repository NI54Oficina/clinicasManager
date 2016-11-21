<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="page-header">
	<h2>Ver paciente</h2>
</div>

<div class="row">
	<div class="col-xs-4">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-4 control-label">DNI:</label>
				<div class="col-sm-4">
					<p class="form-control-static">${paciente.dni}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Nombre:</label>
				<div class="col-sm-6">
					<p class="form-control-static">${paciente.nombre}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Edad:</label>
				<div class="col-sm-4">
					<p class="form-control-static">${paciente.edad}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Sexo:</label>
				<div class="col-sm-6">
					<p class="form-control-static">${paciente.sexo.name}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Teléfono:</label>
				<div class="col-sm-6">
					<p class="form-control-static">${paciente.telefono}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Ocupación:</label>
				<div class="col-sm-6">
					<p class="form-control-static">${paciente.ocupacion.name}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Cobertura:</label>
				<div class="col-sm-6">
					<p class="form-control-static">${paciente.cobertura.name}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Obra Social:</label>
				<div class="col-sm-6">
					<p class="form-control-static">${paciente.obraSocial}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Número de asociado:</label>
				<div class="col-sm-6">
					<p class="form-control-static">${paciente.numeroSocio}</p>
				</div>
			</div>
		</form>
		
		<div class="col-xs-offset-1">
			<sec:authorize access="hasRole('EDITAR_DATOS_FILIATORIOS')">
				<a href='<c:url value="/pacientes/${paciente.id}?form"/>' class="btn btn-primary">Editar</a>
			</sec:authorize>
			<a href="<%= request.getHeader("referer") %>" class="btn btn-default">Volver</a>
		</div>
	</div>
	
	<div class="col-xs-8">
		<h3>Consultas de <b>${paciente.nombre } (${paciente.dni })</b></h3>
		<jsp:include page="../consultas/list.jsp" />
	</div>
</div>


