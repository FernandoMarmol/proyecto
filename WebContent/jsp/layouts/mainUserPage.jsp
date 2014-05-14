<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.boceto.lang.LanguageWorker"%>
<%@ page import="com.boceto.bean.UserSettings"%>

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
	
	<body style="-webkit-transition: left 0.5s ease-in-out;">
	
		<%User user = (User) request.getSession().getAttribute(Constants.SESSION_USER); %>
		
		<div id="content">
		
			<%@include file="/jsp/layouts/common/header.jsp" %>
		
			<div id="bodyContent">
				
				<%if(user.isExpert() && (user.isFirstTimeExpert() || user.getSettings().getPreferredMainView() == UserSettings.VIEW_EXPERT)){%>
					<%@include file="/jsp/functionalities/expert/main.jsp"%>
				<%}
				else{ %>
					<%@include file="/jsp/functionalities/requester/main.jsp"%>
				<%} %>

			</div>
			
			<%@include file="/jsp/layouts/common/footer.jsp" %>
		</div>
		
	</body>
	
</html>