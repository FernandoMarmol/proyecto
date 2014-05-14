$(document).ready(function() {
	
	//Iniciamos los tooltips
	$("div[title]").tooltip( { position: "bottom center",
							effect: "fade",
							fadeInSpeed: 150, 
							fadeOutSpeed: 150,
							tipClass: "tooltip",
							opacity: 0.75,
							predelay: 1750,
							delay: 0 } );
	
	//Iniciamos la capa de informacion de cargando
	initLoadingDiv();
	
	//init clock if present
	setInterval(function(){ if(document.getElementById("showTime")){
		var d1 = new Date();
		document.getElementById("showTime").innerHTML = "<strong>"+addCeroIfLessThan10(d1.getHours())+":"+addCeroIfLessThan10(d1.getMinutes())+":"+addCeroIfLessThan10(d1.getSeconds())+" "+d1.getUTCDate()+"."+(d1.getUTCMonth()+1)+"."+d1.getUTCFullYear()+"</strong>";
	}}, 1000);
});


/*WINDOW ONRESIZE - JQUERY AND NOT JQUERY FUNCTIONS*/
/*$(window).resize(function(){});
window.onresize = function(){};*/