<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
	var readOnly = false;
	
	function setUpReadOnly(){
		$('button, a, input[type=submit]').attr('disabled','disabled');
		$('.allowInReadOnly').removeAttr('disabled');
		$(':input').attr('readonly','true');
		readOnly = true;
	}
	
	function isReadOnly(){
		if(readOnly){
			setUpReadOnly();
		}
	}
</script>
<c:choose>
	<c:when test="${not consulta.estado.dadoDeAlta}">
		<div id="idletimeout">
		</div>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.idletimeout.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.idletimer.js" />"></script>
		<script type="text/javascript">
		
			var liberar = true;
			
			$(function(){
				$.ajax({
					type: 'GET',
					url: '<c:url value="/consulta/${consulta.id}/keepAlive"/>',
					success: function(){
						$.idleTimeout('#idletimeout', '#idletimeout a', {
							idleAfter: 900,
							warningLength: 0,
							pollingInterval: 60,
							keepAliveURL: '<c:url value="/consulta/${consulta.id}/keepAlive"/>',
							serverResponseEquals: '',
							onTimeout: function(){
							},
							onIdle: function(){
								liberarConsultaYSalir();
							},
							onCountdown: function( counter ){
							},
							onResume: function(){
							}
						});
		
					    $(window).bind('beforeunload', function(e) {
					    	if(liberar){
						    	liberarConsulta();
					    	}
					    });
					},
					error: function(error){				
						setUpReadOnly();
						alert('Consulta bloqueada: ' + error.statusText);
					}
				});		
				
				$('.noLiberar').click(function(){
					liberar = false;
				})
				
			})
		
		    function liberarConsulta() {
				$.ajax({
			        type: 'GET',
			        async: false,
			        url: '<c:url value="/consulta/${consulta.id}/liberar" />' + '?q=' + $.now()
			    });
		    }
		    
			function liberarConsultaYSalir() {
			    liberarConsulta();
			    liberar = false;
			    alert('Debido a inactividad, se ha liberado la edición de la consulta. Se procederá a la página principal');
			    window.location.href = '<c:url value="/"/>';
			    
		    }
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			$(function(){
				setUpReadOnly();
			})
		</script>
	</c:otherwise>
</c:choose>

