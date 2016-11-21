<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="header">
	<sec:authorize access="isAuthenticated()">
		<ul class="nav nav-pills">
			<li class="active"><a href="<c:url value="/"/>">Home</a></li>
				<li class="dropdown pull-right">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						<sec:authentication property="principal.username" />
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/resources/j_spring_security_logout'/>">Logout</a></li>
						<sec:authorize access="hasRole('ADMINISTRAR_FICHAS')">
							<li><a href="<c:url value='/ficha'/>">Fichas</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('BUSCAR_PACIENTES')">
							<li><a href="<c:url value='/buscar/paciente'/>">Buscar pacientes</a></li>
						</sec:authorize>	
						<sec:authorize access="hasRole('BUSCAR_CONSULTAS')">
							<li><a href="<c:url value='/buscar/consulta'/>">Buscar consultas</a></li>
						</sec:authorize>	
						<sec:authorize access="hasRole('ADMINISTRAR_USUARIOS')">
							<li><a href="<c:url value='/usuarios'/>">Usuarios</a></li>
						</sec:authorize>	
						<sec:authorize access="hasRole('ADMINISTRAR_GRUPOS')">
							<li><a href="<c:url value='/grupos'/>">Grupos</a></li>
						</sec:authorize>
					</ul>
				</li>
		</ul>
	</sec:authorize>
</div>