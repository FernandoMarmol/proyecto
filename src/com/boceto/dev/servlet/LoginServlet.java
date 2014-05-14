package com.boceto.dev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import com.boceto.Constants;
import com.boceto.bean.User;
import com.boceto.dev.manager.SessionManager;
import com.boceto.dev.manager.UserManager;
import com.boceto.dev.servlet.common.MainServlet;
import com.boceto.dev.servlet.common.Message;
import com.boceto.lang.LanguageWorker;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet( name="LoginServlet", displayName="Login Servlet", urlPatterns = {"/Login"} )
public class LoginServlet extends MainServlet {
	
	/**
	 * @see MainServlet#customExecute(HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message message = new Message();
		
		message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
		message.setDescription(LanguageWorker.getMessage("login.message.error.general"));
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		User user = UserManager.getInstance(getServletContext()).getUser(email, pwd);
		if(user != null){
			message.setMessageType(Constants.JS_AJAX_RESULT_SUCCESS);
			message.setDescription(LanguageWorker.getMessage("login.message.success"));
			
			request.getSession().setAttribute(Constants.SESSION_USER, user);
			
			message.setNextToDo("MainUserPage");
			
			SessionManager.getInstance(getServletContext()).associateUserToSession(user.getId(), request.getSession().getId());
		}
		else{
			message.setMessageType(Constants.JS_AJAX_RESULT_ERROR);
			message.setDescription(LanguageWorker.getMessage("login.message.error"));
			message.setRelatedField("emailLogin");
		}
		
		return message;
	}

	@Override
	public Message validate(HttpServletRequest request) {
		Message message = null;
		
		try{
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			
			if(email.equalsIgnoreCase("") || pwd.equalsIgnoreCase("")){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("login.message.emptydata"));
				message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
				message.setRelatedField("emailLogin");
			}
			
			if(message == null && !EmailValidator.getInstance().isValid(email)){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("login.message.error.email"));
				message.setMessageType(Constants.JS_AJAX_RESULT_ERROR);
				message.setRelatedField("emailLogin");
			}
		}
		catch(Exception e){}
		
		return message;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("LoginServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("LoginServlet - init()");
	}

}
