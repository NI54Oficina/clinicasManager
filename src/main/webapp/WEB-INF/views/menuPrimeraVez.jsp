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

	<div>
		<a href="<c:url value="/pacientes/${consulta.paciente.dni}" />" class="btn btn-default btn-block allowInReadOnly">Ver datos paciente</a>
		<sec:authorize access="hasRole('USAR_FICHA')">
			<a href="<c:url value="/consulta/${consulta.id}/fichaRespuesta" />" class="btn btn-default btn-block noLiberar allowInReadOnly">
				Usar ficha 
				<c:if test="${fn:length(consulta.respuestasFichas) gt 0}">
					(${fn:length(consulta.respuestasFichas)})
				</c:if>
			</a>
		</sec:authorize>
	</div>
</div>
