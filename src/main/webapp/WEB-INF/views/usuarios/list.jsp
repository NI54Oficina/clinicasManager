<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div version="2.0">
	<div class="container">
		<div class="page-header">
			<h1>Lista usuarios</h1>
		</div>
	</div>
	<div
		id="_title_pl_com_snoopconsulting_antecedentes_domain_Usuario_id">
		
		<table class="table table-stripped">
			<thead>
				<tr>
					<th>Username</th>
					<th>Nombre</th>					
					<th>Email</th>	
					<th>Es usuario de gmail</th>				
					<th />
					<th />
					<th />
				</tr>
			</thead>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.username }</td>
					<td>${usuario.nombre }</td>					
					<td>${usuario.email }</td>
					<td>${usuario.usuarioGoogle }</td>
					<td class="utilbox">
						<a title="Mostrar Usuario" alt="Mostrar Usuario" href="<c:url value="/usuarios/${usuario.id}"/>">
							<img title="Mostrar Usuario" src="images/show.png" class="image" alt="Mostrar Usuario" />
						</a>
					</td>
					<td class="utilbox">
						<a title="Actualizar Usuario" alt="Actualizar Usuario" href="<c:url value="/usuarios/${usuario.id}?form"/>">
							<img title="Actualizar Usuario" src="images/update.png" class="image" alt="Actualizar Usuario" />
						</a>
					</td>
					<td class="utilbox">
						<spring:url value="/usuarios/${usuario.id}" var="form_delete"/>
						<form id="command" action="${form_delete}" method="post">
							<input type="hidden" name="_method" value="DELETE" />
							<input onclick="return confirm('Estas seguro que quieres eliminar este elemento');"
								value="Borrar Usuario" type="image" title="Borrar Usuario" src="images/delete.png"
								class="image" alt="Borrar Usuario" />
						</form>
					</td>
				</tr>
			</c:forEach>
			<tr class="footer">
				<td colspan="9">
					<span class="new">
						<a href="<c:url value="/usuarios?form"/> "><img title="Crear nuevo Usuario"
							src="images/add.png" alt="Crear nuevo Usuario" /></a>
					</span>
				</td>
			</tr>
		</table>
	</div>
</div>