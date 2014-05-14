package com.boceto.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.boceto.jaxb.elements.TodoXML;

@Path("/Todo")
public class TodoJAXB {
	
	// This can be used to test the integration with the browser
	@GET
	@Produces({ MediaType.TEXT_XML })
	public TodoXML getXML() {
		TodoXML todo = new TodoXML();
		todo.setSummary("This is my first todo1");
		todo.setDescription("This is my first todo1");
		return todo;
	}

	// This method is called if XMLis request
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public TodoXML getAppXML() {
		TodoXML todo = new TodoXML();
		todo.setSummary("This is my first todo2");
		todo.setDescription("This is my first todo2");
		return todo;
	}
}
