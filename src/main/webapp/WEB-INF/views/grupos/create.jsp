<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	$(function(){
		$('#grupoForm').validate();
	})
</script>
<div version="2.0">		
	<div class="page-header">
		<h1>Formulario grupo</h1>
	</div>
	<div>
		
		<spring:url value="/grupos" var="form" />
		
		<form:form commandName="grupo" class="simple_form form-horizontal" id="grupoForm" action="${form}" method="POST"
			enctype="application/x-www-form-urlencoded">
			
			<form:hidden path="id"/>
			
			<div class="form-group">
				<label for="nombre" class="col-xs-2 control-label">Nombre</label>
				<div class="col-xs-4">
					<form:input path="nombre" id="nombre" class="form-control required"/>
				</div>
			</div>				
			
			
			<div class="form-group">
				<label class="col-xs-2 control-label" for="grupos">Permisos Usuarios : </label>
				<div class="col-xs-4">
					<form:select path="permisosUsuarios" items="${permisosusuarios}" size="8" multiple="true" class="form-control required" />
				</div>
			</div>			
			
			<div class="form-actions submit">
				<input value="Guardar" type="submit" id="proceed" class="btn btn-primary" />
				<a href="<c:url value='/grupos'/>" class="btn btn-default">Volver</a>  
			</div>
		</form:form>
	</div>
</div>