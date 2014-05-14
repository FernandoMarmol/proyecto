package com.boceto.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Esther")
public class Esther {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloEsther() {
		return "Hola, soy Esther y me mola mi rollo";
	}
	
}
