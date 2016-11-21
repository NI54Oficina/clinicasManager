<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="<c:url value="/resources/styles/bootstrap-datetimepicker.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.es.js" />"></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	function completarEmailTemplate(){
		$('#emailMensaje').append('Internación: \n');
		$('#emailMensaje').append('Ingreso el: ' + $('#fechaEntradaInternacion').val() + "\n");
		$('#emailMensaje').append($('#motivo').val());
	}

	$(function(){
		$('.date').datetimepicker({
	    	format : 'dd/mm/yyyy',
	    	autoclose: true,
	    	minView: 2,
	    	language: 'es'
	    });
		
		$('#internacionForm').validate();
		
		$('#enviarMail').click(function(e){
			e.preventDefault();
			loadModal($('#enviarMail'), completarEmailTemplate);
		});
	});
	
</script>

<c:set var="form_url">
	<c:url value="/consulta/${consulta.id}/internacion" />
</c:set>

<jsp:include page="liberarConsulta.jsp" />
<jsp:include page="../common/modalBootstrapPlugin.jsp" />

<div class="page-header">
	<h2>Internación</h2>
</div>

<div class="col-xs-offset-2 col-xs-6">
	<form:form action="${form_url}" method="POST" commandName="internacion" id="internacionForm">
		<div class="form-group">
			<label class="control-label">Fecha de internación</label>
			<div class="input-group date">
				<form:input path="fechaEntrada" id="fechaEntradaInternacion" class="form-control required" type="text" /> <span class="input-group-addon"><i
					class="glyphicon glyphicon-calendar"></i></span>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label" for="motivo">Motivo</label>	
			<form:textarea path="motivo" id="motivo" class="form-control required" rows="6"/>
		</div>
		<div>	
			<input type="submit" value="Guardar" class="btn btn-primary" /> 
			<a href="<c:url value="/consulta/${consulta.id}${consulta.estado.segundaVez ? '' : '/datosIniciales?form' }" />"
					 class="btn btn-default noLiberar">Volver</a>
			<a href="<c:url value='/consulta/${consulta.id}/crearEmail'/>" data-target="#emailModal" id="enviarMail" 
					class="btn btn-default allowInReadOnly"><span class="glyphicon glyphicon-envelope"></span> Enviar mail</a>
		</div>
	</form:form>
</div>

<!-- Modal -->
<div class="modal container" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->