<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>	
	$(function() {
		$('#michiganForm').validate({
		    highlight: function(element) {
			    var prevTd = $(element).closest('td').prev();
			    $(prevTd).css('color','#b94a48');
		    },
		    unhighlight: function(element) {
		    	var prevTd = $(element).closest('td').prev();
			    $(prevTd).removeAttr('style');
		    },
		    errorElement: 'span',
		    errorClass: 'error',
		    errorPlacement: function(error, element) {
		    	var prevTd = $(element).closest('td').prev();
		    	error.css('display','block');
		    	$(prevTd).append(error);
		    }
		});
		
		$('#michiganForm').ajaxForm({
			beforeSubmit: function() {
	            return $('#michiganForm').valid();
            },
	        success: function(score){
	        	loadScoresResumen();
	        	$('#michiganModal').modal('hide');
	        },
	        error: function(response) {
	            alert(response.statusText);
	        }
	    });			
	})
</script>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>Quick Dash</h3>
	</div>
	<spring:url value="/consulta/${consulta.id}/score/michigan" var="michigan_form_url" />
	<form:form id="michiganForm" action="${michigan_form_url}" method="post" commandName="michigan">
		<div class="modal-body">
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%">Durante la última semana</th>
						<th style="width: 10%">Muy bien</th>
						<th style="width: 10%">Bien</th>
						<th style="width: 10%">Moderadamente bien</th>
						<th style="width: 10%">Mal</th>
						<th style="width: 10%">Muy mal</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1: ¿Qué tan bien pudo utilizar su mano?</td>
						<td><form:radiobutton value="1" path="respuestas[0]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[0]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[0]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[0]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[0]"/> 5</td>
					</tr>
					<tr>
						<td>2: ¿Cómo estuvo la sensibilidad en su mano?</td>
						<td><form:radiobutton value="1" path="respuestas[1]" class="required" /> 1</td>
						<td><form:radiobutton value="2" path="respuestas[1]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[1]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[1]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[1]"/> 5</td>
					</tr>
				</tbody>
			</table>
			
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%">Durante la última semana</th>
						<th style="width: 10%">Sin dificultad</th>
						<th style="width: 10%">Dificultad muy leve</th>
						<th style="width: 10%">Dificultad leve</th>
						<th style="width: 10%">Dificultad moderada</th>
						<th style="width: 10%">Dificultad severa</th>
					</tr>
				</thead>
				<tbody>			
					<tr>
						<td>3: ¿CQue tan difícil fue sostener una sartén con su mano?</td>
						<td><form:radiobutton value="1" path="respuestas[2]" class="required" /> 1</td>
						<td><form:radiobutton value="2" path="respuestas[2]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[2]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[2]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[2]"/> 5</td>
					</tr>
					<tr>
						<td>4: ¿Que tan difícil fue abrocharse una camisa?</td>
						<td><form:radiobutton value="1" path="respuestas[3]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[3]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[3]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[3]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[3]"/> 5</td>
					</tr>
				</tbody>
			</table>
			
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%">Durante las últimas 4 semanas</th>
						<th style="width: 10%">Siempre</th>
						<th style="width: 10%">Casi Siempre</th>
						<th style="width: 10%">A veces</th>
						<th style="width: 10%">Ocasionalmente</th>
						<th style="width: 10%">Nunca</th>
					</tr>
				</thead>
				<tbody>									
					<tr>
						<td>5: ¿Con qué frecuencia se vio incapaz de realizar su trabajo por problemas en su mano/muñeca?</td>
						<td><form:radiobutton value="1" path="respuestas[4]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[4]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[4]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[4]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[4]"/> 5</td>
					</tr>
					<tr>
						<td>6: ¿Con qué frecuencia debe tomarse más tiempo para finalizar las tareas de su trabajo por problemas en su mano/muñeca?</td>
						<td><form:radiobutton value="1" path="respuestas[5]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[5]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[5]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[5]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[5]"/> 5</td>
					</tr>
				</tbody>
			</table>
			
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%">Durante la última semana</th>
						<th style="width: 10%">Siempre</th>
						<th style="width: 10%">Casi Siempre</th>
						<th style="width: 10%">A veces</th>
						<th style="width: 10%">Ocasionalmente</th>
						<th style="width: 10%">Nunca</th>
					</tr>
				</thead>
				<tbody>	
					<tr>
						<td>7: ¿Con qué frecuencia el dolor en su mano interfirio con su actividad cotidiana (asearse, comer, etc)</td>
						<td><form:radiobutton value="1" path="respuestas[6]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[6]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[6]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[6]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[6]"/> 5</td>
					</tr>
				</tbody>
			</table>					
					
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%">Durante la última semana</th>
						<th style="width: 10%">Muy leve</th>
						<th style="width: 10%">Leve</th>
						<th style="width: 10%">Moderado</th>
						<th style="width: 10%">Severo</th>
						<th style="width: 10%">Muy severo</th>
					</tr>
				</thead>
				<tbody>						
					<tr>
						<td>8: Describa como fue su dolor</td>
						<td><form:radiobutton value="1" path="respuestas[7]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[7]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[7]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[7]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[7]"/> 5</td>
					</tr>
				</tbody>
			</table>	
			
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%">Durante la última semana</th>
						<th style="width: 10%">Acuerdo</th>
						<th style="width: 10%">Acuerdo moderadamente</th>
						<th style="width: 10%">No acuerdo ni desacuerdo</th>
						<th style="width: 10%">En desacuerdo</th>
						<th style="width: 10%">Muy en desacuerdo</th>
					</tr>
				</thead>
				<tbody>	
					<tr>
						<td>9: Estuve satisfecho con la apariencia de mi mano</td>
						<td><form:radiobutton value="1" path="respuestas[8]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[8]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[8]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[8]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[8]"/> 5</td>
					</tr>
					<tr>
						<td>10: La apariencia de mi mano interfirió con mis actividades sociales</td>
						<td><form:radiobutton value="1" path="respuestas[9]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[9]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[9]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[9]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[9]"/> 5</td>
					</tr>
				</tbody>
			</table>
						
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%">Durante la última semana</th>
						<th style="width: 10%">Muy satisfecho</th>
						<th style="width: 10%">Moderadamente satisfecho</th>
						<th style="width: 10%">Ni satisfecho ni insatisfecho</th>
						<th style="width: 10%">Moderadamente insatisfecho</th>
						<th style="width: 10%">Muy insatisfecho</th>
					</tr>
				</thead>								
					<tr>
						<td>11: ¿Qué tan satisfecho estuvo con lamovilidad de los dedos de su mano?</td>
						<td><form:radiobutton value="1" path="respuestas[10]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[10]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[10]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[10]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[10]"/> 5</td>
					</tr>
					<tr>
						<td>12: ¿Que tan satisfecho estuvo con la movilidad de su muñeca?</td>
						<td><form:radiobutton value="1" path="respuestas[11]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[11]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[11]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[11]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[11]"/> 5</td>
					</tr>					
				</tbody>
			</table>				
		</div>
		<div class="modal-footer">
			<input type="submit" class="btn btn-primary" value="Guardar" />
			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		</div>
	</form:form>			
</div>
<!-- /.modal-content -->