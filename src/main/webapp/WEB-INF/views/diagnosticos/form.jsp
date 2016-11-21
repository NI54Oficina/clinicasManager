<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../common/treeCreation.jsp" />

<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	var labelSinDiagnostico = 'Sin diagnostico';
	var updatedFormAction = $('#diagnosticoForm').attr('action') + '/lesion';

	$(function() {
		var tree = $('#tree1');
		
		createTree(tree, '<c:url value="/consulta/diagnostico/tree" />', false);
		
		$('#noNuevaLesionButton').click(function(){
			$('#nuevaLesionModal').modal('hide');
			$('#diagnosticoModal').modal('hide');
		})
		
	    $('#diagnosticoForm').ajaxForm({
        	beforeSerialize: function() { 
    			$('#nodoDiagnosticoId').val(tree.tree('getSelectedNode').id);
    			$('#submitButton').attr('disabled','disabled');
        	},
	        success: function(diagnostico){
				if(tree.tree('getSelectedNode').name === labelSinDiagnostico){
					$('#diagnosticoModal').modal('hide');
					//Función que se ejecuta en el contenedor de este modal
					afterDiagnosticoCreation(true, diagnostico);	
	        	}
				else{
					$('#nuevaLesionModal').modal();
					//Función que se ejecuta en el contenedor de este modal
					afterDiagnosticoCreation(false, diagnostico);	
				}
				
				//Reset al estado del árbol y al form
				var state = new Object();
				state['open_nodes'] = new Array();
				state['selected_node'] = 1;			
				tree.tree('setState', state);
				$('#submitButton').removeAttr('disabled');
				$('#nodoDiagnosticoId').val("");
				
				$('#diagnosticoForm').attr('action', updatedFormAction);
	        },
	        error: function(response) {
	            alert(response.statusText);
	            $('#submitButton').removeAttr('disabled');
	        }
	    });			

    });
</script>

<spring:url value="/consulta/${consulta.id}/diagnostico" var="form_url" />

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 id="myModalLabel">Diagnóstico</h3>
	</div>
	<form:form action="${form_url}" method="POST" id="diagnosticoForm" commandName="lesion" role="form">
		<div class="modal-body">
	
			<form:input type="hidden" id="nodoDiagnosticoId" path="patologia.id"/>
			
			<div class="form-group">
				<label for="miembro">Miembro</label>
				<form:select id="miembroDiagnostico" path="miembro" class="form-control">
					<form:option value="MSD" >Miembro superior derecho</form:option>
					<form:option value="MSI" >Miembro superior izquierdo</form:option>
					<form:option value="BILATERAL" >Bilateral</form:option>
				</form:select>
			</div>
			
			<div class="panel panel-default" style="position: relative; height: 400px; overflow: auto">
				<div class="panel-heading">Patología</div>
				<div id="tree1" class="panel-body"></div>
			</div>
			
		</div>
		<div class="modal-footer">
			<input id="submitButton" type="submit" class="btn btn-primary noLiberar" value="Guardar"/>
			<a id="closeModalButton" data-dismiss="modal" class="btn btn-default">Cancelar</a>
		</div>
	</form:form>
</div>

<div id="nuevaLesionModal" class="modal" tabindex="-1" style="display: none;" data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<h4 class="modal-title">¿Desea agregar otra lesión?</h4>
	</div>
	<div class="modal-body pull-right" >
		<button data-dismiss="modal" type="button" class="btn btn-primary">Si</button>
		<button id="noNuevaLesionButton" type="button" class="btn btn-default">No</button>
	</div>
</div>

