<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value='/resources/js/bootstrap-modalmanager.js'/>"	type="text/javascript"></script>
<script src="<c:url value='/resources/js/bootstrap-modal.js'/>"	type="text/javascript"></script>
<link rel="stylesheet" href="<c:url value='/resources/styles/bootstrap-modal-bs3patch.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/styles/bootstrap-modal.css'/>">
<script type="text/javascript">
	$.fn.modal.defaults.maxHeight = function() {
	    // subtract the height of the modal header and footer
	    return $(window).height() - 165;
    }

    $.fn.modal.defaults.spinner = $.fn.modalmanager.defaults.spinner = 
    	'<div class="loading-spinner" style="width: 200px; margin-left: -100px;">' + 
    		'<div class="progress progress-striped active">' + 
    			'<div class="progress-bar" style="width: 100%;"></div>' +
    		'</div>' + 
    	'</div>';
    
    function loadModal(button, callback){
    	var modal = $(button.data('target'));
		
		//Si el modal no fue cargado, se carga. Sino se muestra lo cargado anteriormente.
		if(!modal.html().trim()){
			$('body').modalmanager('loading');
		 
	     	modal.load(button.attr('href'), '', function(){
				modal.modal();
				if(callback){
					callback();
				}
	    	});
		}
     	else {
     		modal.modal();
     	}
    }
    	
    $(function(){
		$('.modalAjax').on('click', function(e){
			e.preventDefault(); 
			loadModal($(this));
		});		
    });	
</script>