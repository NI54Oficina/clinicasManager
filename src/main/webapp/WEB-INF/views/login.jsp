<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<spring:url value="/resources/js/jquery.js" var="jquery" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/styles/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/styles/styles.css" var="styleCss" />

<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
<link href="${styleCss}" rel="stylesheet" type="text/css" />
	
<script src="${jquery}" type="text/javascript"><!-- required for FF3 and Opera --></script>
<script src="${bootstrapJs}" type="text/javascript"><!-- required for FF3 and Opera --></script>
<style type="text/css">
body{
    padding-top:40px;
    padding-bottom:40px;
    background-color: #F0F2F2;
    background-image:none;
    }
.form-logo{
    max-width: 500px;
    padding: 10px 0px;
    margin: 0 auto 20px;
    text-align:center;
    }
.form-signin {
    max-width: 410px;
    padding: 19px 29px 29px;
    margin: 0 auto 20px;
    background-color: #fff;
    border: 1px solid #e5e5e5;
    -webkit-border-radius: 5px;
       -moz-border-radius: 5px;
            border-radius: 5px;
    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
       -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
.form-signin .form-signin-heading,
.form-signin .checkbox {
	margin-bottom: 10px;
}
.form-signin input[type="text"],
.form-signin input[type="password"] {
	font-size: 16px;
    height: auto;
    margin-bottom: 15px;
    padding: 7px 9px;
}
.contenedor-botones{
	margin-top:14px; overflow:hidden;
}

    </style>
</head>

<body>
	<spring:url value="/signin/google?access_type=offline&approval_prompt=force" var="form_url_google" />
	<spring:url value="/resources/j_spring_security_check" var="form_url" />

	<div class="container">
		<form class="form-signin" method="post" action="${form_url}">	
			<h2>Ingresar</h2>		
			<hr>
			<input type="text" name='j_username' class="input-block-level"
				placeholder="User"> <input type="password" name='j_password'
				class="input-block-level" placeholder="password">

			<c:if test="${not empty param.login_error }">
				<div class="alert alert-danger">
					<button type="button" class="close" data-dismiss="alert">×</button>
					Usuario o contraseña incorrectos!
				</div>
			</c:if>
			<label class="checkbox"> <input type='checkbox'
				name='_spring_security_remember_me'
				id="_spring_security_remember_me" tabindex="3" value="true" />
				Recordarme
			</label>
			<button class="btn btn-large btn-primary" type="submit">Ingresar</button>
		</form>

	</div>
	<!-- /container -->
</body>

</html>