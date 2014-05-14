<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.boceto.Constants"%>
<%@ page import="com.boceto.bean.User"%>
<%@ page import="com.boceto.lang.LanguageWorker"%>

<div id="userInfo" class="type1">
	<%
	User userAux = null;
	if(request.getSession().getAttribute(Constants.SESSION_USER) != null){
		userAux = (User) request.getSession().getAttribute(Constants.SESSION_USER); %>
	
		<%=userAux.getName()%>&nbsp;(<%=userAux.getEmail()%>)
		
		<button type="button" id="logoutButton" onclick="javascript:logoutUser();" draggable="true"><%=LanguageWorker.getMessage("logout.button.send") %></button>
	<%
	}
	else {%>
		No ha accedido ningún usuario
	<%}%>
</div>