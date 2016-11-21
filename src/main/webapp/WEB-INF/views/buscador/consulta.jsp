<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../common/treeCreation.jsp" />
<link href="<c:url value="/resources/styles/bootstrap-datetimepicker.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.es.js" />"></script>

<style>
	.panel-body-tree{
		height: 225px;
		overflow: auto;
	}
	.panel-body, .panel-footer{
		padding-bottom: 10px;
		padding-top: 10px;
	}
	.form-group {
		margin-bottom: 0px;
	}
</style>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	var ajaxLoaderHtml = '<img id="ajaxLoader" src="<c:url value='/resources/img/ajax-loader.gif'/>" class="ajaxLoader" />';

	$(function(){
		
		var form = $('#buscadorConsultas');
		
		form.ajaxForm({
        	beforeSerialize: function() { 
    			$('#nodoDiagnosticoId').val(getSelectedNodesAsString('#tree1'));  
    			$('#nodoTratamientoId').val(getSelectedNodesAsString('#tree2'));                 
        	},
        	beforeSubmit: function() {
        		$('#resultsTable').html(ajaxLoaderHtml);
        		history.pushState(null, null, form.attr('action') + '?' + form.serialize());
        	},
		    success: function(data){
		    	var div = $(data).find('#consultasDiv');
		    	$('#resultsTable').html(div);
		    	$('html, body').animate({
                    scrollTop: $("#resultsTable").offset().top
                }, 500);
		    },
		    error: function(response) {
		        alert(response.statusText);
		    }
		});			
		
		createTree($('#tree1'), '<c:url value="/consulta/diagnostico/tree" />', true, null, true);
		createTree($('#tree2'), '<c:url value="/consulta/tratamiento/tree" />', true, null, true);
		
		$('#comparacionEdad').change(function() {
			var value = $('#comparacionEdad').val();
			if(value === 'BETWEEN') {
				$('#edadMax').show();
			}
			else {
				$('#edadMax').hide();
			}
		});
		
		$('.date').datetimepicker({
			format : 'dd/mm/yyyy',
	    	autoclose: true,
	    	minView: 2,
	    	language: 'es'
	    });
		
    });
	
	function getSelectedNodesAsString(tree) {
		var nodes = $(tree).tree('getSelectedNodes');
		var ids = '';
		$.each(nodes, function( index, value ) {
			  ids = ids + value.id + ' ';
		});	
		
		return ids;
	}
</script>

<div class="page-header">
	<h2>Buscador de consultas</h2>
</div>

<spring:url value="/buscar/consulta" var="form_url" />

<form:form commandName="form" class="form-horizontal" role="form" id="buscadorConsultas" action="${form_url }">
	<div class="row">	
		<input type="hidden" id="nodoTratamientoId" name="idTratamiento" /> 
		<input type="hidden" id="nodoDiagnosticoId" name="idDiagnostico" />

		<div class="col-xs-6">
			<div class="panel panel-default">
				<div class="panel-heading">Patología</div>
				
				<div id="tree1" class="panel-body panel-body-tree"></div>
				
				<div class="panel-footer">
					<div class="form-group">
						<label class="col-xs-4">Múltiples selecciones</label>
						<div class="col-xs-8">
							<form:select path="operadorLesiones" class="form-control">
								<option value="AND">Intersección (And)</option>
								<option value="OR">Unión (Or)</option>
							</form:select>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-xs-6">
			<div class="panel panel-default" >
				<div class="panel-heading">Tratamientos</div>
				
				<div id="tree2" class="panel-body panel-body-tree"></div>
				
				<div class="panel-footer">
					<div class="form-group">
						<label class="col-xs-4">Múltiples selecciones</label>
						<div class="col-xs-8">
							<form:select path="operadorTratamientos" class="form-control">
								<option value="AND">Intersección (And)</option>
								<option value="OR">Unión (Or)</option>
							</form:select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">		
		<div class="col-xs-12">
		<div class="panel panel-default">
			<div class="panel-heading">Paciente</div>
				<div class="panel-body">
					<div class="col-xs-2">
						<div class="form-group">
							<label class="col-xs-4" for="comparacionEdad">Edad</label>	
							<div class="col-xs-8">
								<form:select path="paciente.comparacionEdad" id="comparacionEdad" class="form-control">
									<form:options items="${comparaciones}" itemLabel="value"/>
								</form:select>
							</div>
						</div>
					</div>
					
					<div class="col-xs-2">
						<div class="form-group">			
							<div class="col-xs-6">
								<form:input path="paciente.edad" id="edad" class="form-control" placeholder="Edad"/>
							</div>
							<div class="col-xs-6">
								<form:input path="paciente.edadMax" id="edadMax" class="form-control" style="display: none" placeholder="Max"/>
							</div>
						</div>
					</div>
					
					<div class="col-xs-2">
						<form:select path="paciente.sexo" id="sexo" class="form-control">
							<option value="" selected>Sexo</option>
							<form:options items="${sexos}" itemLabel="name"/>
						</form:select>
					</div>
				
					<div class="col-xs-2">
						<form:select path="paciente.cobertura" id="cobertura" class="form-control">
							<option value="" selected>Cobertura</option>
							<form:options items="${coberturas}" itemLabel="name"/>
						</form:select>
					</div>
					
					<div class="col-xs-3">
						<form:input path="paciente.obraSocial" id="obraSocial" class="form-control" placeholder="Obra Social"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">		
		<div class="col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">Fecha primera consulta</div>
				
				<div class="panel-body">
					<div class="form-group">
						<label class="col-xs-1" for="comparacionEdad">Desde</label>		
						<div class="col-xs-2">
							<div class="input-group date">
							    <form:input path="fechas.primerConsultaFrom" id="primerConsultaFrom" type="text" class="form-control" value="" />
							    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
						
						<label class="col-xs-offset-1 col-xs-1" for="comparacionEdad">Hasta</label>
						<div class="col-xs-2">
							<div class="input-group date">
								<form:input path="fechas.primerConsultaTo" id="primerConsultaTo" type="text" class="form-control" value="" />
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<button id="buscarButton" class="btn btn-primary btn-block">Buscar</button>
</form:form>

<div id="resultsTable" class="resultadosBusqueda">
	<c:if test="${not empty busquedaRealizada}">
		<jsp:include page="../consultas/list.jsp" />
	</c:if>
</div>
	
