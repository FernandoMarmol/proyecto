package com.boceto.dev.servlet.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boceto.Constants;
import com.google.gson.Gson;

/**
 * @author fmm
 *
 */
public abstract class MainServlet extends HttpServlet {

	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(METHOD_GET, request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		execute(METHOD_POST, request, response);
	}

	protected void execute(String method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENCODING!! " + request.getCharacterEncoding());
		System.out.println("METHOD!! " + method);
		
		boolean isAjax = false;
		
		if(request.getParameter("isAjax") != null && request.getParameter("isAjax").equalsIgnoreCase("true")){
			isAjax = true;
		}
		
		Message message = validate(request);
		
		if(message == null){
			message = customExecute(request, response);
		}
		
		if(isAjax && message != null){
			response.setContentType("application/json");
			// Get the printwriter object from response to write the required json object to the output stream      
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			out.print(gson.toJson(message));
			out.flush();
			
			// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
			/*out.print("{ \"resultMessage\" : \""+resultMessage+"\"," +
						"\"highlightField\" : \""+highlightField+"\"," +
					    "\"type\" : \"" + ajaxResultType + "\" }");*/
		}
		else{
			request.setAttribute(Constants.MESSAGE, message);
			
			if(message != null && message.getNextToDo() != null)
				request.getRequestDispatcher(message.getNextToDo()).forward(request, response);
		}
	}
	
	/**
	 * En este método irá el código a ejecutar por cada servlet
	 */
	public abstract Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * En este método se validarán los datos de la request
	 * @return - Message Error en caso de haberlo o null si no lo hay 
	 */
	public abstract Message validate(HttpServletRequest request);

}
