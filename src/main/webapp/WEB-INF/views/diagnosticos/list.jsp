<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script src="<c:url value='/resources/js/bootstrap-modalmanager.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/bootstrap-modal.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<link rel="stylesheet" href="<c:url value='/resources/styles/bootstrap-modal-bs3patch.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/styles/bootstrap-modal.css'/>">

<script type="text/javascript">
	var diagnosticoCreated = false;
	
	function afterDiagnosticoCreation(reload){
		//$('#diagnosticoTable tr:last').after('<tr>...</tr><tr>...</tr>');
		diagnosticoCreated = true;
		if(reload) {
			location.reload();
		}
	}

	$(function(){		    	    
		$('#diagnosticoModal').on('hidden.bs.modal', function (e) {
			if(diagnosticoCreated){
				location.reload();
			}
		});
		
		var buttonDiagnostico = $('#buttonDiagnostico');
		$('#diagnosticoModal').load(buttonDiagnostico.attr('href'), '', function(){});
		
		$('#buttonDiagnostico').on('click', function(e){
			e.preventDefault(); 
			var button = $(this);

			var modal = $(button.data('target'));
			modal.modal();
		});		
		
		setUpDelete('Eliminar diagnóstico', '¿Está seguro que desea eliminar el diagnóstico completo?');
	})	
</script>

<jsp:include page="../common/modalBootstrapPlugin.jsp" />
<jsp:include page="../consultas/liberarConsulta.jsp" />
<jsp:include page="../common/deleteWithConfirmation.jsp" />

<div class="page-header">
	<h2>Detalle diagnósticos</h2>
</div>

<div>
	<h3>Lesiones</h3>
	<p>Fecha del diagnóstico: <b>${consulta.diagnostico.fechaDiagnostico}</b></p>
	<table id="diagnosticoTable" class="table">
		<thead>
			<tr>
				<th>Miembro</th>
				<th>Patología</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${consulta.diagnostico.lesiones}" var="lesion">
				<tr>
					<td>${lesion.miembro}</td>
					<td>${lesion.fullName} </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value='/consulta/${consulta.id}/diagnostico?form'/>" class="btn btn-primary" data-target="#diagnosticoModal" id="buttonDiagnostico">Nuevo diagnóstico</a>
	<c:if test="${not consulta.estado.sinDiagnostico }">
		<a href="<c:url value='/consulta/${consulta.id}/diagnostico/edit'/>" class="btn btn-primary noLiberar">Modificar</a>
		<a href="<c:url value='/consulta/${consulta.id}/diagnostico'/>" class="btn btn-danger eliminarButton noLiberar">Eliminar</a>
	</c:if>
	<a href="<c:url value='/consulta/${consulta.id}'/>" class="btn btn-default noLiberar allowInReadOnly">Volver</a>
</div>

<c:if test="${not empty consulta.diagnosticosAnteriores}">
	<div style="margin-top: 80px">
		<h3>Diagnósticos anteriores</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Resumen diagnóstico</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${consulta.diagnosticosAnteriores}" var="diagnostico">
					<tr>
						<td>${diagnostico.fechaDiagnostico}</td>
						<td>${diagnostico.resumen}</td>
						<td><a href="<c:url value='/consulta/${consulta.id}/diagnostico/anterior/${diagnostico.id}'/>" class="btn btn-default eliminarButton noLiberar">Eliminar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

<!-- Diagnóstico Modal -->
<div id="diagnosticoModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
</div>