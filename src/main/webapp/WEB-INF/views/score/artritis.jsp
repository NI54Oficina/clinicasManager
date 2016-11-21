<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
	h3{
		margin-bottom: 20px !important;
	}
</style>
<script>	
	$(function() {
		$('#artritisForm').ajaxForm({
			beforeSubmit: function() {
	            return $('#artritisForm').valid();
            },
	        success: function(){
	        	location.reload();
	        	$('#artritisModal').modal('hide');
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
		<h3>Artritis</h3>
	</div>
	<spring:url value="/consulta/${consulta.id}/score/artritis" var="artritis_form_url" />
	<form:form action="${artritis_form_url}" method="post" id="artritisForm" commandName="artritis">
		<div class="modal-body">
			<div class="row">
				<div class="form-group">
					<label class="col-xs-2 control-label ">Miembro superior</label>
					<div class="col-xs-3">
				    	<form:select id="miembroDiagnostico" path="respuestas[0]" class="form-control">
							<form:option value="MSD" >Derecho</form:option>
							<form:option value="MSI" >Izquierdo</form:option>
						</form:select>
			    	</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px">
				<div class="col-xs-12">
					<h3>Muñeca</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<div class="row form-group">
						<label class="col-xs-2 control-label">Dolor</label>
						<div class="col-xs-6">
							<form:select class="form-control" path="respuestas[1]">
								<form:option value=""></form:option>
								<form:option value="2">2</form:option>
								<form:option value="4">4</form:option>
								<form:option value="6">6</form:option>
								<form:option value="8">8</form:option>
								<form:option value="10">10</form:option>
							</form:select>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<table class="table" style="text-align: center">
						<thead>
							<tr>
								<th></th>
								<th style="width: 25%">Leve</th>
								<th style="width: 25%">Moderada</th>
								<th style="width: 25%">Severa</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>sinovitis</td>
								<td><form:radiobutton value="1" path="respuestas[2]"  /></td>
								<td><form:radiobutton value="2" path="respuestas[2]"/></td>
								<td><form:radiobutton value="3" path="respuestas[2]"/></td>
							</tr>
							<tr>
								<td>tenosinovitis dorsal</td>
								<td><form:radiobutton value="1" path="respuestas[3]"/></td>
								<td><form:radiobutton value="2" path="respuestas[3]"/></td>
								<td><form:radiobutton value="3" path="respuestas[3]"/></td>
							</tr>
							<tr>
								<td>luxacion RCD</td>
								<td><form:radiobutton value="1" path="respuestas[4]"/></td>
								<td><form:radiobutton value="2" path="respuestas[4]"/></td>
								<td><form:radiobutton value="3" path="respuestas[4]"/></td>
							</tr>
							<tr>
								<td>tenosinovitis volar</td>
								<td><form:radiobutton value="1" path="respuestas[5]"/></td>
								<td><form:radiobutton value="2" path="respuestas[5]"/></td>
								<td><form:radiobutton value="3" path="respuestas[5]"/></td>
							</tr>
							<tr>
								<td>STC</td>
								<td><form:radiobutton value="1" path="respuestas[6]"/></td>
								<td><form:radiobutton value="2" path="respuestas[6]"/></td>
								<td><form:radiobutton value="3" path="respuestas[6]"/></td>
							</tr>
							<tr>
								<td>afectacion rx</td>
								<td><form:radiobutton value="1" path="respuestas[7]"/></td>
								<td><form:radiobutton value="2" path="respuestas[7]"/></td>
								<td><form:radiobutton value="3" path="respuestas[7]"/></td>
							</tr>
							<tr>
								<td>traslocacion cubital</td>
								<td><form:checkbox value="1" path="respuestas[8]"/></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-xs-6 form-horizontal">
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Flexión</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[9]" placeholder="0 a 80" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Extensión</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[10]" placeholder="70 a -80" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Desv. radial</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[11]" placeholder="0 a 30" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Desv. cubital</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[12]" placeholder="0 a 40" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Pronación</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[13]" placeholder="0 a 90" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Supinación</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[14]" placeholder="0 a 90" />
						</div>
					</div>															
				</div>
			</div>
			<div class="row" style="margin-top: 20px">
				<div class="col-xs-6">
					<h3>Rupturas tendinosas</h3>
				</div>				
			</div>
			<div class="row">
				<div class="col-xs-4">
					<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[15]" /> abeductor largo del pulgar
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[16]" /> extensor largo del pulgar
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[17]" /> extensor corto del pulgar
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[18]" /> extensor propio II
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[19]" /> extensor comun II
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[20]" /> extensor comun III
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[21]" /> extensor comun IV
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[22]" /> extensor comun V
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[23]" /> extensor propio V
					    </label>
	    			</div>
				</div>
				<div class="col-xs-4">
					<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[24]" /> flexor largo del pulgar
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[25]" /> flexor profundo II
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[26]" /> flexor profundo III
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[27]" /> flexor profundo IV
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[28]" /> flexor profundo V
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[29]" /> flexor superficial II
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[30]" /> flexor superficial III
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[31]" /> flexor superficial IV
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[32]" /> flexor superficial V
					    </label>
	    			</div>				
				</div>
				<div class="col-xs-4">
					<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[33]" /> cubital ant
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[34]" /> cubital post
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[35]" /> 1er radial
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[36]" /> 2do radial
					    </label>
	    			</div>
	    			<div class="checkbox">
				    	<label>
				    		<form:checkbox value="true" path="respuestas[37]" /> otros
					    </label>
	    			</div>				
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<h3>Ariculaciones MTC-F</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>II</th>
								<th>III</th>
								<th>IV</th>
								<th>V</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Sinovitis</td>
								<td><form:checkbox value="true" path="respuestas[38]"/></td>
								<td><form:checkbox value="true" path="respuestas[39]"/></td>
								<td><form:checkbox value="true" path="respuestas[40]"/></td>
								<td><form:checkbox value="true" path="respuestas[41]"/></td>
							</tr>
							<tr>
								<td>Dolor</td>
								<td>
									<form:select path="respuestas[42]">
										<form:option value=""></form:option>
										<form:option value="2">2</form:option>
										<form:option value="4">4</form:option>
										<form:option value="6">6</form:option>
										<form:option value="8">8</form:option>
										<form:option value="10">10</form:option>
									</form:select>
								</td>
								<td>
									<form:select path="respuestas[43]">
										<form:option value=""></form:option>
										<form:option value="2">2</form:option>
										<form:option value="4">4</form:option>
										<form:option value="6">6</form:option>
										<form:option value="8">8</form:option>
										<form:option value="10">10</form:option>
									</form:select>
								</td>
								<td>
									<form:select path="respuestas[44]">
										<form:option value=""></form:option>
										<form:option value="2">2</form:option>
										<form:option value="4">4</form:option>
										<form:option value="6">6</form:option>
										<form:option value="8">8</form:option>
										<form:option value="10">10</form:option>
									</form:select>
								</td>
								<td>
									<form:select path="respuestas[45]">
										<form:option value=""></form:option>
										<form:option value="2">2</form:option>
										<form:option value="4">4</form:option>
										<form:option value="6">6</form:option>
										<form:option value="8">8</form:option>
										<form:option value="10">10</form:option>
									</form:select>
								</td>
							</tr>
							<tr>
								<td>Desviación cubital reductible</td>
								<td><form:checkbox value="true" path="respuestas[46]"/></td>
								<td><form:checkbox value="true" path="respuestas[47]"/></td>
								<td><form:checkbox value="true" path="respuestas[48]"/></td>
								<td><form:checkbox value="true" path="respuestas[49]"/></td>
							</tr>
							<tr>
								<td>Desviación cubital no reductible</td>
								<td><form:checkbox value="true" path="respuestas[50]"/></td>
								<td><form:checkbox value="true" path="respuestas[51]"/></td>
								<td><form:checkbox value="true" path="respuestas[52]"/></td>
								<td><form:checkbox value="true" path="respuestas[53]"/></td>
							</tr>
							<tr>
								<td>Contractura de intrínsecos (Fowler)</td>
								<td><form:checkbox value="true" path="respuestas[54]"/></td>
								<td><form:checkbox value="true" path="respuestas[55]"/></td>
								<td><form:checkbox value="true" path="respuestas[56]"/></td>
								<td><form:checkbox value="true" path="respuestas[57]"/></td>
							</tr>
							<tr>
								<td>Rx luxación volar</td>
								<td><form:checkbox value="true" path="respuestas[58]"/></td>
								<td><form:checkbox value="true" path="respuestas[59]"/></td>
								<td><form:checkbox value="true" path="respuestas[60]"/></td>
								<td><form:checkbox value="true" path="respuestas[61]"/></td>
							</tr>
							<tr>
								<td>Rx destrucción osea</td>
								<td><form:checkbox value="true" path="respuestas[62]"/></td>
								<td><form:checkbox value="true" path="respuestas[63]"/></td>
								<td><form:checkbox value="true" path="respuestas[64]"/></td>
								<td><form:checkbox value="true" path="respuestas[65]"/></td>
							</tr>																																										
						</tbody>
					</table>
					
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
								<td><form:input class="form-control" path="respuestas[66]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control" path="respuestas[67]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control" path="respuestas[68]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control" path="respuestas[69]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control" path="respuestas[70]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control" path="respuestas[71]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control" path="respuestas[72]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control" path="respuestas[73]" placeholder="0 a 90" /></td>
							</tr>
							<tr>
								<td>IFP</td>
								<td><form:input class="form-control" path="respuestas[74]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control" path="respuestas[75]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control" path="respuestas[76]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control" path="respuestas[77]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control" path="respuestas[78]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control" path="respuestas[79]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control" path="respuestas[80]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control" path="respuestas[81]" placeholder="0 a 110" /></td>
							</tr>
							<tr>
								<td>IFD</td>
								<td><form:input class="form-control" path="respuestas[82]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control" path="respuestas[83]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control" path="respuestas[84]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control" path="respuestas[85]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control" path="respuestas[86]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control" path="respuestas[87]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control" path="respuestas[88]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control" path="respuestas[89]" placeholder="0 a 65" /></td>
							</tr>		
							<tr>
								<td>TAM</td>
								<td colspan="2"><form:input class="form-control" path="respuestas[90]" /></td>
								<td colspan="2"><form:input class="form-control" path="respuestas[91]" /></td>
								<td colspan="2"><form:input class="form-control" path="respuestas[92]" /></td>
								<td colspan="2"><form:input class="form-control" path="respuestas[93]" /></td>
							</tr>	
						</tbody>
					</table>					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<h3>Dedos</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<table class="table">
						<thead>
							<tr>
								<th>Tenosinovitis digital</th>
								<th>II</th>
								<th>III</th>
								<th>IV</th>
								<th>V</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Grado I (crepitacion o gatillo)</td>
								<td><form:checkbox value="true" path="respuestas[94]"/></td>
								<td><form:checkbox value="true" path="respuestas[95]"/></td>
								<td><form:checkbox value="true" path="respuestas[96]"/></td>
								<td><form:checkbox value="true" path="respuestas[97]"/></td>
							</tr>
							<tr>
								<td>Grado II (bloqueada en flexion)</td>
								<td><form:checkbox value="true" path="respuestas[98]"/></td>
								<td><form:checkbox value="true" path="respuestas[99]"/></td>
								<td><form:checkbox value="true" path="respuestas[100]"/></td>
								<td><form:checkbox value="true" path="respuestas[101]"/></td>
							</tr>
							<tr>
								<td>Grado III (bloqueada en extension)</td>
								<td><form:checkbox value="true" path="respuestas[102]"/></td>
								<td><form:checkbox value="true" path="respuestas[103]"/></td>
								<td><form:checkbox value="true" path="respuestas[104]"/></td>
								<td><form:checkbox value="true" path="respuestas[105]"/></td>
							</tr>
							<tr>
								<td>Grado IV(sinovial hipertrofica gralizada)</td>
								<td><form:checkbox value="true" path="respuestas[106]"/></td>
								<td><form:checkbox value="true" path="respuestas[107]"/></td>
								<td><form:checkbox value="true" path="respuestas[108]"/></td>
								<td><form:checkbox value="true" path="respuestas[109]"/></td>
							</tr>
							<tr>
								<td>Bouttonniere reductible</td>
								<td><form:checkbox value="true" path="respuestas[110]"/></td>
								<td><form:checkbox value="true" path="respuestas[111]"/></td>
								<td><form:checkbox value="true" path="respuestas[112]"/></td>
								<td><form:checkbox value="true" path="respuestas[113]"/></td>
							</tr>
							<tr>
								<td>Bouttonniere no reductible</td>
								<td><form:checkbox value="true" path="respuestas[114]"/></td>
								<td><form:checkbox value="true" path="respuestas[115]"/></td>
								<td><form:checkbox value="true" path="respuestas[116]"/></td>
								<td><form:checkbox value="true" path="respuestas[117]"/></td>
							</tr>			
							<tr>
								<td>Cuello de cisne reductible</td>
								<td><form:checkbox value="true" path="respuestas[118]"/></td>
								<td><form:checkbox value="true" path="respuestas[119]"/></td>
								<td><form:checkbox value="true" path="respuestas[120]"/></td>
								<td><form:checkbox value="true" path="respuestas[121]"/></td>
							</tr>
							<tr>
								<td>Cuello de cisne no reductible</td>
								<td><form:checkbox value="true" path="respuestas[122]"/></td>
								<td><form:checkbox value="true" path="respuestas[123]"/></td>
								<td><form:checkbox value="true" path="respuestas[124]"/></td>
								<td><form:checkbox value="true" path="respuestas[125]"/></td>
							</tr>	
							<tr>
								<td>Mallet agudo</td>
								<td><form:checkbox value="true" path="respuestas[126]"/></td>
								<td><form:checkbox value="true" path="respuestas[127]"/></td>
								<td><form:checkbox value="true" path="respuestas[128]"/></td>
								<td><form:checkbox value="true" path="respuestas[129]"/></td>
							</tr>	
							<tr>
								<td>Mallet de más de 1 mes</td>
								<td><form:checkbox value="true" path="respuestas[130]"/></td>
								<td><form:checkbox value="true" path="respuestas[131]"/></td>
								<td><form:checkbox value="true" path="respuestas[132]"/></td>
								<td><form:checkbox value="true" path="respuestas[133]"/></td>
							</tr>	
							<tr>
								<td>Rx IFP afectación leve</td>
								<td><form:checkbox value="true" path="respuestas[134]"/></td>
								<td><form:checkbox value="true" path="respuestas[135]"/></td>
								<td><form:checkbox value="true" path="respuestas[136]"/></td>
								<td><form:checkbox value="true" path="respuestas[137]"/></td>
							</tr>
							<tr>
								<td>Rx IFP destrucción ósea</td>
								<td><form:checkbox value="true" path="respuestas[138]"/></td>
								<td><form:checkbox value="true" path="respuestas[139]"/></td>
								<td><form:checkbox value="true" path="respuestas[140]"/></td>
								<td><form:checkbox value="true" path="respuestas[141]"/></td>
							</tr>
							<tr>
								<td>Rx IFP luxada o subluxada</td>
								<td><form:checkbox value="true" path="respuestas[142]"/></td>
								<td><form:checkbox value="true" path="respuestas[143]"/></td>
								<td><form:checkbox value="true" path="respuestas[144]"/></td>
								<td><form:checkbox value="true" path="respuestas[145]"/></td>
							</tr>
							<tr>
								<td>Rx IFD afectación leve</td>
								<td><form:checkbox value="true" path="respuestas[146]"/></td>
								<td><form:checkbox value="true" path="respuestas[147]"/></td>
								<td><form:checkbox value="true" path="respuestas[148]"/></td>
								<td><form:checkbox value="true" path="respuestas[149]"/></td>
							</tr>
							<tr>
								<td>Rx IFD destrucción ósea</td>
								<td><form:checkbox value="true" path="respuestas[150]"/></td>
								<td><form:checkbox value="true" path="respuestas[151]"/></td>
								<td><form:checkbox value="true" path="respuestas[152]"/></td>
								<td><form:checkbox value="true" path="respuestas[153]"/></td>
							</tr>
							<tr>
								<td>Rx IFD luxada o subluxada</td>
								<td><form:checkbox value="true" path="respuestas[154]"/></td>
								<td><form:checkbox value="true" path="respuestas[155]"/></td>
								<td><form:checkbox value="true" path="respuestas[156]"/></td>
								<td><form:checkbox value="true" path="respuestas[157]"/></td>
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
								<td><form:input class="form-control" path="respuestas[158]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control" path="respuestas[159]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control" path="respuestas[160]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control" path="respuestas[161]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control" path="respuestas[162]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control" path="respuestas[163]" placeholder="0 a 90" /></td>
								<td><form:input class="form-control" path="respuestas[164]" placeholder="45 a -90" /></td>
								<td><form:input class="form-control" path="respuestas[165]" placeholder="0 a 90" /></td>
							</tr>
							<tr>
								<td>IFP</td>
								<td><form:input class="form-control" path="respuestas[166]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control" path="respuestas[167]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control" path="respuestas[168]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control" path="respuestas[169]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control" path="respuestas[170]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control" path="respuestas[171]" placeholder="0 a 110" /></td>
								<td><form:input class="form-control" path="respuestas[172]" placeholder="45 a -110" /></td>
								<td><form:input class="form-control" path="respuestas[173]" placeholder="0 a 110" /></td>
							</tr>
							<tr>
								<td>IFD</td>
								<td><form:input class="form-control" path="respuestas[174]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control" path="respuestas[175]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control" path="respuestas[176]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control" path="respuestas[177]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control" path="respuestas[178]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control" path="respuestas[179]" placeholder="0 a 65" /></td>
								<td><form:input class="form-control" path="respuestas[180]" placeholder="30 a -65" /></td>
								<td><form:input class="form-control" path="respuestas[181]" placeholder="0 a 65" /></td>
							</tr>		
						</tbody>
					</table>
				</div>
			</div>		
			<div class="row">
				<div class="col-xs-12">
					<h3>Pulgar</h3>
				</div>
			</div>	
			<div class="row">
				<div class="col-xs-4">
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[182]" />
					    	Tenosinovitis digital
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[183]" />
					    	Grado I (crepitacion o gatillo)
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[184]" />
					    	Grado II (bloqueada en flexion)
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[185]" />
					    	Grado III (bloqueada en extension)
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[186]" />
					    	Grado IV (sinovial hipertrofica gralizada)
					  	</label>
					</div>
				</div>
				<div class="col-xs-4">
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
								<td><form:input class="form-control" path="respuestas[187]" placeholder="0 a 50" /></td>
								<td><form:input class="form-control" path="respuestas[188]" placeholder="45 a -50" /></td>
							</tr>
							<tr>
								<td>IF</td>
								<td><form:input class="form-control" path="respuestas[189]" placeholder="0 a 80" /></td>
								<td><form:input class="form-control" path="respuestas[190]" placeholder="20 a -80" /></td>
							</tr>							
						</tbody>
					</table>
				</div>
				<div class="col-xs-4">
					<div class="form-group row">
						<label class="col-xs-6 control-label">Apertura 1ra comisura</label>
						<div class="col-xs-6">
							<form:input class="form-control" path="respuestas[191]" placeholder="0 a 70" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[192]" />
					    	Deformidad tipo I  bouttonniere (T-MTC normal MTC-F flexa IF hiperextendida)
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[193]" />
					    	Deformidad tipo II pico de pato (T-MTC luxada 1er MTC aducto  IF hiperextendida)
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[194]" />
					    	Deformidad tipo III cuello de cisne (T-MTC luxada 1er MTC aducto MTC-F hiperextendida)
					  	</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-4">
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[195]" />
					    	Rx T-MTC afectación leve
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[196]" />
					    	Rx T-MTC osteofitosis y destrucción
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[197]" />
					    	Rx T-MTC subluxada  
					  	</label>
					</div>	
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[198]" />
					    	Rx T-MTC luxada
					  	</label>
					</div>								
				</div>
				<div class="col-xs-4">
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[199]" />
					    	Rx MTC-F afectación leve
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[200]" />
					    	Rx MTC-F luxada o sublux
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[201]" />
					    	Rx MTC-F destrucción osea
					  	</label>
					</div>					
				</div>
				<div class="col-xs-4">
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[202]" />
							Rx IF afectación leve
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[203]" />
					    	Rx IF luxada o sublux
					  	</label>
					</div>
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[204]" />
					    	Rx IF destrucción osea
					  	</label>
					</div>					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<h3>Codo</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<div class="form-group row">
						<label class="col-xs-3 control-label">Dolor</label>
						<div class="col-xs-6">
							<form:select path="respuestas[205]" class="form-control">
								<form:option value=""></form:option>
								<form:option value="2">2</form:option>
								<form:option value="4">4</form:option>
								<form:option value="6">6</form:option>
								<form:option value="8">8</form:option>
								<form:option value="10">10</form:option>
							</form:select>
						</div>
					</div>
					
					<table class="table">
						<thead>
							<tr>
								<th colspan="2">Inestabilidad</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Varo</td>
								<td><form:checkbox value="true" path="respuestas[206]" /></td>
							</tr>
							<tr>
								<td>Valgo</td>
								<td><form:checkbox value="true" path="respuestas[207]" /></td>
							</tr>
							<tr>
								<td>Anteroposterior</td>
								<td><form:checkbox value="true" path="respuestas[208]" /></td>
							</tr>
							<tr>
								<td>RCP</td>
								<td><form:checkbox value="true" path="respuestas[209]" /></td>
							</tr>
						</tbody>
					</table>
					
					<div class="checkbox">
					 	<label>
					    	<form:checkbox value="true" path="respuestas[210]" />
					    	Sinovitis
					  	</label>
					</div>
					
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[211]" />
					    	Ruido o bloqueo
					  	</label>
					</div>										
					
					<table class="table">
						<thead>
							<tr>
								<th colspan="2">Compresión cubital</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Disestesias</td>
								<td><form:checkbox value="true" path="respuestas[212]" /></td>
							</tr>
							<tr>
								<td>Tinel canal</td>
								<td><form:checkbox value="true" path="respuestas[213]" /></td>
							</tr>
							<tr>
								<td>Paresia o parálisis interóseos</td>
								<td><form:checkbox value="true" path="respuestas[214]" /></td>
							</tr>
						</tbody>
					</table>	
					
					<div class="checkbox">
					  	<label>
					    	<form:checkbox value="true" path="respuestas[215]" />
					    	Nódulos reumáticos
					  	</label>
					</div>										
				</div>
				<div class="col-xs-6">
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Flexión</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[216]" placeholder="0 a 150" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Extensión</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[217]" placeholder="10 a -150" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Pronación</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[218]" placeholder="0 a 90" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-offset-3 col-xs-3 control-label">Supinación</label>
						<div class="col-xs-3">
							<form:input class="form-control" path="respuestas[219]" placeholder="0 a 90" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<div class="form-group">
						<label for="otros">Otros</label>
						<form:textarea class="form-control" rows="3" path="respuestas[220]"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<div class="form-group">
						<label for="otros">Resumen diagnóstico</label>
						<form:textarea class="form-control" rows="3" path="respuestas[221]"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<div class="form-group">
						<label for="otros">Plan</label>
						<form:textarea class="form-control" rows="3" path="respuestas[222]"/>
					</div>
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
