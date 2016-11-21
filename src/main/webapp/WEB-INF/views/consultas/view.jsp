<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value="/resources/styles/bootstrap-datetimepicker.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.es.js" />"></script>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	
	var successFunction;
	
	//Form is sent if evolucion textArea is not empty
	function submitForm(){
		if ($.trim($("#evolucion").val())) {
			$('#form').submit();
		}
		else{
			successFunction();
		}
	}
	
	function loadEvoluciones(){
		$.get('${consulta.id}/evolucion?maxResult=2', function(html) {
		    $('#evoluciones #panel-body').html(html);
	    });
	}

    $(function() {
    	
        $('#form').ajaxForm({
            success: function(){
            	successFunction();
            	$("#evolucion").val('');
            	loadEvoluciones();
            },
            error: function(response) {
                alert(response.statusText);
            }
        });	    	
        
    	var buttonPlan = $('#buttonPlan');
    	$('#planModal').load(buttonPlan.attr('href'), '', function(){});        
    	
	    $('#buttonPlan').click(function(e) {
			e.preventDefault(); 
			var button = $(this);

			successFunction = function(){
				var modal = $(button.data('target'));
	     		modal.modal();
			};
			submitForm();
	    });
	    
		$('.redirect').click(function(e){
			e.preventDefault();  
			var button = $(this);
			successFunction = function(){
				window.location.href = button.attr('href');
			}
			submitForm();
		});	    

	    loadEvoluciones();
	    
		$('.date').datetimepicker({
	    	format : 'dd/mm/yyyy',
	    	autoclose: true,
	    	minView: 2,
	    	language: 'es'
	    });
		
    })
    
</script>

<jsp:include page="../common/modalBootstrapPlugin.jsp" />
<jsp:include page="liberarConsulta.jsp" />

<!-- Conditional Modals -->
<sec:authorize access="hasRole('ADMINISTRAR_CIRUGIA')">
	<c:if test="${not empty cirugiaPendiente}">
		<jsp:include page="cirugiaModal.jsp" />
	</c:if>
</sec:authorize>

<sec:authorize access="hasRole('ADMINISTAR_INTERNACION')">
	<c:if test="${not empty internacion}">
		<jsp:include page="internacionModal.jsp" />
	</c:if>
</sec:authorize>

<c:if test="${not empty fichasPendientes}">
	<jsp:include page="fichaModal.jsp" />
</c:if>		

<c:if test="${not empty cirugiaProgramada}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		<fmt:formatDate value="${cirugiaProgramada.fechaCirugia}" var="dateString" pattern="dd/MM/yyyy HH:mm" />
		Hay una cirugía programada para: ${dateString} 
		<sec:authorize access="hasRole('ADMINISTRAR_CIRUGIA')">
			<a href="<c:url value="/consulta/${consulta.id}/cirugia/${cirugiaProgramada.id }" />" class="btn btn-default noLiberar">Editar</a>
		</sec:authorize>
	</div>
</c:if>

<c:if test="${sinDiagnostico}">
	<div class="alert alert-warning">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		<strong>Alerta!</strong> No se realizó ningún diagnóstico. 
		<a href="<c:url value="/consulta/${consulta.id}/diagnostico" />" class="btn btn-warning noLiberar">Cargar ahora</a>
	</div>
</c:if>

<div class="row">
	<div class="col-xs-6">
	
		<div id="infoGral" class="panel panel-info">
			<div class="panel-heading">Información General</div>
			<div class="panel-body">
				<p>
					<b>Fecha del accidente</b>
					<fmt:formatDate value="${consulta.datosIniciales.fechaAccidente}" var="dateString" pattern="dd/MM/yyyy" />
					<span style="float: right;">
						${dateString != null ? dateString : 'Sin fecha'}
					</span>
				</p>
				<p>
					<b>Tiempo transcurrido</b>
					<span style="float: right;">${tiempoTranscurrido1 != null ? tiempoTranscurrido1 : '-'} </span>
				</p>
				<p>
					<b>Fecha de la primer consulta</b>
					<fmt:formatDate value="${consulta.fechaPrimerConsulta}" var="dateString" pattern="dd/MM/yyyy" />
					<span style="float: right;">${dateString}</span>
				</p>
				<p>
					<b>Tiempo transcurrido</b>
					<span style="float: right;">${tiempoTranscurrido2}</span>
				</p>
				<c:if test="${not empty fechaUltimaCirugia}">
					<p>
						<b>Fecha de la última cirugía</b>
						<fmt:formatDate value="${fechaUltimaCirugia}" var="dateString" pattern="dd/MM/yyyy" />
						<span style="float: right;">${dateString}</span>
					</p>
					<p>
						<b>Tiempo transcurrido</b>
						<span style="float: right;">${tiempoTranscurrido3}</span>
					</p>
				</c:if>
			</div>
		</div>	
		
		<div class="panel panel-info">
			<div class="panel-heading">Plan anterior</div>
			<fmt:formatDate value="${plan.date}" var="dateString" pattern="dd/MM/yyyy" />
			<div class="panel-body" style="max-height: 250px; overflow-y: auto">
				${dateString} - ${plan.texto}
			</div>
		</div>
	
		<div id="diagnosticos" class="panel panel-info">
			<fmt:formatDate value="${consulta.diagnostico.fechaDiagnostico}" var="dateString" pattern="dd/MM/yyyy" />
			<div class="panel-heading">Resumen del diagnóstico (${dateString})</div>
			<div id="panel-body">
				<c:choose>
					<c:when test="${empty consulta.diagnostico}">
						<p>No hay diagnósticos realizados</p>
					</c:when>
					<c:otherwise>
						<table id="diagnosticoTable" class="table">
							<thead>
								<tr>
									<th>Lesiones</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${consulta.diagnostico.resumen}</td>
								</tr>
							</tbody>
						</table>		
					</c:otherwise>
				</c:choose>
			</div>
			<sec:authorize access="hasRole('ADMINISTRAR_TRATAMIENTO')">
				<a href="<c:url value='/consulta/${consulta.id}/diagnostico'/>" class="btn btn-info btn-block redirect noLiberar allowInReadOnly" >Agregar / Editar </a>
			</sec:authorize>
		</div>
		
		
		<div id="procedimientos" class="panel panel-info">
			<div class="panel-heading">Procedimientos realizados</div>
			<div id="panel-body" style="max-height: 250px; overflow-y: auto">
				<c:choose>
					<c:when test="${empty tratamientos}">
						<p>No hay tratamientos empezados</p>
					</c:when>
					<c:otherwise>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Fecha</th>
									<th colspan="2">Tratamientos</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${tratamientos}" var="tratamientoVar">
									<tr>
										<fmt:formatDate value="${tratamientoVar.fechaInicioTratamiento}" var="dateString" pattern="dd/MM/yyyy" />
										<td>${dateString}</td>
										<td>${tratamientoVar.miembro}</td>
										<td>${tratamientoVar.fullName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
			<sec:authorize access="hasRole('ADMINISTRAR_TRATAMIENTO')">
				<a href="<c:url value='/consulta/${consulta.id}/tratamiento'/>" class="btn btn-info btn-block redirect noLiberar allowInReadOnly" >Agregar / Editar </a>
			</sec:authorize>
		</div>	
			
		
		<c:if test="${not empty internacionesList}">
			<div id="internaciones" class="panel panel-info">
				<div class="panel-heading">Internaciones</div>
				<div id="panel-body" style="max-height: 250px; overflow-y: auto">
					<table id="diagnosticoTable" class="table">
						<thead>
							<tr>
								<th>Fecha entrada</th>
								<th>Fecha salida</th>
								<th>Motivo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${internacionesList}" var="internacionLoop">
								<tr>
									<fmt:formatDate value="${internacionLoop.fechaEntrada}" var="dateString" pattern="dd/MM/yyyy" />
									<td>${dateString}</td>
									<fmt:formatDate value="${internacionLoop.fechaSalida}" var="dateString" pattern="dd/MM/yyyy" />
									<td>${dateString}</td>
									<td>${internacionLoop.motivo}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>		
				</div>
			</div>
		</c:if>
		
	</div>
	<div class="col-xs-6">
		
		<sec:authorize access="hasRole('ADMINISTRAR_EVOLUCION')">
			<div>
				<form action="${consulta.id}/evolucion" method="POST" id="form">
					<div class="form-group">
						<label for="evolucion" class="control-label">Evolución</label>
						<textarea name="evolucion" id="evolucion" class="form-control" rows="5"></textarea>
					</div> 
			
				</form>
			</div>
		</sec:authorize>	
		
		
		<div id="evoluciones" class="panel panel-info" >
			<div class="panel-heading">Evoluciones anteriores</div>
			<div id="panel-body" style="max-height: 250px; overflow-y: auto"></div>
		</div>		
		
		<div id="fotos">
			<h4>Fotos</h4>
			<div id="mediaElements">
				<jsp:include page="mediaList.jsp" />
			</div>
		</div>
	</div>
</div>

<div id="buttons" class="pull-right">
	<sec:authorize access="hasRole('ADMINISTRAR_ALTA')">
		<a href="<c:url value="/consulta/${consulta.id}/alta?form" />" class="btn btn-info redirect noLiberar allowInReadOnly" >Alta</a>
	</sec:authorize>		
	<sec:authorize access="hasRole('ADMINISTRAR_CIRUGIA')">
		<a href="<c:url value="/consulta/${consulta.id}/cirugia?form" />" class="btn btn-info redirect noLiberar" >Cirugía</a>
	</sec:authorize>	
	<sec:authorize access="hasRole('ADMINISTAR_INTERNACION')">
		<a href="<c:url value="/consulta/${consulta.id}/internacion?form" />" class="btn btn-info redirect noLiberar" >Internar</a>
	</sec:authorize>		
	<sec:authorize access="hasRole('ADMINISTRAR_PLAN')">
		<a href="<c:url value="/consulta/${consulta.id}/plan?form" />" class="btn btn-info" id="buttonPlan" data-target="#planModal" >Plan</a>
	</sec:authorize>
</div>

<!-- Plan Modal -->
<sec:authorize access="hasRole('ADMINISTRAR_PLAN')">
	<div id="planModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	</div>
</sec:authorize>

