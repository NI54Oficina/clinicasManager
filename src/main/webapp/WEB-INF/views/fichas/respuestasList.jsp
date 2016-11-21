<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="<c:url value="/resources/styles/bootstrap-datetimepicker.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.es.js" />"></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<spring:url value="/resources/styles/dataTables.bootstrap.css" var="dataTablesBootstrapCss" />
<spring:url value="/resources/js/jquery.dataTables.min.js" var="dataTables" />
<spring:url value="/resources/js/dataTables.bootstrap.js" var="dataTablesBootstrap" />
<style type="text/css">
    @import "${dataTablesBootstrapCss}";
</style>
<script type="text/javascript">

	$(function(){
		setUpDelete('Eliminar Respuesta', '¿Está seguro que desea eliminar esta respuesta?');
		
		$('#seleccionarFichaButton').click(function(){
			window.location.href = '<c:url value="/consulta"/>/${consulta.id}/ficha/' + ($('#fichaSeleccionada').val()) + '/responder';
		});
	});
</script>

<jsp:include page="../common/modalBootstrapPlugin.jsp" />
<jsp:include page="../consultas/liberarConsulta.jsp" />
<jsp:include page="../common/deleteWithConfirmation.jsp" />

<div class="page-header">
	<h2>Fichas respondidas</h2>
</div>

<div class="row">
	<div class="col-xs-9"> 
		<table id="respuestasFichasTable" class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Fecha</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${consulta.respuestasFichas}" var="respuestasFicha">
					<tr>
						<td>${respuestasFicha.ficha.nombre}</td>
						<td>
							<fmt:formatDate value="${respuestasFicha.date}" var="dateString" pattern="dd/MM/yyyy HH:mm" />
							${dateString}
						</td>
						<td>
							<div class="btn-group">
								<a href="<c:url value='/consulta/${consulta.id}/fichaRespuesta/${respuestasFicha.id}'/>" type="button" class="btn btn-default noLiberar allowInReadOnly">
									<span class="glyphicon glyphicon-zoom-in"></span> Ver
								</a>
								<a href="<c:url value='/consulta/${consulta.id}/fichaRespuesta/${respuestasFicha.id}'/>" class="btn btn-default eliminarButton noLiberar">
									<span class="glyphicon glyphicon-remove"></span> Eliminar
								</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
			    <tr>
			        <th><input type="text" name="search_engine" value="" class="form-control" /></th>
			        <th></th>
			        <th></th>
			    </tr>
			</tfoot>
		</table>
	</div>
	
	<div class="col-xs-3"> 
		<div class="panel panel-default">
			<div class="panel-heading">Fichas</div>
			<div class="panel-body">
				<a href="<c:url value='/consulta/${consulta.id}/score/artritis'/>" class="btn btn-default btn-block modalAjax allowInReadOnly" data-target="#artritisModal">Ficha de Artritis</a>
				<a href="<c:url value='/consulta/${consulta.id}/score/movilidadDedos'/>" class="btn btn-default btn-block modalAjax allowInReadOnly" data-target="#movilidadDedosModal">Ficha de Movilidad de dedos</a>
			</div>
		</div>
	</div>
</div>


<!-- Button trigger modal -->
<button class="btn btn-primary" data-toggle="modal" data-target="#fichaModal">Responder nueva ficha</button>
<a class="btn btn-default noLiberar allowInReadOnly" href='<c:url value="/consulta/${consulta.id}" />'>Volver</a>

<!-- Modal -->
<div class="modal container" id="artritisModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->

<!-- Modal -->
<div class="modal container" id="movilidadDedosModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->

<!-- Modal -->
<div class="modal" id="fichaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">Elija una ficha...</h4>
		</div>
		<div class="modal-body">
			<c:choose>
				<c:when test="${not empty fichas}">
					<select id="fichaSeleccionada" class="form-control"> 
						<c:forEach items="${fichas}" var="ficha">
							<option value="${ficha.id}">${ficha.nombre}</option>
						</c:forEach>
					</select>
				</c:when>
				<c:otherwise>
					<p>No hay fichas sin responder.</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="modal-footer">
			<c:if test="${not empty fichas}">
				<button id="seleccionarFichaButton" type="button" class="btn btn-primary noLiberar">Seleccionar</button>
			</c:if>
			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		</div>
	</div>
</div>
<!-- /.modal -->

<script type="text/javascript" charset="utf-8" src="${dataTables}" ></script>
<script type="text/javascript" charset="utf-8" src="${dataTablesBootstrap}" ></script>
<script type="text/javascript"> 
    $(document).ready(function() {
 
        var oTable = $('#respuestasFichasTable').dataTable( {
            "aLengthMenu": [[10, 25, 50, 100, -1], [ 10, 25, 50, 100, "All"]],
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
                { "mData": "fecha" },
                { "mData": "icons" }
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