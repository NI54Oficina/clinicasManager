<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value="/resources/styles/bootstrap-datetimepicker.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.es.js" />"></script>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
var successFunction;

function afterDiagnosticoCreation(reload, diagnostico){
	$('#buttons a,#tratamientoButton,#addMediaButton').removeAttr('disabled');
	$('#diagnosticoResumen .panel-body').html(diagnostico.resumen);
	
	$.get("fichasPendientes", function( data ) {
		$( "#main" ).append( data );
	});
}

function toggleSelectsVisibility(){
	$("#traumatico").is(":checked") ? $('#mecanismo_select, #lugar_select').show() : $('#mecanismo_select, #lugar_select').hide();  
}

$(function(){
	
	var buttonDiagnostico = $('#buttonDiagnostico');
	var buttonPlan = $('#buttonPlan');
	
	$('#diagnosticoModal').load(buttonDiagnostico.attr('href'), '', function(){});
	$('#planModal').load(buttonPlan.attr('href'), '', function(){});
	
	$('#buttonDiagnostico, #buttonPlan').on('click', function(e){
		e.preventDefault(); 
		var button = $(this);

		successFunction = function(){
			var modal = $(button.data('target'));
			modal.modal();
		};
		
		$('#primeraVezForm').submit();
	});
	
    $('#primeraVezForm').ajaxForm({
    	beforeSubmit: function() {
            return $('#primeraVezForm').valid();
        },
        success: function(){
        	successFunction();
        },
        error: function(response) {
            alert(response.statusText);
        }
    });	
    
    $('#primeraVezForm').validate({
        rules: {
	        mecanismo: {
				emptyIf: true	        	
	        },
	        lugarAccidente: {
	        	emptyIf: true
	        }
        }
    });    
	
	$('.redirect').click(function(e){
		e.preventDefault();  
		var button = $(this);
		successFunction = function(){
			window.location.href = button.attr('href');
		}
		$('#primeraVezForm').submit();
	});
	
	$('.date').datetimepicker({
    	format : 'dd/mm/yyyy',
    	autoclose: true,
    	minView: 2,
    	language: 'es'
    });
	
	$('#traumatico').change(toggleSelectsVisibility);
	
	toggleSelectsVisibility();
	
	<c:if test="${empty consulta.diagnostico}">
		$('#buttons a, #tratamientoButton').attr('disabled','true');
	</c:if>
	
	$("#sinFechaAccidente").change(function(){
		$('#fechaAccidente').attr('disabled', $(this).is(":checked"));
	})
	
	$('#fechaAccidente').attr('disabled', $("#sinFechaAccidente").is(":checked"));
	
})
</script>

<jsp:include page="../common/modalBootstrapPlugin.jsp" />
<jsp:include page="liberarConsulta.jsp" />

<div class="page-header">
	<h2>Nueva consulta</h2>
</div>

<c:if test="${not empty fichasPendientes}">
	<jsp:include page="fichaModal.jsp" />
</c:if>	

<spring:url value="/consulta/${consulta.id}/save" var="form_url" />

<form:form action="${form_url}" commandName="form" method="POST" id="primeraVezForm" role="form">

	<div class="row">
		<div class="col-xs-4">
			<div class="form-group">
				<label class="control-label" for="fechaAccidente">Fecha del accidente</label>
				<div class="input-group date">
					<form:input path="fechaAccidente" id="fechaAccidente" class="form-control required" type="text" value="" />
					<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>
				<div class="checkbox">
					<label for="sinFechaAccidente">
						<input type="checkbox" id="sinFechaAccidente" ${consulta.datosIniciales != null and consulta.datosIniciales.fechaAccidente == null ? 'checked' : ''} />
						Sin fecha 
					</label>
				</div>
			</div>
			
			<div class="form-group">
				<label for="miembro">Miembro</label>
				<form:select path="miembro" items="${miembros}" itemLabel="name"  id="miembro" class="form-control" />
			</div>

			<div class="form-group">
				<div class="checkbox">
					<label for="traumatico">
						<form:checkbox path="traumatico" id="traumatico" />
						<b>Traumático</b>
					</label>
				</div>
			</div>
			
			<div id="mecanismo_select" class="form-group">
				<label class="control-label" for="mecanismo">Mecanismo</label>
				<form:select path="mecanismo" id="mecanismo" class="form-control" >
					<form:option value="" label="Seleccione..." />
	   				<form:options items="${mecanismos}" itemLabel="name" />
				</form:select>
			</div>
			
			<div id="lugar_select" class="form-group">
				<label class="control-label" for="lugarAccidente">Lugar del accidente</label>
				<form:select path="lugarAccidente" id="lugarAccidente" class="form-control" >
					<form:option value="" label="Seleccione..." />
					<form:options items="${lugares}" itemLabel="name" />
				</form:select>
			</div>	
			
			<div class="form-group">
				<label for="internacionesPrevias">Internaciones previas</label>
				<form:textarea path="internacionesPrevias" id="internacionesPrevias" rows="5" class="form-control"/>
			</div>	
			
			<div id="diagnosticoResumen" class="panel panel-default" >
				<div class="panel-heading">Diagnóstico</div>
				<div class="panel-body">${consulta.diagnostico.resumen }</div>
				<sec:authorize access="hasRole('ADMINISTRAR_DIAGNOSTICO')">
					<a id="buttonDiagnostico" href="<c:url value="/consulta/${consulta.id}/diagnostico?form" />" class="btn btn-default btn-block"
						data-target="#diagnosticoModal" >Agregar</a>
				</sec:authorize>
			</div>
			
			<sec:authorize access="hasRole('ADMINISTRAR_TRATAMIENTO')">
				<a href="<c:url value="/consulta/${consulta.id}/tratamiento?from=datosIniciales" />" class="btn btn-default redirect btn-block noLiberar" 
					id="tratamientoButton">Tratamiento</a>
			</sec:authorize>
		</div>
		<div class="col-xs-8"> 
			<div class="form-group">
				<label for="interrogatorio" class="control-label">Interrogatorio</label>	
				<form:textarea path="interrogatorio" id="interrogatorio" rows="9" class="form-control required"/>
			</div>
			
			<div>
				<h4>Fotos</h4>
				<div id="mediaElements">
					<jsp:include page="mediaList.jsp" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<div id="buttons" class="pull-right">
	<sec:authorize access="hasRole('ADMINISTRAR_ALTA')">
		<a href="<c:url value="/consulta/${consulta.id}/alta?form" />" class="btn btn-default redirect noLiberar">Alta</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMINISTRAR_CIRUGIA')">
		<a href="<c:url value="/consulta/${consulta.id}/cirugia?form" />" class="btn btn-default redirect noLiberar">Cirugía</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMINISTAR_INTERNACION')">
		<a href="<c:url value="/consulta/${consulta.id}/internacion?form" />" class="btn btn-default redirect noLiberar">Internar</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMINISTRAR_PLAN')">
		<a href="<c:url value="/consulta/${consulta.id}/plan?form" />"  data-target="#planModal" class="btn btn-default" id="buttonPlan">Plan</a>
	</sec:authorize>
</div>

<!-- Plan Modal -->
<div id="planModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
</div>

<!-- Diagnóstico Modal -->
<div id="diagnosticoModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
</div>
