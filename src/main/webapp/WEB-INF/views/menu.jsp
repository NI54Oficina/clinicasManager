<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div id="menu_lateral">
	<div class="panel panel-default">
		<div class="panel-heading">Datos del paciente</div>
		<div class="panel-body" style="padding-left: 5px; padding-right: 5px;">
			<p><b>${consulta.paciente.nombre }</b> </p>
			<p>Edad:<b> ${consulta.paciente.edad}</b> </p>
			<p>Sexo:<b> ${consulta.paciente.sexo.name}</b> </p>
			<p>Cobertura:<b> ${consulta.paciente.cobertura.name }</b> </p> 
			<p>Ocupación:<b> ${consulta.paciente.ocupacion.name }</b> </p>
		</div>
	</div>

	<c:if test="${not empty conBotonera}">
		<div>
			<a href="<c:url value="/pacientes/${consulta.paciente.dni}" />" class="btn btn-default btn-block allowInReadOnly">Ver datos paciente</a>
			<a href="<c:url value="/consulta/${consulta.id}/datosIniciales" />" class="btn btn-default btn-block noLiberar allowInReadOnly">Ver datos iniciales</a>
			<sec:authorize access="hasRole('CREAR_CONSULTA')">
				<a href="<c:url value="/consulta/crearConsulta?dni=${consulta.paciente.dni}" />" class="btn btn-default btn-block allowInReadOnly">Nueva consulta</a>
			</sec:authorize>
			<sec:authorize access="hasRole('USAR_FICHA')">
				<a href="<c:url value="/consulta/${consulta.id}/fichaRespuesta" />" class="btn btn-default btn-block noLiberar allowInReadOnly">
					Usar ficha 
					<c:if test="${cantidadFichas gt 0 }">
						(${cantidadFichas})
					</c:if>
				</a>
			</sec:authorize>
			<sec:authorize access="hasRole('USAR_SCORE')">
				<a href="<c:url value="/consulta/${consulta.id}/score" />" class="btn btn-default btn-block noLiberar allowInReadOnly">
					Usar score 
					<c:if test="${fn:length(consulta.scores) gt 0}">
						(${fn:length(consulta.scores)})
					</c:if>
				</a>
			</sec:authorize>
		</div>
	</c:if>
</div>
