<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

	$(function(){
		$('.btn-remove').click(function(e){
			e.preventDefault(); 
			$.ajax({
			    url: $(this).attr('href'),
			    type: 'DELETE',
			    success: function(result) {
			    	location.reload();
			    }
			});
		});
	});

</script>

<div class="page-header">
	<h2>Configuraciones de la ficha</h2>
</div>

<table class="table table-stripped">
	<thead>
		<tr>
		    <th>Ficha</th>
		    <th>Patología</th>
		    <th>Frecuencia (semanas)</th>
		    <th></th>
		</tr>
  	</thead>
  	<tbody>
  		<c:forEach items="${configuraciones}" var="configuracion">
  			<tr>
  				<td>${configuracion.ficha.nombre }</td>
  				<td>${configuracion.fullName}</td>
  				<td>${configuracion.periodoSemanas}</td>
  				<td>
  					<a href="<c:url value='/ficha/configuracion/${configuracion.id}'/>" class="btn btn-default btn-remove">
						<span class="glyphicon glyphicon-remove"></span> Eliminar
					</a>
				</td>
  			</tr>
  		</c:forEach>
  	</tbody>
</table>
<a href="<c:url value='/ficha'/>" class="btn btn-default">Volver</a>

