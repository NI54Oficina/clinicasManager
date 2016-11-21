<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script type="text/javascript"> 

	function completarEmailTemplate(){
		$('#emailMensaje').append('Plan: \n');
		$('#emailMensaje').append($('#textoPlan').val());
	}

	$(function(){
		$('#planForm').validate();
		
		
		$('#enviarMail').click(function(e){
			e.preventDefault();
			loadModal($('#enviarMail'), completarEmailTemplate);
		});
	});
</script>

<spring:url value="/consulta/${idConsulta}/guardarPlan" var="form_url" />

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 id="myModalLabel">Plan a seguir</h3>
	</div>
	<div class="modal-body">
		<form:form action="${form_url}" method="POST" id="planForm" commandName="form">

			<div class="form-group">
				<label class="control-label" for="interrogatorio">Plan</label>
				<form:textarea path="texto" id="textoPlan" class="form-control required" rows="6" />
			</div>

			<input type="submit" class="btn btn-primary" value="Guardar"/>
			<a href="<c:url value='/consulta/${idConsulta}/crearEmail'/>" data-target="#emailModal" id="enviarMail" 
					class="btn btn-default allowInReadOnly"><span class="glyphicon glyphicon-envelope"></span> Enviar mail</a>
		</form:form>
	</div>
</div>

<!-- Modal -->
<div class="modal container" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->
