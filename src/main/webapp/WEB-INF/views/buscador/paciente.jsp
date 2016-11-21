<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	$(function(){
		var form = $('#buscadorPacientes');
		
		form.ajaxForm({
		    success: function(data){
		    	var div = $(data).find('#resultsTable');
		    	$('#resultsTable').html(div);
		    	history.pushState(null, null, form.attr('action') + '?' + form.serialize());
		    },
		    error: function(response) {
		        alert(response.statusText);
		    }
		});	
		
		$('#resetFormButton').click(function(e){e
			e.preventDefault();
			$('#buscadorPacientes').clearForm();
		})
	});
</script>
<style>
	select option[disabled]:first-child {
		display: none;
	}
</style>

<div class="page-header">
	<h2>Buscador de paciente</h2>
</div>

<spring:url value="/buscar/paciente" var="form_url" />

<div class="row">	

	<form:form commandName="paciente" action="${form_url}" method="GET" class="form-horizontal" role="form" id="buscadorPacientes">
		<div class="col-xs-3 col-xs-offset-1">
			<form:input path="nombre" id="nombre" class="form-control" placeholder="nombre"/>
		</div>
	
		<div class="col-xs-2">
			<form:select path="sexo" id="sexo" class="form-control">
				<option value="" selected disabled>Sexo</option>
				<form:options items="${sexos}" itemLabel="name"/>
			</form:select>
		</div>
	
		<div class="col-xs-2">
			<form:select path="cobertura" id="cobertura" class="form-control">
				<option value="" selected disabled>Cobertura</option>
				<form:options items="${coberturas}" itemLabel="name"/>
			</form:select>
		</div>
	
		<div class="col-xs-2">
			<form:select path="ocupacion" id="ocupacion" class="form-control">
				<option value="" selected disabled>Ocupación</option>
				<form:options items="${ocupaciones}" itemLabel="name"/>
			</form:select>		
		</div>
		
		<input type="submit" value="Buscar" class="btn btn-primary" />
		<button class="btn btn-default" id="resetFormButton">Resetear</button>
	</form:form>
	
</div>

<div id="resultsTable" class="resultadosBusqueda">
	<c:if test="${not empty busquedaRealizada}">
		<jsp:include page="pacienteTable.jsp" />
	</c:if>
</div>
	
