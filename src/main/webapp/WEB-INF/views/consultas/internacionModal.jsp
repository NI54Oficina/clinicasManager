<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/additional-methods.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>

	$(function() {

		$('#internacionModal').modal('show');

		$('#internacionForm').ajaxForm({
			beforeSubmit : function() {
				return $('#internacionForm').valid();
			},
			success : function() {
				$('#internacionModal').modal('hide');
			},
			error : function() {
				alert('Hubo un error');
			}
		});

		$('#internacionForm').validate({
			rules : {
				fechaSalida : {
					required : true,
					greaterThan : "#fechaEntrada"
				}
			}
		});
	})
</script>

<spring:url value="/consulta/${consulta.id}/internacion/${internacion.id}" var="form_internacion_url" />

<!-- Internación Modal -->
<div id="internacionModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  	<div class="modal-content">
  		<div class="modal-header">
    		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      		<h3 class="modal-title">Internación programada</h3>
  		</div>
  		<div class="modal-body">
			<form action="${form_internacion_url}" method="POST" id="internacionForm">
				<div class="form-group">
					<label class="control-label">Fecha de internación</label>
					<div class="input-group">
						<fmt:formatDate value="${internacion.fechaEntrada}" var="dateString" pattern="dd/MM/yyyy" />
						<p id="fechaEntrada">${dateString}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Fecha de salida</label>
					<div class="input-group date">
						<input name="fechaSalida" id="fechaSalida" class="form-control" type="text" /> <span class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer"> 
	        <button type="submit" form="internacionForm" class="btn btn-primary">Internación finalizada</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">Internación continúa</button>
		</div>
	</div><!-- /.modal-content -->
</div>