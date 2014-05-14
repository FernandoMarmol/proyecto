<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.boceto.Constants"%>
<%@ page import="com.boceto.bean.User"%>
<%@ page import="com.boceto.bean.UserSettings"%>
<%@ page import="com.boceto.dev.servlet.UserActionServlet"%>

<%User userAux1 = (User) request.getSession().getAttribute(Constants.SESSION_USER);%>

<script>
	function showRequesterMainView(){
		showLoading("Loading Requester Main View...");
		
		$.ajax({
			async: true,
			url: "RequesterMainViewJSP",
			success: function (data) { taskComplete(); $("#bodyContent").html(data); },
			failure: function (errMsg) { taskComplete(); console.log(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $("#buttonRequesterView")[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
	}
	
	function makeThisMainView(){
		$("#buttonMainViewState")[0].disabled = true;
		showLoading("User logging...");
		
		$.ajax({
			async: true,
			type: "POST",
			url: "UserAction",
			data: { isAjax: "true", action: "<%=UserActionServlet.ACTION_USER_MAIN_VIEW%>", newPreferredView: <%=UserSettings.VIEW_EXPERT%> },
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "json",
			success: function (data) { taskComplete(); showResult("bodyContent", "buttonMainViewState", data, false); },
			failure: function (errMsg) { taskComplete(); $("#buttonMainViewState")[0].disabled = false; console.log(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $("#buttonMainViewState")[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
	}
</script>

<%@include file="/jsp/functionalities/expert/myProfile.jsp"%>

<div class="expandableTab" style="margin-left: 25px;" onmouseover="javascript:enlargeDivVertically('menuOptions', 250);" onmouseout="javascript:shortenDivVertically('menuOptions', '0', 250);">
	<div id="menuOptions" onmouseout="javascript:preventPropagation(event);">
		
		<%if(userAux1.getSettings().getPreferredMainView() != UserSettings.VIEW_EXPERT){%>
			<span id="buttonMainViewState" onclick="javascript:makeThisMainView();">Establecer la vista como principal</span>
		<%} %>
		
		<span id="buttonRequesterView" onclick="javascript:showRequesterMainView();">Ir a la vista del solicitante</span>
	</div>
	<div id="menu" onmouseout="javascript:preventPropagation(event);">&nbsp;...&nbsp;</div>
</div>

<%@include file="/jsp/layouts/common/result.jsp" %>