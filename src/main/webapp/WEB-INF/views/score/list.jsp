<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href="<c:url value="/resources/styles/bootstrap-datetimepicker.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.es.js" />"></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	function loadScoresResumen(){
		$.get('<c:url value="/consulta/${consulta.id}/score/resumen"/>', function(html){
			$('#scoresResumen').html(html);
		});
	}
</script>

<jsp:include page="../common/modalBootstrapPlugin.jsp" />
<jsp:include page="../consultas/liberarConsulta.jsp" />

<div class="page-header">
	<h2>Scores</h2>
</div>

<spring:url value="/consulta/${consulta.id}/alta" var="form_url" />

<div class="col-xs-6">
	<div id="scoresResumen">
		<jsp:include page="resumen.jsp" />
	</div>
		
	<a href="<c:url value='/consulta/${consulta.id}'/>" class="btn btn-default noLiberar allowInReadOnly">Volver</a>
</div>

<div class="col-xs-4 col-xs-offset-1"> 
	<div class="panel panel-default">
		<div class="panel-heading">Scores</div>
		<div class="panel-body">
			<a href="<c:url value='/consulta/${consulta.id}/score/quickDash'/>" class="btn btn-default btn-block modalAjax allowInReadOnly" data-target="#quickDashModal">Completar Quick Dash</a>
			<a href="<c:url value='/consulta/${consulta.id}/score/escalaDeDolor'/>" class="btn btn-default btn-block modalAjax allowInReadOnly" data-target="#escalaDeDolorModal">Completar Escala de Dolor</a>
			<a href="<c:url value='/consulta/${consulta.id}/score/michigan'/>" class="btn btn-default btn-block modalAjax allowInReadOnly" data-target="#michiganModal">Completar Brief Michigan</a>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal container" id="quickDashModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->

<!-- Modal -->
<div class="modal" id="escalaDeDolorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->

<!-- Modal -->
<div class="modal container" id="michiganModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div><!-- /.modal -->