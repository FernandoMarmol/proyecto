<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.boceto.lang.LanguageWorker"%>

<%@ taglib uri="languageTLD" prefix="language" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Content-Type" content="text/html"/>
		
		<jsp:directive.page contentType="text/html; charset=UTF-8" /> 
		
		<title><language:getMessage key="home.title"/></title>
		
		<link rel="stylesheet" href="css/style.css" type="text/css"/>
		
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript" src="//cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
		<script type="text/javascript" src="js/common-functions.js"></script>
		<script type="text/javascript" src="js/init.js"></script>
		
	</head>
	
	<body>
		
		<div id="content">
		
			<%@include file="/jsp/layouts/common/header.jsp" %>
		
			<div id="bodyContent">
				
				<%@include file="/jsp/functionalities/common/login.jsp" %>
				
				<div class="type1" style="display: block;">
					EXPLICACIÓN DE LA WEB
				</div>
				
			</div>
			
			<%@include file="/jsp/layouts/common/footer.jsp" %>
		</div>
		
		<!-- La difeerncia entre estos dos includes son los siguientes:
			1. el primero integraría el código de la jsp en esta jsp. De esa manera, si tuviesemos declaradas dos variables con el mismo nombre, una en cada jsp, daría error de compilación
			2. el segundo no integraría el codigo sino que generaría el html resultante del jsp y lo pegaría directamente aqui, por lo que no habría problema de duplicidad de variables. Este segundo caso sin embargo es más costoso y lento -->
		<!-- < % @ include file="includeExample.jsp" % >
			 < j s p : include page="includeExample.jsp"></ j s p : include> -->
		
	</body>
	
</html>