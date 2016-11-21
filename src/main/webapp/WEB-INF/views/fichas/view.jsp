<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="page-header">
	<h2>Ficha: ${ficha.nombre}</h2>
</div>

<ul class="list-group">
	<c:forEach items="${ficha.preguntas}" var="pregunta" >
		<li class="list-group-item">
			<p><b>${pregunta.pregunta}</b></p>
			<ul class="list-inline">
				<c:forEach items="${pregunta.opciones}" var="opcion">
					${opcion} -
				</c:forEach>
			</ul>
		</li>
	</c:forEach>
</ul>

<a href="<c:url value='/ficha'/>" class="btn btn-default">Volver</a>
