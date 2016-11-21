<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../common/treeCreation.jsp" />

<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	$(function() {
		var tree = $('#tree1');
		
		createTree(tree, '<c:url value="/consulta/tratamiento/tree" />', false);
		
		$('#noNuevoTratamientoButton').click(function(){
			$('#nuevoTratamientoModal').modal('hide');
			$('#tratamientoModal').modal('hide');
		})
		
	    $('#tratamientoForm').ajaxForm({
        	beforeSerialize: function() { 
    			$('#nodotratamientoId').val(tree.tree('getSelectedNode').id);
    			$('#submitButton').attr('disabled','disabled');
        	},
	        success: function(){
	        	//Función que se ejecuta en el contenedor de este modal
	        	afterTratamientoCreation();
	        	
	        	$('#nuevoTratamientoModal').modal();
	        	
				//Reset al estado del árbol y al form
				var state = new Object();
				state['open_nodes'] = new Array();
				state['selected_node'] = 1;			
				tree.tree('setState', state);
				$('#nodotratamientoId').val("");
				$('#submitButton').removeAttr('disabled');
	        },
	        error: function(response) {
	            alert(response.statusText);
	            $('#submitButton').removeAttr('disabled');
	        }
	    });			

    });
</script>

<spring:url value="/consulta/${consulta.id}/tratamiento" var="form_url" />

<div class="modal-content">
	<form:form action="${form_url}" method="POST" id="tratamientoForm" commandName="tratamiento" role="form">
		<div class="modal-header">
			<h3 id="myModalLabel">Tratamiento</h3>
		</div>	
		<div class="modal-body">

			<form:input type="hidden" id="nodotratamientoId" path="tratamiento.id"/>
			
			<div class="form-group">
				<label for="miembro">Miembro</label>
				<form:select path="miembro" class="form-control">
					<form:option value="MSD" >Miembro superior derecho</form:option>
					<form:option value="MSI" >Miembro superior izquierdo</form:option>
				</form:select>
			</div>
			
			<div class="panel panel-default" style="height: 400px; overflow: auto">
				<div class="panel-heading">Tratamientos</div>
				<div id="tree1" class="panel-body"></div>
			</div>
			
		</div>
		<div class="modal-footer">
			<input id="submitButton" type="submit" class="btn btn-primary noLiberar" value="Guardar"/>
			<a id="closeModalButton" data-dismiss="modal" class="btn btn-default">Cancelar</a>
		</div>
	</form:form>
</div>

<div id="nuevoTratamientoModal" class="modal" tabindex="-1" style="display: none;" data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<h4 class="modal-title">¿Desea agregar otro tratamiento?</h4>
	</div>
	<div class="modal-body pull-right" >
		<button data-dismiss="modal" type="button" class="btn btn-primary">Si</button>
		<button id="noNuevoTratamientoButton" type="button" class="btn btn-default">No</button>
	</div>
</div>

