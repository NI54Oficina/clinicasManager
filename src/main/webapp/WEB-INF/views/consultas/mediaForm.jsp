<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery.form.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/jquery.validate.min.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="<c:url value='/resources/js/script.js'/>" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script>
	$(function(){
		
		var form = $('#mediaUploadForm'); 
		
		form.ajaxForm({
	    	beforeSubmit: function() {
	    		if(form.valid()){
	    			$('#mediaSubmitButton').attr('disabled','disabled');
	    			$('.modal-content').css('cursor','wait');
	    		}
	            return form.valid();
	        },
			success: function(mediaElements) { 
				$('#mediaSubmitButton').attr("disabled","disabled");
				refreshMediaElementsContainer(mediaElements);
			},
	        error: function(response) {
	        	$('#mediaSubmitButton').removeAttr('disabled');
	        	$('.modal-content').css('cursor','auto');
	        	alert(response.statusText);
	        }
		});
		
		form.validate(); 	
		
		$('#addFileInput').click(function(e) {
			e.preventDefault();
			$('#fileInputGroup').append('<input name="file" type="file" accept="image/*" class="required"/>');
		});
		
	})
</script>

<spring:url value="/consulta/${consultaId}/fileUpload" var="uploadUrl"/>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">Imagen upload</h3>
	</div>
	<div class="modal-body">
		<form action="${uploadUrl}" method="POST" id="mediaUploadForm" enctype="multipart/form-data" role="form">
			<div id="fileInputGroup" class="form-group">
				<label class="control-label" for="exampleInputFile">File input</label>
				<input name="file" type="file" accept="image/*" class="required"/>				
			</div>
			<div class="form-group">
				<a id="addFileInput">Agregar otro archivo</a>
			</div>
			<div class="form-group">
				<label for="media_descripcion">Descripción</label>
				<textarea name="description" id="media_descripcion" class="form-control" ></textarea>
			</div>
			<input type="submit" value="Guardar" id="mediaSubmitButton" class="btn btn-primary"/>
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancelar</button>
		</form>
	</div>
</div>
