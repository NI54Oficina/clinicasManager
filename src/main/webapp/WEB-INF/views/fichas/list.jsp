<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">

	$(function(){
		setUpDelete('Eliminar ficha', '¿Está seguro que desea eliminar esta ficha?');
	});

</script>

<jsp:include page="../common/deleteWithConfirmation.jsp" />
<jsp:include page="../common/modalBootstrapPlugin.jsp" />

<div class="page-header">
	<h2>Fichas</h2>
</div>

<table class="table">
	<thead>
		<tr>
			<th>Nombre</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${fichas}" var="ficha">
			<tr>
				<td>${ficha.nombre}</td>
				<td>
					<div class="btn-group">
						<a href="<c:url value='/ficha/${ficha.id}'/>" type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-zoom-in"></span> Ver
						</a>
						<a href="<c:url value='/ficha/${ficha.id}?form'/>" type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-edit"></span> Editar
						</a>
						<a href="<c:url value='/ficha/${ficha.id}'/>" class="btn btn-default eliminarButton">
							<span class="glyphicon glyphicon-remove"></span> Eliminar
						</a>
						<sec:authorize access="hasRole('CONFIGURAR_FICHAS')">
							<a href="<c:url value='/ficha/${ficha.id}/configurar'/>" class="btn btn-default">
								<span class="glyphicon glyphicon-cog"></span> Configurar
							</a>	
							<a href="<c:url value='/ficha/${ficha.id}/configuraciones'/>" class="btn btn-default">
								<span class="glyphicon glyphicon-list"></span> Ver configuraciones existentes
							</a>
						</sec:authorize>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="<c:url value='/ficha?form'/>"  class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> Crear ficha</a>