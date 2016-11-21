<!doctype html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:directive.page contentType="text/html;charset=UTF-8" />
<jsp:directive.page pageEncoding="UTF-8" /> 	
<html>	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<spring:url value="/resources/js/jquery.js" var="jquery" />
		<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
		<spring:url value="/resources/styles/bootstrap.css" var="bootstrapCss" />
		<spring:url value="/resources/styles/styles.css" var="styleCss" />
		
		<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
		<link href="${styleCss}" rel="stylesheet" type="text/css" />
			
		<script src="${jquery}" type="text/javascript"><!-- required for FF3 and Opera --></script>
		<script src="${bootstrapJs}" type="text/javascript"><!-- required for FF3 and Opera --></script>
		<spring:message code="application_name" var="app_name" htmlEscape="false"/>
		<title><spring:message code="welcome_h3" arguments="${app_name}" /></title>
		
		<script>
			$.ajaxSetup({
				cache: false 
			});
		</script>
	</head>
	
  	<body>
   		<div class="container">
   			<tiles:insertAttribute name="header" />
		    <div id="main">
		    	<div class="container">
	    			<tiles:insertAttribute name="body"/>
	    		</div> 
		    </div>
		</div>
	</body>
</html>
