<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:if test="${not empty fichasPendientes}">
	<script>
		$(function() {
		    $('#seleccionarFichaButton').click(function() {
			    window.location.href = '<c:url value="/consulta"/>/${consulta.id}/ficha/' + ($('#fichaSeleccionada').val()) + '/responder';
		    });
	
		    $('#fichaModal').modal('show');
	    });
	</script>
	
	<!-- Modal -->
	<div class="modal" id="fichaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Tiene las siguientes fichas pendientes para completar</h4>
			</div>
			<div class="modal-body">
				<select id="fichaSeleccionada" class="form-control">
					<c:forEach items="${fichasPendientes}" var="ficha">
						<option value="${ficha.id}">${ficha.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<button id="seleccionarFichaButton" type="button" class="btn btn-primary noLiberar">Seleccionar</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			</div>
		</div>
	</div>
</c:if>