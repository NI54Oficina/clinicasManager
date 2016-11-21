<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script type="text/javascript">

	$(function(){
		$('#respuestasFichaForm').validate();
	})
</script>

<jsp:include page="../consultas/liberarConsulta.jsp" />

<div class="page-header">
	<h2>Ficha: ${respuestaFicha.ficha.nombre}</h2>
</div>

<spring:url value="/consulta/${consulta.id}/ficha/responder" var="form_url" />

<form:form method="POST" commandName="respuestaFicha" action="${form_url}" id="respuestasFichaForm">

	<form:hidden path="ficha.id" value="${respuestaFicha.ficha.id}"/>

	<c:forEach items="${respuestaFicha.ficha.preguntas}" var="pregunta" varStatus="status">
		<div class="form-group">
			<label class="control-label">${pregunta.pregunta }</label>
			<input type="hidden" name="respuestasPregunta[${status.index}].fichaPregunta.id" value="${pregunta.id}" />
			<c:choose>
				<c:when test="${not empty pregunta.opciones}">
					<br />
					<c:choose>
						<c:when test="${pregunta.multiplesRespuestas}">
							<c:forEach items="${pregunta.opciones}" var="opcion">
								<label class="checkbox">
									<input type="checkbox" class="required" name="respuestasPregunta[${status.index}].respuesta" value="${opcion}">${opcion}
								</label>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${pregunta.opciones}" var="opcion">
								<label class="radio-inline">
									<input type="radio" class="required" name="respuestasPregunta[${status.index}].respuesta" value="${opcion}">${opcion}
								</label>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<input type="text" name="respuestasPregunta[${status.index}].respuesta" class="form-control required" />
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
	
	<div>
		<input type="submit" value="Guardar" class="btn btn-primary noLiberar" />
		<a href="<c:url value='/consulta/${consulta.id}/fichaRespuesta'/>" class="btn btn-default noLiberar">Volver</a>  
	</div>	
</form:form>