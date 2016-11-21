<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div version="2.0">
	<div class="container">
		<div class="page-header">
			<h1>Ver grupo</h1>
		</div>
	</div>	
	<div id="_title_ps_com_snoopconsulting_antecedentes_domain_Grupo_id">
		<div class="container" >
			<label class="span2" for="_nombre_id">Nombre : </label>
			<div class="box span2">${grupo.nombre}</div>
		</div>
		<br />
		<div class="container"
			id="_s_com_snoopconsulting_antecedentes_domain_Grupo_permisosUsuarios_id">
			<label class="span2" for="_permisosUsuarios_id">Permisos Usuarios : </label>
			<div class="box span2">
				<c:forEach items="${grupo.permisosUsuarios}" var="permiso">
					${permiso}
				</c:forEach>
			</div>
		</div>
		<br />
		<div class="container">
			<label class="span2" for="_enabled_id">Enabled : </label>
			<div class="box span2">
				${grupo.enabled}
			</div>
		</div>
		<br />
		<div class="quicklinks form-actions submit container">
			<span class="span1">
				<spring:url value="/grupos/${grupo.id}" var="form_delete"/>
				<form id="command" action="${form_delete}" method="post">
					<input type="hidden" name="_method" value="DELETE" />
					<input	onclick="return confirm('Estas seguro que quieres eliminar este elemento');"
						value="Borrar Grupo" type="image" title="Borrar Grupo" src="../images/delete.png" 
						class="image" alt="Borrar Grupo" />
				</form>
			</span>
			
			<span class="span1">
				<a title="Actualizar Grupo" alt="Actualizar Grupo" href="<c:url value="/grupos/${grupo.id}?form"/>">
					<img title="Actualizar Grupo" src="../images/update.png" class="image"	alt="Actualizar Grupo" />
				</a>
			</span>
				
			<span class="span1">
				<a	title="Crear nuevo Grupo" alt="Crear nuevo Grupo" href="<c:url value="/grupos?form"/>">
					<img title="Crear nuevo Grupo" src="../images/create.png" class="image" alt="Crear nuevo Grupo" />
				</a>
			</span>
				
			<span class="span1">
				<a title="Listado de Grupos" alt="Listado de Grupos" href="<c:url value="/grupos"/> ">
					<img title="Listado de Grupos"	src="../images/list.png" class="image"	alt="Listado de Grupos" />
				</a>
			</span>
		</div>
	</div>
</div>