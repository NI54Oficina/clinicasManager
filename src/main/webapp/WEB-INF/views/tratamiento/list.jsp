<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
	var tratamientoCreated = false;
	
	function afterTratamientoCreation(){
		tratamientoCreated = true;
	}

	$(function(){
		setUpDelete('Eliminar tratamiento', '¿Está seguro que desea eliminar este elemento?');
		
		$('#tratamientoModal').on('hidden.bs.modal', function (e) {
			if(tratamientoCreated){
				location.reload();
			}
		});
	})	
</script>

<jsp:include page="../common/modalBootstrapPlugin.jsp" />
<jsp:include page="../consultas/liberarConsulta.jsp" />
<jsp:include page="../common/deleteWithConfirmation.jsp" />

<div class="page-header">
	<h2>Tratamientos</h2>
</div>

<c:choose>
	<c:when test="${empty consulta.tratamientos}">
		<p>No hay tratamientos realizados</p>
	</c:when>
	<c:otherwise>
		<div>
			<table id="tratamientoTable" class="table">
				<thead>
					<tr>
						<th>Miembro</th>
						<th>Tratamiento</th>
						<th>Fecha inicio</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${consulta.tratamientos}" var="tratamiento">
						<tr>
							<td>${tratamiento.miembro}</td>
							<td>${tratamiento.fullName}</td>
							<fmt:formatDate value="${tratamiento.fechaInicioTratamiento}" var="dateString" pattern="dd/MM/yyyy" />
							<td>${dateString}</td>
							<sec:authorize access="hasRole('ELIMINAR_TRATAMIENTO')">
								<td><a href="<c:url value='/consulta/${consulta.id}/tratamiento/${tratamiento.id}'/>" class="btn btn-default eliminarButton">
									<span class="glyphicon glyphicon-remove"></span> Remover tratamiento
								</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:otherwise>
</c:choose>
<a href="<c:url value='/consulta/${consulta.id}/tratamiento?form'/>" class="btn btn-primary modalAjax" data-target="#tratamientoModal">Agregar tratamiento</a>

<a href="<c:url value="/consulta/${consulta.id}${consulta.estado.segundaVez ? '' : '/datosIniciales?form' }" />" class="btn btn-default noLiberar allowInReadOnly">Volver</a>

<c:if test="${not empty consulta.cirugias}">
	<br /><br /><br />
	<div class="page-header">
		<h2>Cirugías</h2>	
	</div>
	<div>
		<table id="tratamientoTable" class="table">
			<thead>
				<tr>
					<th>Procedimiento</th>
					<th>Descripción</th>
					<th>Fecha</th>
					<th>Lugar</th>
					<th>Estado</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${consulta.cirugias}" var="cirugia">
					<tr>
						<td>${cirugia.fullName}</td>
						<td>${cirugia.descripcion}</td>
						<fmt:formatDate value="${cirugia.fechaCirugia}" var="dateString" pattern="dd/MM/yyyy HH:mm" />
						<td>${dateString}</td>
						<td>${cirugia.lugar}</td>
						<td>${cirugia.estado.name}</td>
						<td>
							<sec:authorize access="hasRole('ADMINISTRAR_CIRUGIA')">
								<c:choose>
									<c:when test="${cirugia.estado == 'PENDIENTE' }">
										<a href="<c:url value='/consulta/${consulta.id}/cirugia/${cirugia.id}'/>" class="btn btn-primary">
											<span class="glyphicon glyphicon-edit"></span> Editar cirugia
										</a>
									</c:when>
									<c:otherwise>
										<a href="<c:url value='/consulta/${consulta.id}/cirugia/${cirugia.id}'/>" class="btn btn-default eliminarButton">
											<span class="glyphicon glyphicon-remove"></span> Remover cirugia
										</a>
									</c:otherwise>
								</c:choose>
							</sec:authorize>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</</c:if>

<!-- Tratamiento Modal -->
<div id="tratamientoModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
</div>