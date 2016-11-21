<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>	
	$(function() {
		$('#escalaDeDolorForm').ajaxForm({
			beforeSubmit: function() {
	            return $('#escalaDeDolorForm').valid();
            },
	        success: function(){
	        	loadScoresResumen();
	        	$('#escalaDeDolorModal').modal('hide');
	        },
	        error: function(response) {
	            alert(response.statusText);
	        }
	    });				
	})
</script>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4>Escala de dolor - ${escalaDeDolorScore }</h4>
	</div>
	<spring:url value="/consulta/${consulta.id}/score/escalaDeDolor" var="escalaDeDolor_form_url" />
	<form action="${escalaDeDolor_form_url}" method="post" id="escalaDeDolorForm">
		<div class="modal-body">
			<img alt="" src="<c:url value='/images/escalaDolor.png'/>" />
			<div class="col-xs-2 col-xs-offset-1" style="padding-left: 0;">
				<input type="radio" name="value" value="0" style="margin-left: -8px" class="required" ${escalaDeDolorScore=='0.0'?'checked':''} />
			</div>
			<div class="col-xs-2" style="padding-left: 0;">
				<input type="radio" name="value" value="2" style="margin-left: -8px" ${escalaDeDolorScore=='2.0'?'checked':''}>
			</div>
			<div class="col-xs-2" style="padding-left: 0;">
				<input type="radio" name="value" value="4" style="margin-left: -8px" ${escalaDeDolorScore=='4.0'?'checked':''}>
			</div>
			<div class="col-xs-2" style="padding-left: 0;">
				<input type="radio" name="value" value="6" style="margin-left: -8px" ${escalaDeDolorScore=='6.0'?'checked':''}>
			</div>
			<div class="col-xs-2" style="padding-left: 0;">
				<input type="radio" name="value" value="8" style="margin-left: -8px" ${escalaDeDolorScore=='8.0'?'checked':''}>
			</div>
			<div class="col-xs-1" style="padding-left: 0;">
				<input type="radio" name="value" value="10" style="margin-left: -8px" ${escalaDeDolorScore=='10.0'?'checked':''}>
			</div>
		</div>
		<div class="modal-footer">
			<input type="submit" class="btn btn-primary" value="Guardar" />
			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		</div>
	</form>
</div>
	<!-- /.modal-content -->		
