<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/styles/dataTables.bootstrap.css" var="dataTablesBootstrapCss" />
<spring:url value="/resources/js/jquery.dataTables.min.js" var="dataTables" />
<spring:url value="/resources/js/dataTables.bootstrap.js" var="dataTablesBootstrap" />
<style type="text/css">
    @import "${dataTablesBootstrapCss}";
</style>

<div id="consultasDiv">

	<jsp:include page="../common/deleteWithConfirmation.jsp" />
	<jsp:include page="../common/modalBootstrapPlugin.jsp" />
	
	<script src="<c:url value='/resources/js/jquery.tablesorter.min.js'/>" type="text/javascript"></script>
	<script>
		$(function(){
			
			$('a[data-toggle="tooltip"]').tooltip();
			
			setUpDelete('Eliminar consulta', '¿Está seguro que desea eliminar esta consulta?');
			
			$('.emailButton').click(function(e){
				e.preventDefault();
				//Destruyo el modal para que vuelva a ser cargado
				$('#emailModal').html('');
				loadModal($(this));
			});	
	    })
	</script>
	
	<table id="consultasTable" class="table table-striped table-bordered table-sortable">
	    <thead>
	        <tr>
	            <th style="width: 18%">Paciente</th>
	            <th style="width: 14%">Fecha primera consulta</th>
	            <th style="width: 40%">Diagnóstico</th>
	            <th style="width: 16%">Estado</th>
	            <th style="width: 12%"></th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:choose>
	    		<c:when test="${not empty consultas}">
			    	<c:forEach items="${consultas}" var="consulta">
			    		<tr>
				            <td>${consulta.paciente.nombre} (DNI: ${consulta.paciente.dni})</td>
				            <fmt:formatDate value="${consulta.fechaPrimerConsulta}" var="dateString" pattern="dd/MM/yyyy" />
				            <td>${dateString}</td>
				            <td>${consulta.diagnostico.resumen}</td>
				            <td>
				            	<c:choose>
					            	<c:when test="${consulta.estado.dadoDeAlta}">
					            		<fmt:formatDate value="${consulta.alta.fecha}" var="dateString" pattern="dd/MM/yyyy" />
					            		ALTA ${dateString}
					            	</c:when>
					            	<c:otherwise>ACTIVA</c:otherwise>
				            	</c:choose>
				            </td>
				            <td><a href="<c:url value='/consulta/${consulta.id}'/>" class="btn btn-default btn-xs" data-toggle="tooltip" title="Ver">
				            	<span class="glyphicon glyphicon-zoom-in"></span></a>
				            	<a class="btn btn-default btn-xs emailButton" href="<c:url value='/consulta/${consulta.id}/crearEmail/full'/>" data-target="#emailModal" 
				            			data-toggle="tooltip" title="Enviar mail">
			            			<span class="glyphicon glyphicon-envelope"></span></a>
				            	<sec:authorize access="hasRole('ELIMINAR_CONSULTAS')">
				            		<a href="<c:url value='/consulta/${consulta.id}'/>" class="btn btn-default btn-xs eliminarButton" data-toggle="tooltip" title="Eliminar">
				            		<span class="glyphicon glyphicon-remove"></span></a>
			            		</sec:authorize>	
				            </td>
			    		</tr>
			    	</c:forEach>
	    		</c:when>
	    		<c:otherwise>
	    			<tr><td colspan="6" align="center">No hay resultados</td></tr>
	    		</c:otherwise>
	    	</c:choose>
	    </tbody>
	</table>
	
	<sec:authorize access="hasRole('CREAR_CONSULTA')">
		<c:if test="${not empty paciente.dni }">
			<a href="<c:url value='/consulta/crearConsulta?dni=${paciente.dni}'/>" class="btn btn-default">Nueva consulta</a>
		</c:if>
	</sec:authorize>
	
	<!-- Modal -->
	<div class="modal container" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->
	
	<c:if test="${not empty consultas}">
		<script type="text/javascript" charset="utf-8" src="${dataTables}" ></script>
		<script type="text/javascript" charset="utf-8" src="${dataTablesBootstrap}" ></script>
		<script type="text/javascript"> 
		jQuery.extend( jQuery.fn.dataTableExt.oSort, {
			"date-uk-pre": function ( a ) {
			    var ukDatea = a.split('/');
			    return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
			},
		
			"date-uk-asc": function ( a, b ) {
			    return ((a < b) ? -1 : ((a > b) ? 1 : 0));
			},
		
			"date-uk-desc": function ( a, b ) {
			    return ((a < b) ? 1 : ((a > b) ? -1 : 0));
			}
		} );
	
	    $(document).ready(function() {
	 
	        var oTable = $('#consultasTable').dataTable( {
	            "aLengthMenu": [[10, 25, 50, 100, -1], [ 10, 25, 50, 100, "All"]],
	            "iDisplayLength": 25,
	            "oLanguage": {
	                "sSearch": "Buscar en todas las columnas:",
	                "sLengthMenu": "_MENU_ filas por página",
	                "sEmptyTable": "No hay datos para mostrar",
	                "sZeroRecords": "No hay datos para mostrar",
	                "sInfo": "_TOTAL_ entradas(mostrando de _START_ a _END_)",
	                "sInfoFiltered": " - filtrado a partir de _MAX_ entradas",
	                "sInfoEmpty": "No hay entradas",
	                "oPaginate": {
	                    "sNext": "Siguiente",
	                    "sPrevious": "Anterior",
	                    "sFirst": "Principio",
	                    "sLast": "Fin"
	                }
	            },
	            "sPaginationType": "bootstrap",
	            "sDom": "<'row'<'col-xs-5 col-sm-6'l>r>t<'row'<'col-xs-3 col-sm-4 col-md-5'i><'col-xs-9 col-sm-8 col-md-7 text-right'p>>",
	            "aoColumns": [
	                { "mData": "nombre" },
	                { "mData": "fecha", "sType": "date-uk" },
	                { "mData": "diagnostico", "bSortable" : false },
	                { "mData": "estado" },
	                { "bSortable" : false }
	            ],
	            "aoColumnDefs" : [ 
	                {
	                    "aTargets": [1],
	                }
	            ]
	        });      
	        
	        $("tfoot input").keyup( function () {
	            oTable.fnFilter( this.value, $("tfoot input").index(this) );
	        } );
	        
	        
	    });
		</script>	
	</c:if>
</div>

