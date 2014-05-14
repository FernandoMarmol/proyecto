package com.boceto.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/Hello")
public class Hello {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey1";
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey2" + "</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey3" + "</title>"
				+ "<body><h1>" + "Hello Jersey3" + "</body></h1>" + "</html> ";
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHelloPOST() {
		return "<html> " + "<title>" + "Hello Jersey3" + "</title>"
				+ "<body><h1>" + "Hello Jersey3" + "</body></h1>" + "</html> ";
	}

}