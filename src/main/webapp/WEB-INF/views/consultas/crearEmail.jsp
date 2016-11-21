<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	$(function(){
		$('#mailForm').validate();
		
		$('#mailForm').ajaxForm({
			beforeSerialize: function() { 
    			$('#mailForm input[type="submit"]').attr('disabled','disabled');
        	},
			success: function(){
		    	alert('Mail enviado con éxito');
		    	$('#mailForm input[type="submit"]').removeAttr('disabled');
		    },
		    error: function(response) {
		    	alert(response.statusText);
		    	$('#mailForm input[type="submit"]').removeAttr('disabled');
		    }
		 });
	})
</script>

<spring:url value="/consulta/enviarEmail" var="form_url" />

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>Enviar mail</h3>
	</div>
	<div class="modal-body">
		<form action="${form_url}" method="post" id="mailForm">
			<div class="form-group">
				<label class="control-label" for="emailDestino">Destino</label>	
				<input name="destino" id="emailDestino" class="form-control required email"/>
			</div>
			
			<div class="form-group">
				<label class="control-label" for="emailAsunto">Asunto</label>	
				<input name="asunto" id="emailAsunto" class="form-control required "/>
			</div>
			
			<div class="form-group">
				<label class="control-label" for="emailMensaje">Mensaje</label>	
				<textarea name="mensaje" id="emailMensaje" class="form-control required" rows="10">${emailContent}</textarea>
			</div>
			
			<input type="submit" value="Enviar" class="btn btn-primary"/>
		</form>
	</div>
</div>