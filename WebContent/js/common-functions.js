/************************************
 ******* VARIABLES GLOBALES *********
 ************************************/

//Indica si hay algo en carga y se est� mostrando la capa informativa
var isSomethingLoading = false;
//Indica cuantas cosas hay cargando
var loadingCounter = 0;

/************************************
 * FUNCIONES DE LA CAPA DE CARGA
 ************************************/

function initLoadingDiv(){
	var loadingDiv = $("#loadingDiv")[0];
	loadingDiv.style.top = "-5%";
}

/**
 * Muestra la capa de carga
 */
function showLoading(message){
	
	var loadingDiv = $("#loadingDiv")[0];
	
	loadingCounter++;
	
	if(loadingCounter == 1)
		loadingDiv.innerHTML = message;
	else if(message != null && message.length > 0)
		loadingDiv.innerHTML = loadingDiv.innerHTML + "<br>" + message;
	
	if(!isSomethingLoading){
		isSomethingLoading = true;
		
		loadingDiv.style.display = "block";
		$("#loadingDiv").animate({ opacity: 0.95, top: "3%" }, 500, function() { console.log("animation complete"); });
	}
	
	startHideDaemon();
}

/**
 * Se encarga de comprobar si debe ocultar la capa de cargando
 */
function startHideDaemon(){
	if(loadingCounter<=0)
		$('#loadingDiv').animate({ opacity: "hide", top: "-5%" }, 500, function() { isSomethingLoading = false; console.log("animation complete"); });
	else
		setTimeout(startHideDaemon, 750);
}

/**
 * Indica que una de las tareas por las que se muestra la capa de cargando ha acabado
 */
function taskComplete(){
	if(loadingCounter > 0)
		loadingCounter--;
}

/**************************************
 * FIN / FUNCIONES DE LA CAPA DE CARGA
 **************************************/


/************************************
 * FUNCIONES DE LA CAPA DE RESULTADO
 ************************************/

var prevHighlightedField = "";

/**
 * Muestra la capa de resultado 'result' la cual debe estar declarada en nuestro html lo último en el body
 * @param divInsert - es el id de la capa en la que queremos mostrar el resultado
 * @param button - el id del botón que hubiese que reactivar en caso de que hubiese habido un error
 * @param data - Los datos correspondientes a la respuesta del servlet
 * @param reload - boolean que indica si se recarga la página entera con la salida nextToDo del servlet 
 */
function showResult(divInsert, button, data, reload){
	
	var type = "";
	var message = "";
	var highlightField = "";
	var nextToDo = "";
	
	type = data.messageType;
	message = data.description;
	highlightField = data.relatedField;
	nextToDo = data.nextToDo;
	
	if(data.isError){
		$("#"+button)[0].disabled = false;
	}
	
	if(type === 'success' && nextToDo && reload){
		window.location = ""+nextToDo;
	}
	else{
		//$("#"+divInsert).fadeOut("500");
		
		if(nextToDo && nextToDo.length > 0){
			$.ajax({
				async: true,
				url: nextToDo,
				success: function (data) { taskComplete(); $("#"+divInsert).append(data); },
				failure: function (errMsg) { taskComplete(); console.log(errMsg); },
				error: function(XMLHttpRequest, textStatus, errorThrown) {console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
			});
		}
		
		if(button && button.length > 0)
			$("#"+button)[0].disabled = false;
		
		if(prevHighlightedField != null && prevHighlightedField != "")
			$("#"+prevHighlightedField)[0].className = "";
		
		prevHighlightedField = highlightField;
		
		var element = $("#"+divInsert).find("#result")[0];
		
		if(element){
		
			//Si hubiese un mensaje previo, lo ocultamos y mostramos el nuevo
			if(element.style.display === "block"){
				$(element).animate({ opacity: 0.10, height: "0%" }, 225, 
					function() {
						
						if(highlightField != null && highlightField != "" && document.getElementById(highlightField))
							$("#"+highlightField)[0].className = type + "Txt";
						
						element.className = type;
						element.innerHTML = message;
						element.style.display="block";
						$(element).animate({ opacity: 0.95, height: "1em" }, 225, function() { console.log("animation complete"); });
						element.onclick = function(){hideResult();};
					}
				);
			}
			else{ //si no, mostramos inmediatamente el nuevo
				
				if(highlightField != null && highlightField != "" && document.getElementById(highlightField))
					$("#"+highlightField)[0].className = type + "Txt";
				
				element.className = type;
				$(element).css('overflow', "hidden");
				element.innerHTML = message;
				element.style.display = "block";
				$(element).animate({ opacity: 0.95, height: "1em" }, 225, function() { console.log("animation complete"); });
				element.onclick = function(){hideResult();};
			}
		}
	}
}

/**
 * Ocultar la capa de resultado
 */
function hideResult(divId){
	var element = $("#result")[0];
	
	if(element){
		$("#result").animate({ opacity: "toggle", height: "0%" }, 225, function() { console.log("animation complete"); });
	}
}

function showMessage(type, message){
	var element = $("#bodyContent").find("#result")[0];
	
	if(element){
		
		//Si hubiese un mensaje previo, lo ocultamos y mostramos el nuevo
		if(element.style.display === "block"){
			$(element).animate({ opacity: 0.10, height: "0%" }, 225, 
				function() {
					element.className = type;
					element.innerHTML = message;
					element.style.display="block";
					$(element).animate({ opacity: 0.95, height: "1em" }, 225, function() { console.log("animation complete"); });
					element.onclick = function(){hideMessage();};
				}
			);
		}
		else{ //si no, mostramos inmediatamente el nuevo
			element.className = type;
			$(element).css('overflow', "hidden");
			element.innerHTML = message;
			element.style.display = "block";
			$(element).animate({ opacity: 0.95, height: "1em" }, 225, function() { console.log("animation complete"); });
			element.onclick = function(){hideMessage();};
		}
	}
}

/**
 * Ocultar la capa de mensaje
 */
function hideMessage(){
	var element = $("#bodyContent").find("#result")[0];
	
	if(element){
		$("#bodyContent").find("#result").animate({ opacity: "toggle", height: "0%" }, 225, function() { console.log("animation complete"); });
	}
}
/*******************************
 * FIN FUNCIONES CAPA RESULTADO
 *******************************/



/**************************************
 * FUNCIONES DE GEOLOCALIZACION
 **************************************/
/**
 * Intenta averiguar cual es nuestra posici�n exacta y en caso de conseguirlo lo muestra en la capa cuyo id le pasamos como parametro 
 */
function showPosition(divId){
	if (navigator.geolocation){
		navigator.geolocation.getCurrentPosition(
	 
			function (position) {
				/*
				Did we get the position correctly?
				alert (position.coords.latitude);
				To see everything available in the position.coords array:
				for (key in position.coords) {alert(key)}
				
				Other posibilities
				position.coords.latitude			- The latitude as a decimal number
				position.coords.longitude			- The longitude as a decimal number
				position.coords.accuracy			- The accuracy of position
				position.coords.altitude			- The altitude in meters above the mean sea level
				position.coords.altitudeAccuracy	- The altitude accuracy of position
				position.coords.heading				- The heading as degrees clockwise from North
				position.coords.speed				- The speed in meters per second
				position.timestamp					- The date/time of the response
				*/
		 
				mapMyPosition(divId, position);
			}, 
			// next function is the error callback
			function (error){
				switch(error.code){
					case error.TIMEOUT:
						alert ('Timeout');
						break;
					case error.POSITION_UNAVAILABLE:
						alert ('Position unavailable');
						break;
					case error.PERMISSION_DENIED:
						alert ('Permission denied');
						break;
					case error.UNKNOWN_ERROR:
						alert ('Unknown error');
						break;
				}
			}
		);
	}
	else{
		alert("Geolocation is not supported by this browser");
	}
}

/**
 * Se le pasa una id de capa y una posici�n y nos muestra una imagen de google maps en dicha capa
 * @param position
 */
function mapMyPosition(divId, position){
	latlon=position.coords.latitude+","+position.coords.longitude;
	img_url="http://maps.googleapis.com/maps/api/staticmap?center="+latlon+"&zoom=14&size=400x300&sensor=false";
	
	document.getElementById(divId).innerHTML="<img src='"+img_url+"'>";
}

/**************************************
 * FIN - FUNCIONES DE GEOLOCALIZACION
 **************************************/









/**
 * Esta funcion coge una capa y la alarga en funcion de su contenido
 * @param divId - Capa a alargar
 * @param animationTime - Es el tiempo que tardar� la animaci�n en realizarse
 */
function enlargeDivVertically(divId, animationTime){
	
	var scrollOverflow = false;
	var newHeight = 0;
	
	$("#"+divId).clearQueue();
	
	if($("#"+divId).height() != "auto"){
	
		var currentHeight = $("#"+divId).height();
		$("#"+divId).css("height", "auto");
		
		if($("#"+divId).height() > $("#"+divId).parent().height()){
			scrollOverflow = true;
			newHeight = $("#"+divId).parent().height();
		}
		else{
			newHeight = $("#"+divId).height();
		}
	
		$("#"+divId).height(currentHeight);
		
		if(currentHeight != newHeight){
			$("#"+divId).animate( { height: newHeight }, animationTime,
															//funcion callback
															function() { 
																//si hay que mostrar un scroll en la capa, se muestra
																if(scrollOverflow){ 
																	$("#"+divId).css("overflow-y", "scroll");
																}
																
																console.log("enlargeDivVertically animation finished");
															}  );
		}
	}
}

/**
 * Esta funcion coge una capa divId y la acorta verticalmente hasta minHeight px
 * @param divId
 * @param minHeight
 * @param animationTime - Es el tiempo que tardar� la animaci�n en realizarse
 */
function shortenDivVertically(divId, minHeight, animationTime){
	
	$("#"+divId).clearQueue();
	
	var currentHeight = $("#"+divId).height();

	if(currentHeight > minHeight){
		$("#"+divId).animate( { height: minHeight }, animationTime,
														//funcion callback
														function() {
															console.log("shortenDivVertically animation finished");
														}  );
	}
}

/**
 * Funci�n que recoge un n�mero y en caso de ser menor que 10, le a�ade un cero a la izquierda
 * @param number
 * @returns
 */
function addCeroIfLessThan10(number){
	try{
		if(parseInt(number)<10)
			return "0"+number;
		else
			return number;
	}
	catch(e){
		return number;
	}
}

/**
 * Funcion que desloga un usuario
 */
function logoutUser(){
	showLoading("Disconnecting User...");
	
	$.ajax({
		async: true,
		type: "POST",
		url: "Logout",
		data: { isAjax: "true" },
		success: function (data) { 
			taskComplete();
			window.location = "./HomeLayout";
		},
		failure: function (errMsg) { taskComplete(); $('#logoutButton')[0].disabled = false; console.log(errMsg); },
		error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $('#logoutButton')[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
	});
}


/**
 * Funcion que carga la capa de datos de usuario en la cabecera del html
 */
function loadUserData(){
	showLoading("Loading User Data...");
	
	$.ajax({
		async: true,
		url: "UserInfoJSP",
		success: function (data) { taskComplete(); $("#userInfo").remove(); $("#bodyHeader").append(data); },
		failure: function (errMsg) { taskComplete(); console.log(errMsg); },
		error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $("#loginButton")[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
	});
}

/**
 * Esta funcion impide la propagaci�n de un evento
 * @param e - evento
 */
function preventPropagation(e){
	if (!e) var e = window.event;
	e.cancelBubble = true;
	if (e.stopPropagation){e.stopPropagation();}
}

function isValidImageURL(url, callback) {
    var img = new Image();
    img.onerror = function() { callback(url, false); };
    img.onload =  function() { callback(url, true); };
    img.src = url;
}