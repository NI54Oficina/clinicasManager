<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>

	function toggleDisable(){
		if ($('#usuarioGoogle').is(":checked")) {
			$('#password').attr('disabled','disabled');
		}
		else{
			$('#password').removeAttr('disabled');
		}
	}

	$(function(){
		$('#usuarioForm').validate();
		
		toggleDisable();
		
		$('#usuarioGoogle').change(toggleDisable);
	})
</script>
<div version="2.0">
	<div class="page-header">
		<h1>Formulario usuario</h1>
	</div>
	
	<spring:url value="/usuarios"  var="form"/>	
	
	<form:form commandName="usuario" class="simple_form form-horizontal" id="usuarioForm" action="${form}" method="POST"
		enctype="application/x-www-form-urlencoded">
		
		<form:hidden path="id"/>
		
		<div class="form-group">
			<label for="username" class="col-xs-2 control-label">Username</label>
			<div class="col-xs-4">
				<form:input path="username" id="username" class="form-control required"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="nombre" class="col-xs-2 control-label">Nombre</label>
			<div class="col-xs-4">
				<form:input path="nombre" id="nombre" class="form-control required"/>
			</div>
		</div>		
		
		<div class="form-group">
			<label for="email" class="col-xs-2 control-label">E-mail</label>
			<div class="col-xs-4">
				<form:input path="email" id="email" class="form-control required email"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label for="usuarioGoogle">						
						El mail se usará para el login
						<form:checkbox path="usuarioGoogle" id="usuarioGoogle" />
					</label>
				</div>
			</div>
		</div>	
		
		<c:if test="${empty usuario.id}">
			<c:set var="passwordRequired" value="required" />
		</c:if>
		
		<div class="form-group">
			<label class="col-xs-2 control-label" for="password">Password : </label>
			<div class="col-xs-4">
				<form:password path="password" id="password" class="form-control ${passwordRequired}"/>
			</div>
			<c:if test="${not empty usuario.id}">
				<span class="help-block">Si se deja en blanco, se mantendrá la password actual.</span>
			</c:if>				
		</div>
		
		<div class="form-group">
			<label class="col-xs-2 control-label" for="grupos">Grupos : </label>
			<div class="col-xs-4">
				<form:select path="grupos" class="form-control required" items="${grupoes}" itemLabel="nombre" itemValue="id" multiple="true" id="grupos" />
			</div>
		</div>
		
		<div>						
			<input value="Guardar" type="submit" id="proceed" class="btn btn-primary" />
			<a href="<c:url value='/usuarios'/>" class="btn btn-default">Volver</a> 
		</div>
	</form:form>
</div>