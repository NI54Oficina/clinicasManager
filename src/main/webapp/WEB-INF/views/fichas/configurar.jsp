<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../common/treeCreation.jsp" />

<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	$(function() {
		
		var tree = $('#tree1');
		
		createTree(tree, '<c:url value="/consulta/diagnostico/tree" />', true);
		
		$('#configuracionFichaForm').validate();   		
		
		$('#configuracionFichaForm').submit(function(){
			var nodes = $('#tree1').tree('getSelectedNodes');
			var ids = '';
			$.each(nodes, function( index, value ) {
				  ids = ids + value.id + ' ';
			});

			$('#nodosSeleccionados').val(ids);
			
			if(ids.length > 0 && ids != 'undefined'){
				return true;
			}
			
			$('#patologiaPanel').addClass('panel-danger')
			
			return false;
		})
		
    });
</script>

<div class="page-header">
	<h2>Configurar ficha</h2>
</div>

<spring:url value="/ficha/configurar" var="form_url" />
<div class="col-xs-6 col-xs-offset-2">
	<form:form commandName="configuracionFicha" action="${form_url}" method="POST" id="configuracionFichaForm">
	
		<form:hidden path="ficha.id"/>
		
		<input type="hidden" id="nodosSeleccionados" name="nodosSeleccionados"/>
			
		<div class="form-group">
			<label for="semanas">Semanas</label>
			<form:input id="semanas" path="periodoSemanas" class="form-control required digits"/>
		</div>
		
		<div id="patologiaPanel" class="panel panel-default" style="height: 400px; overflow: auto">
			<div class="panel-heading">Patología</div>
			<div id="tree1" class="panel-body"></div>
		</div>
		<span class="help-block">Selección múltiple permitida.</span>	
		
		<div>	
			<input id="submitButton" type="submit" class="btn btn-primary" value="Guardar"/>
			<a href="<c:url value='/ficha'/>" class="btn btn-default">Volver</a>
		</div>	
	</form:form>
</div>