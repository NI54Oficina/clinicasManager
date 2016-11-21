<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>	
	$(function() {
		$('#quickDashForm').validate({
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
		
		$('#quickDashForm').ajaxForm({
			beforeSubmit: function() {
	            return $('#quickDashForm').valid();
            },
	        success: function(score){
	        	loadScoresResumen();
	        	$('#quickDashModal').modal('hide');
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
	<spring:url value="/consulta/${consulta.id}/score/quickDash" var="quickDash_form_url" />
	<form:form id="quickDashForm" action="${quickDash_form_url}" method="post" commandName="quickDash">
		<div class="modal-body">
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%"></th>
						<th style="width: 10%">Sin dificultad</th>
						<th style="width: 10%">Poca dificultad</th>
						<th style="width: 10%">Moderada dificultad</th>
						<th style="width: 10%">Dificultad severa</th>
						<th style="width: 10%">Incapaz de realizar</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1: Abrir un frasco con tapa a rosca</td>
						<td><form:radiobutton value="1" path="respuestas[0]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[0]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[0]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[0]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[0]"/> 5</td>
					</tr>
					<tr>
						<td>2: Hacer tareas domésticas pesadas (lavar pisos o paredes)</td>
						<td><form:radiobutton value="1" path="respuestas[1]" class="required" /> 1</td>
						<td><form:radiobutton value="2" path="respuestas[1]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[1]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[1]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[1]"/> 5</td>
					</tr>
					<tr>
						<td>3: Cargas una bolsa o maletín</td>
						<td><form:radiobutton value="1" path="respuestas[2]" class="required" /> 1</td>
						<td><form:radiobutton value="2" path="respuestas[2]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[2]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[2]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[2]"/> 5</td>
					</tr>
					<tr>
						<td>4: Lavarse la espalda</td>
						<td><form:radiobutton value="1" path="respuestas[3]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[3]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[3]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[3]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[3]"/> 5</td>
					</tr>
					<tr>
						<td>5: Cortar los alimentos con un cuchillo</td>
						<td><form:radiobutton value="1" path="respuestas[4]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[4]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[4]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[4]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[4]"/> 5</td>
					</tr>
					<tr>
						<td>6: Actividades recreativas que requieran fuerza de impacto (golf, tenis, utilizar un martillo, etc.)</td>
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
						<th style="width: 50%"></th>
						<th style="width: 10%">Absoluta- mente nada</th>
						<th style="width: 10%">Un poco</th>
						<th style="width: 10%">Moderada- mente</th>
						<th style="width: 10%">Bastante</th>
						<th style="width: 10%">Muchísimo</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>7: Durante la última semana ¿se vio limitada su actividad social a causa de su mano/brazo/hombro?</td>
						<td><form:radiobutton value="1" path="respuestas[6]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[6]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[6]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[6]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[6]"/> 5</td>
					</tr>
					<tr>
						<td>8: Durante la última semana ¿se vio limitada su actividad laboral a causa de su mano/brazo/hombro?</td>
						<td><form:radiobutton value="1" path="respuestas[7]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[7]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[7]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[7]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[7]"/> 5</td>
					</tr>
				</tbody>
			</table>	
			
			<h4>Durante la última semana presentó:</h4>		
			
			<table class="table">
				<thead>
					<tr>
						<th style="width: 50%"></th>
						<th style="width: 10%">Ninguna</th>
						<th style="width: 10%">Leve</th>
						<th style="width: 10%">Moderada</th>
						<th style="width: 10%">Intensa</th>
						<th style="width: 10%">Muy intensa</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>9: Dolor en mano/brazo/hombro</td>
						<td><form:radiobutton value="1" path="respuestas[8]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[8]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[8]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[8]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[8]"/> 5</td>
					</tr>
					<tr>
						<td>10: Parestesias en mano/brazo/hombro</td>
						<td><form:radiobutton value="1" path="respuestas[9]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[9]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[9]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[9]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[9]"/> 5</td>
					</tr>
					<tr>
						<td>11: Dificultad para dormir</td>
						<td><form:radiobutton value="1" path="respuestas[10]" class="required"/> 1</td>
						<td><form:radiobutton value="2" path="respuestas[10]"/> 2</td>
						<td><form:radiobutton value="3" path="respuestas[10]"/> 3</td>
						<td><form:radiobutton value="4" path="respuestas[10]"/> 4</td>
						<td><form:radiobutton value="5" path="respuestas[10]"/> 5</td>
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