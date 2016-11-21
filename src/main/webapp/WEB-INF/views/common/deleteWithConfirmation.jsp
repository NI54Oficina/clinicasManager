<script>
	var deleteUrl;
	var button;

	function setUpDelete(title, message, successFunction){
		$('#confirmationTitle').html(title);
		$('#confirmationMessage').html(message);
		
		if(!successFunction){
			successFunction = function() {
	    		location.reload();
			}
		}

		//Agrega el evento automáticamente a los objetos creados dinámicamente
		$(document).on('click', '.eliminarButton', function(e){
			e.preventDefault();
			button = $(this);
			deleteUrl = $(this).attr('href');
			$('#confirmarModal').modal('show');
		});
		
		$('.btn-remove').click(function(e){
			e.preventDefault(); 
			$.ajax({
			    url: deleteUrl,
			    type: 'DELETE',
			    success: successFunction,
			    error: function(error){
			    	console.log(error);
			    	alert(error.statusText);
			    }
			});
		});
	}
</script>

<div id="confirmarModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3 id="confirmationTitle"></h3>
		</div>
		<div class="modal-body">
			<p id="confirmationMessage"></p>
		</div>
		<div class="modal-footer">			
			<a class="btn btn-danger btn-remove">Eliminar</a>
			<a id="closeModalButton" data-dismiss="modal" class="btn btn-default">Cancelar</a>
		</div>
	</div>
</div>