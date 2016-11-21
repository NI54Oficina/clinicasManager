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
			location = '<c:url value='/consulta/${consulta.id}/diagnostico'/>';
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
			
			$('#diagnosticoForm').attr('action', updatedFormAction);
		});		
		
		setUpDelete('Eliminar lesión', '¿Está seguro que desea eliminar esta lesión?');
	})	
</script>

<jsp:include page="../common/modalBootstrapPlugin.jsp" />
<jsp:include page="../consultas/liberarConsulta.jsp" />
<jsp:include page="../common/deleteWithConfirmation.jsp" />

<div class="page-header">
	<h2>Modificar diagnóstico</h2>
</div>

<div>
	<h4>Lesiones</h4>
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
					<td>${lesion.fullName}</td>
					<c:if test="${not consulta.estado.sinDiagnostico }">
						<sec:authorize access="hasRole('ELIMINAR_LESION')">
							<td><a href="<c:url value='/consulta/${consulta.id}/diagnostico/${lesion.id}'/>" class="btn btn-default eliminarButton noLiberar">
								<span class="glyphicon glyphicon-remove"></span> Remover lesión
							</a></td>
						</sec:authorize>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a id="buttonDiagnostico" href="<c:url value='/consulta/${consulta.id}/diagnostico?form'/>" class="btn btn-primary" data-target="#diagnosticoModal">Agregar lesión</a>
	<a id="buttonVolver" href="<c:url value='/consulta/${consulta.id}/diagnostico'/>" class="btn btn-default noLiberar allowInReadOnly">Volver</a>
</div>

<!-- Diagnóstico Modal -->
<div id="diagnosticoModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
</div>