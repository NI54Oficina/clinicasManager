<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>

	var preguntaHTMLTemplate = '<div class="well"><button type="button" class="close removePregunta"  aria-hidden="true">&times;</button>' +
						'<div class="form-group"><label class="control-label" for="nombre">Pregunta</label>' +
						'<input name="preguntas[{nroPregunta}].pregunta" class="form-control input-sm required"/></div><div class="form-group">' +
						'<label class="control-label" for="nombre">Opciones (opcionales)</label><div class="row"><div class="col-xs-4">' +
			 			'<input name="preguntas[{nroPregunta}].opciones[0]" type="text" class="form-control" /></div><div class="col-xs-4">' +
			  			'<input name="preguntas[{nroPregunta}].opciones[1]" type="text" class="form-control" /></div><div class="col-xs-4">' +
			  			'<input name="preguntas[{nroPregunta}].opciones[2]" type="text" class="form-control" /></div></div></div>' +
			  			'<div class="checkbox"><label><input name="preguntas[{nroPregunta}].multiplesRespuestas" type="checkbox" class="checkbox"/>Selección múltiple permitida</label></div>' +
			  			'<div><a class="agregarOpcion btn btn-default" data-nroPregunta="{nroPregunta}" onclick="agregarOpciones(this)">Agregar más opciones</a></div></div>';

	var opcionesHTMLTemplate = '<div class="row" style="margin-bottom:15px"><div class="col-xs-4"><input name="preguntas[{nroPregunta}].opciones[{nroOpcion1}]" type="text" class="form-control" />' +
						'</div><div class="col-xs-4"><input name="preguntas[{nroPregunta}].opciones[{nroOpcion2}]" type="text" class="form-control" /></div>' +
						'<div class="col-xs-4"><input name="preguntas[{nroPregunta}].opciones[{nroOpcion3}]" type="text" class="form-control" /></div></div>';
						
	var nroOpcionToNroPregunta = new Object();
	nroOpcionToNroPregunta['0'] = 3;
	
	<c:forEach items="${form.preguntas}" var="pregunta" varStatus="status" begin="1">
		nroOpcionToNroPregunta['${status.index}'] = Math.floor(${fn:length(pregunta.opciones)} / 3) * 3;
	</c:forEach>
	
	var nroPregunta = ${fn:length(form.preguntas)};
	
	if(nroPregunta == 0){
		nroPregunta = 1;
	}
	
	function agregarOpciones(object){
		
		object = $(object);
		
		var nroPregunta = object.attr('data-nroPregunta');
		
		var opcionesHTML = opcionesHTMLTemplate.replace('{nroOpcion1}',nroOpcionToNroPregunta[nroPregunta]++);
		opcionesHTML = opcionesHTML.replace('{nroOpcion2}',nroOpcionToNroPregunta[nroPregunta]++);
		opcionesHTML = opcionesHTML.replace('{nroOpcion3}',nroOpcionToNroPregunta[nroPregunta]++);
		
		//Replace all
		var find = '{nroPregunta}';
		var regEx = new RegExp(find, 'g');
		opcionesHTML = opcionesHTML.replace(regEx, nroPregunta);

		object.parent().before(opcionesHTML);
	}

	$(function(){
		
		$('#fichaForm').validate();   		
		
		$('#agregarPregunta').click(function(){
			
			nroOpcionToNroPregunta[nroPregunta] = 3;
			
			//Replace all
			var find = '{nroPregunta}';
			var regEx = new RegExp(find, 'g');
			
			var preguntaHTML = preguntaHTMLTemplate.replace(regEx, nroPregunta++);
			
			$('#agregarPreguntaDiv').before(preguntaHTML);
		});
		
		$('#fichaForm').submit(function(){
			if($('#fichaForm').valid()){
				$('input:text').filter(function() { 
					return $(this).val() == ""; 
				}).attr("disabled", "disabled");
			}
			
			return true;
		});
		
		$('body').on('click','.removePregunta',function(){
			$(this).closest('.well').remove();
		})

	});
</script>

<spring:url value="/ficha" var="form_url" />

<div class="page-header">
	<h2>Editor de fichas - Crear</h2>
</div>

<div class="col-xs-9">
	<form:form commandName="form" id="fichaForm" action="${form_url}" role="form">
	
		<form:hidden path="id"/>
	
		<div class="form-group">
			<label class="control-label" for="nombre">Nombre de la ficha</label>
			<div class="row">
				<div class="col-xs-6">
					<form:input path="nombre" class="form-control input-sm required"/>
				</div>	
			</div>
		</div>
		
		
		<c:choose>
			<c:when test="${fn:length(form.preguntas) le 0}">
				<div class="well">		
					<button type="button" class="close removePregunta"  aria-hidden="true">&times;</button>
					<div class="form-group">
						<label class="control-label" for="nombre">Pregunta</label>
						<form:input path="preguntas[0].pregunta" class="form-control input-sm required"/>
					</div>
					
					<div class="form-group">
						<label class="control-label" for="nombre">Opciones (opcionales)</label>
						<div class="row">
							<div class="col-xs-4">
							 	<form:input path="preguntas[0].opciones[0]" type="text" class="form-control" />
							</div>
							<div class="col-xs-4">
							  	<form:input path="preguntas[0].opciones[1]" type="text" class="form-control" />
							</div>
							<div class="col-xs-4">
							  	<form:input path="preguntas[0].opciones[2]" type="text" class="form-control" />
							</div>
						</div>
					</div>
					
					<div class="checkbox">
						<label>
							<form:checkbox path="preguntas[0].multiplesRespuestas" class="checkbox"/> Selección múltiple permitida
						</label>
					</div>
					
					<div>
						<a class="agregarOpcion btn btn-default" data-nroPregunta="0" onclick="agregarOpciones(this)">Agregar más opciones</a>
					</div> 
				</div>
			</c:when>
			<c:otherwise>		
		
				<c:forEach items="${form.preguntas}" var="pregunta" varStatus="statusPreguntas">
					<div class="well">
						<button type="button" class="close removePregunta" data-dismiss="modal" aria-hidden="true">&times;</button>
						<div class="form-group">
							<label class="control-label" for="nombre">Pregunta</label>
							<form:input path="preguntas[${statusPreguntas.index}].pregunta" class="form-control input-sm"/>
							<form:hidden path="preguntas[${statusPreguntas.index}].id"/>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="nombre">Opciones (opcionales)</label>			
							<c:forEach items="${pregunta.opciones}" step="3" var="opcion" varStatus="statusOpciones">
								<c:set value="${statusOpciones.index}" var="index" />
			
								<div class="row">
									<div class="col-xs-4">
									 	<form:input path="preguntas[${statusPreguntas.index}].opciones[${index}]" type="text" class="form-control" />
									</div>
									<div class="col-xs-4">
									  	<form:input path="preguntas[${statusPreguntas.index}].opciones[${index + 1}]" type="text" class="form-control" />
									</div>
									<div class="col-xs-4">
									  	<form:input path="preguntas[${statusPreguntas.index}].opciones[${index + 2}]" type="text" class="form-control" />
									</div>
								</div>
							
							</c:forEach>
						</div>
						
					<div class="checkbox">
						<label>
							<form:checkbox path="preguntas[${statusPreguntas.index}].multiplesRespuestas" class="checkbox"/> Selección múltiple permitida
						</label>
					</div>
						
						<div>
							<a class="agregarOpcion btn btn-default" data-nroPregunta="${statusPreguntas.index}" onclick="agregarOpciones(this)">Agregar más opciones</a>
						</div> 
					</div>
				</c:forEach>	
			</c:otherwise>
		</c:choose>	
		
		<div id="agregarPreguntaDiv">
			<a id="agregarPregunta" class="btn btn-default">Agregar pregunta</a>
			<input type="submit" value="Guardar" class="btn btn-primary" />
		</div>
		
	</form:form>
</div>
