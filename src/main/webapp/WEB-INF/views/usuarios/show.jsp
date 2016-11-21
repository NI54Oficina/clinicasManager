<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div version="2.0">
	<div class="container">
		<div class="page-header">
			<h1>Ver usuario</h1>
		</div>
	</div>
	<div id="_title_ps_com_snoopconsulting_antecedentes_domain_Usuario_id">
		<div class="container">
			<label class="span2" for="_username_id">Username : </label>
			<div class="box span2">${usuario.username }</div>
		</div>	
		<br />
		<div class="container">
			<label class="span2" for="_nombre_id">Nombre : </label>
			<div class="box span2">${usuario.nombre }</div>
		</div>
		<br />
		<div class="container">
			<label class="span2" for="_enabled_id">Enabled : </label>
			<div class="box span2">${usuario.enabled }</div>
		</div>
		<br />
		<div class="container">
			<label class="span2" for="_grupos_id">Grupos : </label>
			<div class="box span2">
				<c:forEach items="${usuario.grupos}" var="grupo">
					${grupo.nombre }
				</c:forEach>
			</div>
		</div>
		<br />
		<div class="container">
			<label class="span2" for="_email_id">Email : </label>
			<div class="box span2">${usuario.email }</div>
		</div>

		<br />
		<div class="quicklinks form-actions submit container">
			<span class="span1">
				<spring:url value="/usuarios/${usuario.id}" var="delete_form" />
				<form id="command" action="${delete_form}" method="post">
					<input type="hidden" name="_method" value="DELETE" />
					<input onclick="return confirm('Estas seguro que quieres eliminar este elemento');"
						value="Borrar Usuario" type="image" title="Borrar Usuario" src="../images/delete.png"
						class="image" alt="Borrar Usuario" />
				</form>
			</span>
				
			<span class="span1">
				<a title="Actualizar Usuario" alt="Actualizar Usuario" href="<c:url value="/usuarios/${usuario.id}?form"/> ">
					<img title="Actualizar Usuario" src="../images/update.png" class="image" alt="Actualizar Usuario" />
				</a>
			</span>
					
			<span class="span1">
				<a title="Crear nuevo Usuario" alt="Crear nuevo Usuario" href="<c:url value="/usuarios?form"/> ">
					<img title="Crear nuevo Usuario" src="../images/create.png" class="image"	alt="Crear nuevo Usuario" />
				</a>
			</span>
					
			<span class="span1">
				<a title="Listado de Usuarios" alt="Listado de Usuarios" href="<c:url value="/usuarios"/> ">
					<img title="Listado de Usuarios" src="../images/list.png" class="image"
						alt="Listado de Usuarios" />
				</a>
			</span>
		</div>
	</div>
</div>