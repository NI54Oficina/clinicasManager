<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div version="2.0">
	<div class="page-header">
		<h1>Lista grupos</h1>
	</div>
	<div>		
		<table class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Enabled</th>
					<th />
					<th />
					<th />
				</tr>
			</thead>
			<c:forEach items="${grupoes}" var="grupo">
				<tr>
					<td>${grupo.nombre}</td>
					<td>${grupo.enabled}</td>
					<td class="utilbox">
						<a title="Mostrar Grupo" alt="Mostrar Grupo" href="<c:url value="/grupos/${grupo.id}"/>">
							<img title="Mostrar Grupo" src="images/show.png" class="image" alt="Mostrar Grupo" />
						</a>
					</td>
					<td class="utilbox">
						<a title="Actualizar Grupo"	alt="Actualizar Grupo" href="<c:url value="/grupos/${grupo.id}?form"/>">
							<img title="Actualizar Grupo" src="images/update.png" class="image" alt="Actualizar Grupo" />
						</a>
					</td>
					<td class="utilbox">
						<spring:url value="/grupos/${grupo.id}" var="form_delete" />
						<form id="command" action="${form_delete}" method="post">
							<input type="hidden" name="_method" value="DELETE" />
							<input onclick="return confirm('Estas seguro que quieres eliminar este elemento');"
								value="Borrar Grupo" type="image" title="Borrar Grupo" src="images/delete.png"
								class="image" alt="Borrar Grupo" />
						</form>
					</td>
				</tr>		
			</c:forEach>	
			<tr class="footer">
				<td colspan="6">
					<span class="new">
						<a href="<c:url value="/grupos?form"/> "><img title="Crear nuevo Grupo" src="images/add.png"
							 alt="Crear nuevo Grupo" /></a>
					</span>
				</td>
			</tr>
		</table>
	</div>
</div>