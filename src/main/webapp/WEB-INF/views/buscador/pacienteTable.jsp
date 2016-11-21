<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script src="<c:url value='/resources/js/jquery.tablesorter.min.js'/>" type="text/javascript"></script>

<jsp:include page="../common/deleteWithConfirmation.jsp" />
<jsp:include page="../common/modalBootstrapPlugin.jsp" />

<script>
	$(function(){
		$("#pacientesTable").tablesorter(  );
		
		setUpDelete('Eliminar paciente', '¿Está seguro que desea eliminar el paciente? Se eliminarán todas las consultas asociadas.');

	})
</script>

<table class="table table-striped table-bordered table-sortable" id="pacientesTable" class="tablesorter">
    <thead>
        <tr>
            <th style="width: 12%">DNI</th>
            <th>Nombre</th>
            <th style="width: 6%">Edad</th>
            <th>Sexo</th>
            <th>Cobertura</th>
            <th>Ocupación</th>
            <th style="width: 10%"></th>
        </tr>
    </thead>
    <tbody>
   		<c:choose>
    		<c:when test="${not empty pacientes}">
		    	<c:forEach items="${pacientes}" var="paciente">
		    		<tr>
			            <td>${paciente.dni}</td>
			            <td>${paciente.nombre}</td>
			            <td>${paciente.edad}</td>
			            <td>${paciente.sexo.name}</td>
			            <td>${paciente.cobertura.name}</td>
			            <td>${paciente.ocupacion.name}</td>
			            <td>
			            	<a href="<c:url value='/pacientes/${paciente.dni}'/>" class="btn btn-default btn-xs">
			            		<img title="Mostrar Paciente" src="<c:url value='/images/show.png' />"  />
			            	</a>
			            	<sec:authorize access="hasRole('EDITAR_DATOS_FILIATORIOS')">
				            	<a href="<c:url value='/pacientes/${paciente.id}?form'/>" class="btn btn-default btn-xs">
				            		<img title="Editar Paciente" src="<c:url value='/images/update.png' />"  />
				            	</a>
			            	</sec:authorize>
			            	<sec:authorize access="hasRole('ELIMINAR_PACIENTE')">
				            	<a class="btn btn-default btn-xs eliminarButton" href="<c:url value='/pacientes/${paciente.id}'/>">
				            		<img title="Eliminar Paciente" src="<c:url value='/images/delete.png' />"  />
				            	</a>
			            	</sec:authorize>
			            </td>
		    		</tr>
		    	</c:forEach>
    		</c:when>
    		<c:otherwise>
    			<tr><td colspan="7" align="center">No hay resultados</td></tr>
    		</c:otherwise>
    	</c:choose>
    </tbody>
</table>


