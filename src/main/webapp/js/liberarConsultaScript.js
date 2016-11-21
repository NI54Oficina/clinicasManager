function liberarConsultaYSalir() {
	liberarConsulta();
	alert('Debido a inactividad, se ha liberado la edición de la consulta. Se procederá a la página principal');
	window.location.href = '<c:url value="/"/>'
}
setTimeout(liberarConsultaYSalir, 60000);

$(window).bind('beforeunload', function(e) {
	liberarConsulta();
});

function liberarConsulta() {
	$.get('<c:url value="/consulta/${consulta.id}/liberar" />' + '?q=' + $.now());
}