<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../common/treeCreation.jsp" />

<link href="<c:url value="/resources/styles/bootstrap-datetimepicker.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.es.js" />"></script>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>

	var idCirugia;
	var me = this;
	
	function siguienteAccion() {
		window.location.href = '<c:url value="/"/>'
	}
	
	function completarEmailTemplate(){
		$('#emailMensaje').append('Última cirugía: \n');
		$('#emailMensaje').append('Programada para el: ' + $('#fechaCirugia').val() + "\n");
		$('#emailMensaje').append($('#descripcion').val());
	}
	
	$(function(){
		$('.date').datetimepicker({
			format : 'dd/mm/yyyy HH:ii',
	    	autoclose: true,
	    	language: 'es'
	    });
		
		$('#enviarMail').click(function(e){
			e.preventDefault();
			loadModal($('#enviarMail'), completarEmailTemplate);
		});
		
		createTree($('#tree1'), '<c:url value="/consulta/tratamiento/tree" />', false, $('#nodoTratamientoId').val());
		
        $('#cirugiaForm').ajaxForm({
        	beforeSerialize: function() { 
        		var node = $('#tree1').tree('getSelectedNode');
    			$('#nodoTratamientoId').val(node.id);                 
        	},
        	beforeSubmit: function() {
	            return $('#cirugiaForm').valid();
            },
            success: function(data) {
            	idCirugia = data;
	            siguienteAccion();
            },
            error: function(response) {
            	if(response.status == 400) {
            		alert("Valores ingresados inválidos o incompletos");
            	}
            	else {
	            	alert("La consulta ya posee una cirugía programada");
            	}
            }
        });
        
        setUpDelete('Cancelar cirugía', '¿Está seguro que desea cancelar?', function(){
        	window.location.href = '<c:url value="/consulta/${consulta.id}"/>'
        });
	});
</script>

<jsp:include page="liberarConsulta.jsp" />
<jsp:include page="../common/deleteWithConfirmation.jsp" />
<jsp:include page="../common/modalBootstrapPlugin.jsp" />

<div class="page-header">
	<h2>Programar cirugía</h2>
</div>

<div class="col-xs-6">
	<spring:url value="/consulta/${consulta.id}/cirugia/${empty cirugia.id ? '' : cirugia.id}" var="form_url" />	
	<form:form action="${form_url}" method="POST" commandName="cirugia" id="cirugiaForm">
	
		<form:input type="hidden" id="nodoTratamientoId" path="tratamiento.id"/>
		<form:input type="hidden" path="id"/>
		
		<div class="form-group">
			<label class="control-label">Fecha de la cirugía</label>
			<div class="input-group date">
			    <form:input path="fechaCirugia" readonly="true" id="fechaCirugia" type="text" class="form-control required" value="" />
			    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label" for="lugar">Lugar</label>	
			<form:input path="lugar" id="lugar" class="form-control required"/>
		</div>
		
		<div class="form-group">
			<label for="descripcion">Descripción</label>	
			<form:textarea path="descripcion" id="descripcion" class="form-control" rows="6"/>
		</div>
		
		<div>	
			<input type="submit" class="btn btn-primary" value="Guardar"/>
			<a href="<c:url value="/consulta/${consulta.id}${consulta.estado.segundaVez ? '' : '/datosIniciales?form' }" />"
					 class="btn btn-default noLiberar">Volver</a>
			<a href="<c:url value='/consulta/${consulta.id}/crearEmail'/>" data-target="#emailModal" id="enviarMail" 
					class="btn btn-default allowInReadOnly"><span class="glyphicon glyphicon-envelope"></span> Enviar mail</a>
			<c:if test="${not empty cirugia.id }">
				<a href="<c:url value='/consulta/${consulta.id}/cirugia/${cirugia.id}'/>" class="btn btn-danger eliminarButton">
					<span class="glyphicon glyphicon-remove"></span> Cancelar cirugía
				</a>		
			</c:if>
		</div>
	</form:form>
</div>

<div class="col-xs-6">
	<div class="panel panel-default">
		<div class="panel-heading">Tratamientos</div>
		<div id="tree1" class="panel-body"  style="max-height: 350px; overflow: auto;"></div>
	</div>
</div>

<!-- Modal -->
<div class="modal container" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->

<c:if test="${empty cirugia.id}">
	<script>
		function siguienteAccion() {
    	    $('#eventModal').modal('show');
		}
		
		function crearEvento(){
			var idCalendario = $('#idCalendario').val();
			$.ajax({
				type: 'POST',
				url: '<c:url value="/consulta/${consulta.id}/cirugia/" />' + idCirugia + '/crearEvento',
				data: { calendarId: idCalendario },
				success: function(){
					window.location.href = '<c:url value="/"/>'
				},
				error: function(xhr, status, error){
					alert ( "Error: " + xhr.responseText );
				}
			});
		}
	</script>

	<div id="eventModal" class="modal fade" tabindex="-1" role="dialog"	aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<h3>¿Crear evento en Google Calendar?</h3>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="idCalendario">Calendario</label>
					<select id="idCalendario" class="form-control">
						<c:forEach items="${calendarios}"  var="calendario">
							<option value="${calendario.id}">${calendario.summary}</option>
						</c:forEach>	
					</select>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="crearEvento()">Crear</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="window.location.href = '<c:url value="/"/>'">No</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</c:if>
