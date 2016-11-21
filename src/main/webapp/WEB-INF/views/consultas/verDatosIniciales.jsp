<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="liberarConsulta.jsp" />

<div class="panel panel-default">
	<div class="panel-heading">Interrogatorio</div>
	<div class="panel-body">${datosIniciales.interrogatorio}</div>
</div>

<div class="panel panel-default">
	<div class="panel-heading">Internaciones previas</div>
	<div class="panel-body">${datosIniciales.internacionesPrevias}</div>
</div>
<p>
	<b>Fecha accidente: </b> ${datosIniciales.fechaAccidente}
</p>
<p>
	<b>Miembro: </b> ${datosIniciales.miembro.name}
</p>
<p>
	<b>Traumático: </b>
	<c:choose>
		<c:when test="${datosIniciales.traumatico}">Si</c:when>
		<c:otherwise>No</c:otherwise>
	</c:choose>
</p>
<c:if test="${not empty datosIniciales.mecanismo}">
	<p><b>Mecanismo:</b> ${datosIniciales.mecanismo.name} </p>
	<p><b>Lugar accidente: </b> ${datosIniciales.lugarAccidente.name}</p>
</c:if>

<a href='<c:url value="?form"/>' class="btn btn-primary noLiberar">Editar</a>
<a href='<c:url value="/consulta/${consulta.id}"/>' class="btn btn-default noLiberar allowInReadOnly">Volver</a>