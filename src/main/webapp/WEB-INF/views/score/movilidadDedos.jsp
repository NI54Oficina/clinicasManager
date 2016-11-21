<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	h3{
		margin-bottom: 20px !important;
	}
	td input{
		font-weight: bold;
	}
	::-webkit-input-placeholder { /* WebKit browsers */
    	font-weight: normal;
	}
	:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	    font-weight: normal;
	}
	::-moz-placeholder { /* Mozilla Firefox 19+ */
	    font-weight: normal;
	}
	:-ms-input-placeholder { /* Internet Explorer 10+ */
	    font-weight: normal;
	}
</style>
<script>	
	$(function() {
		$('#movilidadDedosForm').ajaxForm({
			beforeSubmit: function() {
	            return $('#movilidadDedosForm').valid();
            },
	        success: function(){
	        	location.reload();
	        	$('#movilidadDedosModal').modal('hide');
	        },
	        error: function(response) {
	            alert(response.statusText);
	        }
	    });		
		
		$('#fichasAnteriores').change(function(){
			if($('#fichasAnteriores').val()){
				$.get('score/movilidadDedos/' + $('#fichasAnteriores').val(), function(response){
					var result = $(response).find('#fichaContent');
					$('#fichaContent').html(result);
					$('input').attr('disabled', true);
				})				
			}
			else {
				$('input').attr('disabled', false);
				calcularTam();
			}
		});
		
		calcularTam();
	})
	
	function calcularTam() {
		calcularTamFor(2);
		calcularTamFor(3);
		calcularTamFor(4);
		calcularTamFor(5);
	}
	
	function calcularTamFor(index) {
		$('#IFPExtension' + index + ', #IFPFlexion' + index + ', #IFDExtension' + index + ', #IFDFlexion' + index + ', #MTCExtension' + index + ', #MTCFlexion' + index).change(function(){
			var IFPExtension = parseInt($('#IFPExtension' + index).val()) || 0;
			var IFPFlexion = parseInt($('#IFPFlexion' + index).val()) || 0;
			var IFDExtension = parseInt($('#IFDExtension' + index).val()) || 0;
			var IFDFlexion = parseInt($('#IFDFlexion' + index).val()) || 0;
			var MTCExtension = parseInt($('#MTCExtension' + index).val()) || 0;
			var MTCFlexion = parseInt($('#MTCFlexion' + index).val()) || 0;
			
			IFPExtension = IFPExtension > 0 ? 0 : -IFPExtension;
			IFDExtension = IFDExtension > 0 ? 0 : -IFDExtension;
			MTCExtension = MTCExtension > 0 ? 0 : -MTCExtension;
			
			var sum = (IFPFlexion + IFDFlexion + MTCFlexion) - (IFPExtension + IFDExtension + MTCExtension);
			$('#tam' + index).val(sum);
			
// 			if($(this).val()) {
// 				$(this).css('font-weight', 'bold');
// 			} else {
// 				$(this).css('font-weight', 'normal');
// 			}
		})
	}
</script>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>Ficha de movilidad</h3>
	</div>
	<spring:url value="/consulta/${consulta.id}/score/movilidadDedos" var="artritis_form_url" />
	<form:form action="${artritis_form_url}" method="post" id="movilidadDedosForm" commandName="fichaMovilidadDedos">
		<div class="modal-body">
			<c:if test="${not empty fichasAnteriores}">
				<div class="row form-group">
					<label class="col-xs-2">Ver ficha anterior</label>
					<div class="col-xs-3">
						<select id="fichasAnteriores" class="form-control" >
							<option></option>
							<c:forEach items="${fichasAnteriores}" var="fichaAnterior">
								<fmt:formatDate value="${fichaAnterior.fecha }" var="dateString" pattern="dd/MM/yyyy HH:mm" />
								<option value="${fichaAnterior.id }">${dateString}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</c:if>
			<div id="fichaContent">
				<h3>Movilidad de dedos</h3>
				<div class="row">
					<table class="table">
						<thead>
							<tr>
								<th>Mov activa</th>
								<th colspan="2">II</th>
								<th colspan="2">III</th>
								<th colspan="2">IV</th>
								<th colspan="2">V</th>
							</tr>
							<tr>
								<th></th>
								<th>extensión</th>
								<th>flexión</th>
								<th>extensión</th>
								<th>flexión</th>
								<th>extensión</th>
								<th>flexión</th>
								<th>extensión</th>
								<th>flexión</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>MTC-F</td>
								<td><form:input class="form-control number" id="MTCExtension2" path="respuestas[0]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control number" id="MTCFlexion2"   path="respuestas[1]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control number" id="MTCExtension3" path="respuestas[2]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control number" id="MTCFlexion3"   path="respuestas[3]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control number" id="MTCExtension4" path="respuestas[4]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control number" id="MTCFlexion4"   path="respuestas[5]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control number" id="MTCExtension5" path="respuestas[6]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control number" id="MTCFlexion5"   path="respuestas[7]" placeholder="0 a 90" /></td>
							</tr>
							<tr>
								<td>IFP</td>
								<td><form:input class="form-control number" id="IFPExtension2" path="respuestas[8]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control number" id="IFPFlexion2"   path="respuestas[9]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control number" id="IFPExtension3" path="respuestas[10]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control number" id="IFPFlexion3"   path="respuestas[11]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control number" id="IFPExtension4" path="respuestas[12]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control number" id="IFPFlexion4"   path="respuestas[13]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control number" id="IFPExtension5" path="respuestas[14]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control number" id="IFPFlexion5"   path="respuestas[15]" placeholder="0 a 110" /></td>
							</tr>
							<tr>
								<td>IFD</td>
								<td><form:input class="form-control number" id="IFDExtension2" path="respuestas[16]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control number" id="IFDFlexion2"   path="respuestas[17]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control number" id="IFDExtension3" path="respuestas[18]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control number" id="IFDFlexion3"   path="respuestas[19]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control number" id="IFDExtension4" path="respuestas[20]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control number" id="IFDFlexion4"   path="respuestas[21]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control number" id="IFDExtension5" path="respuestas[22]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control number" id="IFDFlexion5"   path="respuestas[23]" placeholder="0 a 65" /></td>
							</tr>		
							<tr>
								<td>TAM</td>
								<td colspan="2"><form:input class="form-control" id="tam2" path="respuestas[24]" readonly="true" /></td>
								<td colspan="2"><form:input class="form-control" id="tam3" path="respuestas[25]" readonly="true"/></td>
								<td colspan="2"><form:input class="form-control" id="tam4" path="respuestas[26]" readonly="true"/></td>
								<td colspan="2"><form:input class="form-control" id="tam5" path="respuestas[27]" readonly="true"/></td>
							</tr>	
						</tbody>
					</table>	
					
					<table class="table">
						<thead>
							<tr>
								<th>Mov pasiva</th>
								<th colspan="2">II</th>
								<th colspan="2">III</th>
								<th colspan="2">IV</th>
								<th colspan="2">V</th>
							</tr>
							<tr>
								<th></th>
								<th>extensión</th>
								<th>flexión</th>
								<th>extensión</th>
								<th>flexión</th>
								<th>extensión</th>
								<th>flexión</th>
								<th>extensión</th>
								<th>flexión</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>MTC-F</td>
								<td><form:input class="form-control number" path="respuestas[28]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control number" path="respuestas[29]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control number" path="respuestas[30]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control number" path="respuestas[31]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control number" path="respuestas[32]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control number" path="respuestas[33]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control number" path="respuestas[34]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control number" path="respuestas[35]" placeholder="0 a 90" /></td>
							</tr>
							<tr>
								<td>IFP</td>
								<td><form:input class="form-control number" path="respuestas[36]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control number" path="respuestas[37]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control number" path="respuestas[38]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control number" path="respuestas[39]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control number" path="respuestas[40]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control number" path="respuestas[41]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control number" path="respuestas[42]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control number" path="respuestas[43]" placeholder="0 a 110" /></td>
							</tr>
							<tr>
								<td>IFD</td>
								<td><form:input class="form-control number" path="respuestas[44]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control number" path="respuestas[45]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control number" path="respuestas[46]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control number" path="respuestas[47]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control number" path="respuestas[48]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control number" path="respuestas[49]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control number" path="respuestas[50]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control number" path="respuestas[51]" placeholder="0 a 65" /></td>
							</tr>		
						</tbody>
					</table>	
					
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>Apertura 1ra comisura</th>
								<th>Oposición</th>
								<th>Reposición levanta pulgar de la mesa</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>MTC-F</td>
								<td><form:input class="form-control number" path="respuestas[52]" placeholder="0 a 70" /></td>
								<td><form:input class="form-control number" path="respuestas[53]" placeholder="0 a 80mm" /></td>
								<td><form:checkbox value="true" path="respuestas[54]"/></td>
							</tr>						
						</tbody>
					</table>						
					
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>Flexión</th>
								<th>Extensión</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>MTC-F</td>
								<td><form:input class="form-control number" path="respuestas[55]" placeholder="0 a 50" /></td>
								<td><form:input class="form-control number" path="respuestas[56]" placeholder="45 a -50" /></td>
							</tr>
							<tr>
								<td>IF</td>
								<td><form:input class="form-control number" path="respuestas[57]" placeholder="0 a 80" /></td>
								<td><form:input class="form-control number" path="respuestas[58]" placeholder="20 a -80" /></td>
							</tr>							
						</tbody>
					</table>		
					
					<h3 style="margin-top: 40px">Hombro, Codo y Muñeca</h3>
					<table class="table">
						<thead>
							<tr>
								<th>Hombro</th>
								<th>Codo</th>
								<th>Muñeca</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><label>Flexión</label><form:input class="form-control number" path="respuestas[59]" placeholder="0 a 180" /></td>
								<td><label>Flexión</label><form:input class="form-control number" path="respuestas[60]" placeholder="0 a 150" /></td>
								<td><label>Flexión</label><form:input class="form-control number" path="respuestas[61]" placeholder="0 a 80" /></td>
							</tr>
							<tr>
								<td><label>Extensión</label><form:input class="form-control number" path="respuestas[62]" placeholder="0 a 60" /></td>
								<td><label>Extensión</label><form:input class="form-control number" path="respuestas[63]" placeholder="0 a -150" /></td>
								<td><label>Extensión</label><form:input class="form-control number" path="respuestas[64]" placeholder="0 a -80" /></td>
							</tr>
							<tr>
								<td><label>Aducción</label><form:input class="form-control number" path="respuestas[65]" placeholder="0 a 30" /></td>
								<td><label>Pronación</label><form:input class="form-control number" path="respuestas[66]" placeholder="0 a 90" /></td>
								<td><label>Desv. Radial</label><form:input class="form-control number" path="respuestas[67]" placeholder="0 a 30" /></td>
							</tr>
							<tr>
								<td><label>Abeducción</label><form:input class="form-control number" path="respuestas[68]" placeholder="0 a 180" /></td>
								<td><label>Supinación</label><form:input class="form-control number" path="respuestas[69]" placeholder="0 a 90" /></td>
								<td><label>Desv. Cubita</label><form:input class="form-control number" path="respuestas[70]" placeholder="0 a 40" /></td>
							</tr>
							<tr>
								<td><label>Rotación int</label><form:input class="form-control number" path="respuestas[71]" placeholder="0 a 70" /></td>
								<td></td>
								<td><label>Pronación</label><form:input class="form-control number" path="respuestas[72]" placeholder="0 a 90" /></td>
							</tr>
							<tr>
								<td><label>Rotación ext</label><form:input class="form-control number" path="respuestas[73]" placeholder="0 a 90" /></td>
								<td></td>
								<td><label>Supinación</label><form:input class="form-control number" path="respuestas[74]" placeholder="0 a 90" /></td>
							</tr>							
						</tbody>
					</table>								
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<input type="submit" class="btn btn-primary" value="Guardar" />
			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		</div>
	</form:form>
</div>
	<!-- /.modal-content -->		
