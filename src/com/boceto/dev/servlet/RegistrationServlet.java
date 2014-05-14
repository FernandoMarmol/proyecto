package com.boceto.dev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import com.boceto.Constants;
import com.boceto.dev.manager.UserManager;
import com.boceto.dev.servlet.common.MainServlet;
import com.boceto.dev.servlet.common.Message;
import com.boceto.lang.LanguageWorker;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet( name="RegistrationServlet", displayName="Registration Servlet", urlPatterns = {"/Registration"} )
public class RegistrationServlet extends MainServlet {

	/**
	 * @see MainServlet#customExecute(String method, HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message message = new Message();
		
		message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
		message.setDescription(LanguageWorker.getMessage("registration.message.error.general"));
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		boolean registered = UserManager.getInstance(getServletContext()).registerUser(name, email, pwd);
		
		if(registered){
			
			request.getSession().setAttribute(Constants.SESSION_USER, UserManager.getInstance(getServletContext()).getUser(email, pwd));
			
			message.setMessageType(Constants.JS_AJAX_RESULT_SUCCESS);
			message.setDescription(LanguageWorker.getMessage("registration.message.success"));
			
			message.setNextToDo("MainUserPage");
		}
		else{
			message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
			message.setDescription(LanguageWorker.getMessage("registration.message.error"));
		}
		
		return message;
	}
	
	
	@Override
	public Message validate(HttpServletRequest request) {
		
		Message message = null;
		
		try{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			
			
			if(name.equalsIgnoreCase("") || email.equalsIgnoreCase("") || pwd.equalsIgnoreCase("")){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("registration.message.emptydata"));
				message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
				message.setRelatedField("name");
			}
			
			if(message == null && !EmailValidator.getInstance().isValid(email)){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("registration.message.error.email"));
				message.setMessageType(Constants.JS_AJAX_RESULT_ERROR);
				message.setRelatedField("email");
			}
		}
		catch(Exception e){}
		
		return message;
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("RegistrationServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("RegistrationServlet - init()");
	}

}
