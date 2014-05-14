<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.boceto.lang.LanguageWorker"%>
<%@ page import="com.boceto.Constants"%>

<script type="text/javascript">
	function executeLogin(event){
		if(event.which == 13) { //Si es enter
			event.preventDefault();
			loginUser();      
		}
	}
	
	function loginUser(){
		$("#loginButton")[0].disabled = true;
		showLoading("User logging...");
		
		$.ajax({
			async: true,
			type: "POST",
			url: "Login",
			data: { isAjax: "true", email: document.LoginForm.emailLogin.value, pwd: document.LoginForm.pwdLogin.value },
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "json",
			success: function (data) { taskComplete(); showResult("loginDiv", "loginButton", data, true); },
			failure: function (errMsg) { taskComplete(); $("#loginButton")[0].disabled = false; console.log(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $("#loginButton")[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
	}
	
	function showRegistration(){
		showLoading("Showing Registration Info...");
		
		$.ajax({
			async: true,
			url: "RegistrationJSP",
			success: function (data) { taskComplete(); $("#loadFunctionality").append(data); $("#loadFunctionality").animate({ opacity: "show" }, 500, function() { });},
			failure: function (errMsg) { taskComplete(); console.log(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) {console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
	}
</script>

<!-- Capa de Login -->
<div id="loginDiv" class="type1">
	<%if(request.getSession().getAttribute(Constants.SESSION_USER) == null){ %>
		<label><%=LanguageWorker.getMessage("login.title") %></label>
		
		<br><br>
		
		<form action="LoginServlet" name="LoginForm" method="post">
		
			<input type="text" name="emailLogin" id="emailLogin" placeholder="<%=LanguageWorker.getMessage("login.email") %>"/>
			<input type="password" name="pwdLogin" id="pwdLogin" placeholder="<%=LanguageWorker.getMessage("login.pwd") %>" onkeypress="javascript:executeLogin(event);"/>
		
			<button type="button" id="loginButton" onclick="javascript:loginUser();" draggable="true"><%=LanguageWorker.getMessage("login.button.send") %></button>
			
			<div id="noRegisteredDiv"><span class="subText">¿No tiene cuenta y desea registrarse?</span>&nbsp;<button type="button" class="subButton" id="regButton" onclick="javascript:showRegistration();" draggable="true"><%=LanguageWorker.getMessage("registration.button") %></button></div>
			<div id="loadFunctionality" style="display: none;"></div>
		</form>
	<%}
	else{%>
		<label><%=LanguageWorker.getMessage("login.message.user.already.logged")%></label>
		
		<button type="button" id="logoutButton" onclick="javascript:logoutUser();" draggable="true"><%=LanguageWorker.getMessage("logout.button.send") %></button>
	<%}%>
	
	<%@include file="/jsp/layouts/common/result.jsp" %>
	
</div>