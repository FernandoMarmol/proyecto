<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.boceto.lang.LanguageWorker"%>

<div id="bodyHeader">
	
	<div id="loadingDiv"><%=LanguageWorker.getMessage("loading.primary.message") %></div>

	<span id="showTime"></span>
	
	<div style="position: absolute; top: 15%; width: 100%; line-height: 1; font-size: 30px; text-align: center;"><!-- QUÉ APLICACIÓN--></div>
	
	<%@include file="/jsp/functionalities/common/userInfo.jsp" %>
	
</div>