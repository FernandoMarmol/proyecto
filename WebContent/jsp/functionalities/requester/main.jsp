<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.boceto.Constants"%>
<%@ page import="com.boceto.bean.User"%>
<%@ page import="com.boceto.bean.UserSettings"%>
<%@ page import="com.boceto.dev.servlet.UserActionServlet"%>

<%User userAux1 = (User) request.getSession().getAttribute(Constants.SESSION_USER);%>

<script>

	var newRequestState = false;

	function showNewRequestForm(){
		if(newRequestState){
			newRequestState = false;
			$("#newRequestTab").css('top', '0px');
			$("#divNewRequest").animate({ opacity: 0, height: '0%' }, 500, function(){console.log("acabada la animacion1"); $("#divNewRequest").hide(); $(".expandableTab").css("visibility", "visible"); });
		}
		else{
			newRequestState = true;
			$(".expandableTab").css("visibility", "hidden");
			$(".type2").hide();
			$("#newRequestTab").css("visibility", "visible");
			$("#newRequestTab").css('top', '-2px');
			$("#divNewRequest").show().animate({ opacity: 1, height: '70%'}, 500, function(){(console.log("acabada la animacion1"));});
		}
	}
	

	var expertRegState = false;

	function showExpertRegistration(){
		if(expertRegState){
			expertRegState = false;
			$("#expRegTab").css('top', '0px');
			$("#divExpertRegistration").animate({ opacity: 0, height: '0%' }, 500, function(){console.log("acabada la animacion1"); $("#divExpertRegistration").hide(); $(".expandableTab").css("visibility", "visible");});
		}
		else{
			expertRegState = true;
			$(".expandableTab").css("visibility", "hidden");
			$(".type2").hide();
			$("#expRegTab").css("visibility", "visible");
			$("#expRegTab").css('top', '-2px');
			$("#divExpertRegistration").show().animate({ opacity: 1, height: '70%'}, 500, function(){(console.log("acabada la animacion1"));});
		}
	}
	
	function showExpertMainView(){
		showLoading("Loading Expert Main View...");
		
		$.ajax({
			async: true,
			url: "ExpertMainViewJSP",
			success: function (data) { taskComplete(); $("#bodyContent").html(data); },
			failure: function (errMsg) { taskComplete(); console.log(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $("#buttonExpertView")[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
	}
	
	function makeThisMainView(){
		$("#buttonMainViewState")[0].disabled = true;
		showLoading("User logging...");
		
		$.ajax({
			async: true,
			type: "POST",
			url: "UserAction",
			data: { isAjax: "true", action: "<%=UserActionServlet.ACTION_USER_MAIN_VIEW%>", newPreferredView: <%=UserSettings.VIEW_REQUESTER%> },
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "json",
			success: function (data) { taskComplete(); showResult("bodyContent", "buttonMainViewState", data, false); },
			failure: function (errMsg) { taskComplete(); $("#buttonMainViewState")[0].disabled = false; console.log(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $("#buttonMainViewState")[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
	}
	
	function addPhotoValidation(){
		if(document.RegistrationRequestForm.addImage.value != ""){
			isValidImageURL(document.RegistrationRequestForm.addImage.value, addPhoto);
		}
		else{
			showMessage("<%=Constants.JS_AJAX_RESULT_INFO%>", "Debes introducir una URL de una imágen");
		}
	}
	
	function addPhoto(url, validated){
		if(validated){
			if(document.RegistrationRequestForm.nrImagesURLs.value == "")
				document.RegistrationRequestForm.nrImagesURLs.value += document.RegistrationRequestForm.addImage.value;
			else
				document.RegistrationRequestForm.nrImagesURLs.value += "<%=Constants.STRING_SPLITTER_SEPARATOR%>"+document.RegistrationRequestForm.addImage.value;
			
			showMessage("<%=Constants.JS_AJAX_RESULT_SUCCESS%>", "Imágen introducida correctamente");
		}
		else{
			showMessage("<%=Constants.JS_AJAX_RESULT_ERROR%>", "No es una imágen válida");
		}
	}
	
	function createNewRequest(){
		
		$("#nrButton")[0].disabled = true;
		showLoading("Registering Petition...");
			
		$.ajax({
			async: true,
			type: "POST",
			url: "RegistrationRequest",
			data: { isAjax: "true", nrTitle: document.RegistrationRequestForm.nrTitle.value, 
									nrDesc: document.RegistrationRequestForm.nrDesc.value,
									nrEmail: document.RegistrationRequestForm.nrEmail.value,
									nrCity: document.RegistrationRequestForm.nrCity.value,
									nrAddress: document.RegistrationRequestForm.nrAddress.value,
									nrPostalCode: document.RegistrationRequestForm.nrPostalCode.value,
									nrImagesURLs: document.RegistrationRequestForm.nrImagesURLs.value },
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "json",
			success: function (data) { taskComplete(); showResult("bodyContent", "nrButton", data, true); },
			failure: function (errMsg) { taskComplete(); $("#nrButton")[0].disabled = false; console.log(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $("#nrButton")[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
		
		return false;
	}
</script>

<%@include file="/jsp/functionalities/requester/myRequests.jsp"%>

<!-- Capa de nueva peticion -->
<div id="divNewRequest" class="type2">
	<form action="RegistrationRequest" name="RegistrationRequestForm" method="post" accept-charset="UTF-8">
		<input type="text" id="nrTitle" name="nrTitle" placeholder="Título" size="125" maxlength="125"/>
		<br><br>
		<textarea id="nrDesc" name="nrDesc" placeholder="Descripción" rows="8" cols="95" maxlength="10000"></textarea>
		<br><br>
		<input type="text" id="nrEmail" name="nrEmail" placeholder="e-Mail" size="100" maxlength="100"/>
		<br><br>
		<input type="text" id="nrCity" name="nrCity" placeholder="Ciudad" size="125" maxlength="100"/>
		<br><br>
		<input type="text" id="nrAddress" name="nrAddress" placeholder="Direccion" size="125" maxlength="250"/>
		<br><br>
		<input type="text" id="nrPostalCode" name="nrPostalCode" placeholder="Código Postal" size="10" maxlength="6"/>
		<br><br><br>
		<input type="hidden" id="nrImagesURLs" name="nrImagesURLs"/>
		<input type="text" id="addImage" size="125" placeholder="Introduce la URL de la imágen"/>&nbsp; <button type="button" onclick="javascript:addPhotoValidation();">Añadir Foto</button>
		<br><br>
		<button type="submit" id="nrButton" onclick="javascript:return createNewRequest();" style="position: absolute; bottom: 0%; right: 0%; margin: 6%;">Enviar Petición</button>
	</form>
</div>

<%if(userAux1.isExpert()){%>
	<div id="menuTab" class="expandableTab" onmouseover="javascript:enlargeDivVertically('menuOptions', 250);" onmouseout="javascript:shortenDivVertically('menuOptions', '0', 250);"  style="margin-left: 25px; width: 12%;">
		<div id="menuOptions" onmouseout="javascript:preventPropagation(event);">
		
			<%if(userAux1.getSettings().getPreferredMainView() != UserSettings.VIEW_REQUESTER){%>
				<span id="buttonMainViewState" onclick="javascript:makeThisMainView();">Establecer la vista como principal</span>
			<%}%>
			
			<span id="buttonExpertView" onclick="javascript:showExpertMainView();">Ir a la vista del Experto</span>
		</div>
		<div id="menu" onmouseout="javascript:preventPropagation(event);">&nbsp;...&nbsp;</div>
	</div>
<%}
else{%>
	<!-- Capa de nuevo registro -->
	<%@include file="/jsp/functionalities/expert/registration.jsp"%>
	
	<!-- Pestaña de nuevo registro -->
	<div id="expRegTab" class="expandableTab" style="margin-left: 25px; width: 12%;">
		<div id="menuOptions">&nbsp;</div>
		<div id="menu" onclick="javascript:showExpertRegistration();">Registrarme como Experto</div>
	</div>
<%}%>

<!-- Pesaña de nueva peticion -->
<div id="newRequestTab" class="expandableTab" style="width: 12%;">
	<div id="menuOptions">&nbsp;</div>
	<div id="menu" onclick="javascript:showNewRequestForm();">Nueva Petición/Propuesta</div>
</div>

<%@include file="/jsp/layouts/common/result.jsp" %>
