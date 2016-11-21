<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>	
	$(function() {
		if(!readOnly){
			$('#cirugiaModal').modal('show');
		}
	})
</script>

<!-- Cirugía Modal -->
<div id="cirugiaModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  	<div class="modal-content">   
  		<div class="modal-header">
  			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  			<h3>¿Fue realizada la siguiente cirugía?</h3>
  		</div>   			
  		<div class="modal-body">
			<fmt:formatDate value="${cirugiaPendiente.fechaCirugia}" var="dateString" pattern="dd/MM/yyyy" />
			<p>Fecha: ${dateString}</p>
			<p>Lugar: ${cirugiaPendiente.lugar} </p>
			<p>Procedimiento: ${cirugiaPendiente.fullName } </p>
			<p>Descripción: ${cirugiaPendiente.descripcion} </p>
		</div>
		<div class="modal-footer"> 
	        <a href="<c:url value='/consulta/${consulta.id}/cirugia/${cirugiaPendiente.id}/confirmar'/>" class="btn btn-primary noLiberar">Confirmar</a>
	        <a href="<c:url value='/consulta/${consulta.id}/cirugia/${cirugiaPendiente.id}'/>" class="btn btn-primary noLiberar">Editar</a>
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
  		</div>
	</div><!-- /.modal-content -->
</div>