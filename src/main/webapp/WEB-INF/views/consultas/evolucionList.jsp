<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>
    $(function() {
		$('#masEvolucionesButton').click(function(e){
			e.preventDefault();
			var maxResult = $('#evolucionesTable tr').size() + 2;
		    $.get('${consulta.id}/evolucion?maxResult='+maxResult, function(html) {
			    $('#evoluciones #panel-body').html(html);
		    });
		});
    })
</script>

<c:choose>
	<c:when test="${empty evoluciones}">
		<p>No hay evoluciones anteriores</p>
	</c:when>
	<c:otherwise>
		<table id="evolucionesTable" class="table table-hover">
			<c:forEach items="${evoluciones}" var="evolucion">
				<tr>
					<fmt:formatDate value="${evolucion.fecha}" var="dateString" pattern="dd/MM/yyyy" />
					<td>${dateString }</td>
					<td>${evolucion.texto}</td>
				</tr>
			</c:forEach>
		</table>
		<sec:authorize access="hasRole('ADMINISTRAR_EVOLUCION')">
			<c:if test="${fn:length(evoluciones) lt fn:length(consulta.evoluciones) }">
				<a href="#" id="masEvolucionesButton" class="btn btn-info btn-block">Mostrar más</a>
			</c:if>
		</sec:authorize>
	</c:otherwise>
</c:choose>