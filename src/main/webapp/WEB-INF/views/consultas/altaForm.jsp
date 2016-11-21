<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href="<c:url value="/resources/styles/bootstrap-datetimepicker.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.es.js" />"></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	function loadScoresResumen(){
		$.get('<c:url value="/consulta/${consulta.id}/score/resumen"/>', function(html){
			$('#scoresResumen').html(html);
		});
	}
	
	function completarEmailTemplate(){
		$('#emailMensaje').append('Alta (' + $('#altaFecha').val() + '): \n');
		$('#emailMensaje').append($('#altaTexto').val());
	}

	$(function(){
		$('.date').datetimepicker({
	    	autoclose: true,
	    	language: 'es',
	    	format : 'dd/mm/yyyy',
	    	minView: 2
	    });
		
		$('#altaForm').validate();
		
		loadScoresResumen();
		
		$('#enviarMail').click(function(e){
			e.preventDefault();
			loadModal($('#enviarMail'), completarEmailTemplate);
		});	
		
	});

</script>

<jsp:include page="../common/modalBootstrapPlugin.jsp" />
<jsp:include page="liberarConsulta.jsp" />

<div class="page-header">
	<h2>Alta</h2>
</div>

<spring:url value="/consulta/${consulta.id}/alta" var="form_url" />

<div class="col-xs-6">
	<form:form action="${form_url}" method="POST" commandName="form" id="altaForm">
		
		<div class="form-group">
			<label class="control-label">Fecha de alta</label>
			<div class="input-group date">
			    <form:input path="fecha" readonly="true" id="altaFecha" type="text" class="form-control required" value="" />
			    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
			</div>
		</div>
		
		<div class="form-group">
		    <form:textarea path="texto" id="altaTexto" type="text" class="form-control required" rows="6" />
		</div>		
		
		<div id="scoresResumen">
		</div>
		
		<input type="submit" class="btn btn-primary" value="Guardar"/>
		<a href="<c:url value="/consulta/${consulta.id}${consulta.estado.segundaVez ? '' : '/datosIniciales?form' }" />" class="btn btn-default noLiberar allowInReadOnly">Volver</a>
		<a href="<c:url value='/consulta/${consulta.id}/crearEmail'/>" data-target="#emailModal" id="enviarMail" 
					class="btn btn-default allowInReadOnly"><span class="glyphicon glyphicon-envelope"></span> Enviar mail</a>
	</form:form>
	
</div>
<div class="col-xs-4 col-xs-offset-1"> 
	<div class="panel panel-default">
		<div class="panel-heading">Scores</div>
		<div class="panel-body">
			<a href="<c:url value='/consulta/${consulta.id}/score/quickDash'/>" class="btn btn-default btn-block modalAjax allowInReadOnly" data-target="#quickDashModal">Completar Quick Dash</a>
			<a href="<c:url value='/consulta/${consulta.id}/score/escalaDeDolor'/>" class="btn btn-default btn-block modalAjax allowInReadOnly" data-target="#escalaDeDolorModal">Completar Escala de Dolor</a>
			<a href="<c:url value='/consulta/${consulta.id}/score/michigan'/>" class="btn btn-default btn-block modalAjax allowInReadOnly" data-target="#michiganModal">Completar Brief Michigan</a>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal container" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->

<!-- Modal -->
<div class="modal container" id="quickDashModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->

<!-- Modal -->
<div class="modal" id="escalaDeDolorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->

<!-- Modal -->
<div class="modal container" id="michiganModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->
