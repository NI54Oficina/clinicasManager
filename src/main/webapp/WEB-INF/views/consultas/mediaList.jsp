<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>
	function refreshMediaElementsContainer(mediaElements){
		$.each(mediaElements, function(index, mediaElement) {
			$('.mediaContainer .panel-body').append(
					'<img src="<c:url value="/media/' + mediaElement.id + ' " />" class="mediaElement img-thumbnail"' + 
					'data-id="' + mediaElement.id + '" data-description="' + mediaElement.descripcion + '" />');
			$('#newMediaModal').modal('hide');
			$('#mediaSubmitButton').removeAttr('disabled');
			$('.modal-content').css('cursor','pointer');
		});
	}
	
	function zoomIn(){
		var src = $('.selectedMediaElement').attr('src');
		$('#zoomInImage').attr('src',src);
		$('#zoomInModal').modal('show');
	}
 
	$(function(){
		
		$('#zoomInButton, #deleteMediaButton').attr('disabled','true');
		
		$('body').on('click', '.mediaElement', function(clickEvent){
			$('.textAreaDisplay p').html($(this).data('description'));
			
			$('.selectedMediaElement').removeClass('selectedMediaElement');
			$(this).addClass('selectedMediaElement');
			
			$('#zoomInButton').removeAttr('disabled');	
			$('#deleteMediaButton').removeAttr('disabled');	
		});
		
		$('body').on('dblclick', '.mediaElement', zoomIn);				
		$('#zoomInButton').click(zoomIn);
		
		$('#deleteMediaButton').click(function(){
			var id = $('.selectedMediaElement').data('id');
			$.ajax({
			    type: 'DELETE',
			    url: '<c:url value="/media/"/>' + id,
			    success: function(){
			    	$('.selectedMediaElement').remove();
			    	$('.textAreaDisplay p').html('');
			    },
			    error: function(error){
			    	alert(error);
			    }
			});
		});
		
		isReadOnly();
		
		<c:if test="${empty consulta.diagnostico}">
			$('#addMediaButton').attr('disabled','true');
		</c:if>
		
	})
</script>

<div id="mediaElementsContainer">
	<div class="mediaContainer panel panel-default" style="margin-bottom: 0">
		<div class="panel-body">
			<c:forEach items="${consulta.mediaElements}" var="mediaElement">
				<img src="<c:url value='/media/${mediaElement.id}' />" class="mediaElement img-thumbnail" data-id="${mediaElement.id}" data-description="${mediaElement.descripcion}" />
			</c:forEach>
		</div>
	</div>
	<div class="panel panel-default textAreaDisplay">
		<div class="panel-body">
			<p></p>
		</div>
	</div>
	<a class="btn btn-default" id="zoomInButton"><span class="glyphicon glyphicon-zoom-in"></span></a>
	<sec:authorize access="hasRole('ADMINISTRAR_TRATAMIENTO')">
		<a class="btn btn-default" id="deleteMediaButton"><span class="glyphicon glyphicon-trash"></span></a>
		<a id="addMediaButton" href="<c:url value="/consulta/${consulta.id}/media?form"/>" class="btn btn-default modalAjax allowInReadOnly" data-target="#newMediaModal" >Subir nueva imagen</a>
	</sec:authorize>
</div>

<!-- New Media Modal -->
<div id="newMediaModal" class="modal" tabindex="-1" role="dialog"	aria-labelledby="myModalLabel" aria-hidden="true">
</div>

<!-- Zoom in Modal -->
<div id="zoomInModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-body">
		<img id="zoomInImage" src="" />
	</div>
</div>