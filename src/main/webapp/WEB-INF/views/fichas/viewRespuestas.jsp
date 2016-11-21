<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../consultas/liberarConsulta.jsp" />

<div class="page-header">
	<h2>Ficha: ${respuestasFicha.ficha.nombre}</h2>
</div>

<c:forEach items="${respuestasFicha.respuestasPregunta}" var="respuesta"> 
	<p><b>${respuesta.fichaPregunta.pregunta}</b></p>
	<p><u>Rta:</u> ${respuesta.respuesta}</p>
</c:forEach>

<a href="<c:url value='/consulta/${consulta.id}/fichaRespuesta'/>" class="btn btn-default noLiberar allowInReadOnly">Volver</a>
