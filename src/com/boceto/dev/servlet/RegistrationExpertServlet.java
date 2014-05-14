package com.boceto.dev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boceto.Constants;
import com.boceto.bean.User;
import com.boceto.dev.manager.ExpertManager;
import com.boceto.dev.servlet.common.MainLoggedServlet;
import com.boceto.dev.servlet.common.MainServlet;
import com.boceto.dev.servlet.common.Message;
import com.boceto.lang.LanguageWorker;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet( name="RegistrationExpertServlet", displayName="RegistrationExpert Servlet", urlPatterns = {"/RegistrationExpert"} )
public class RegistrationExpertServlet extends MainLoggedServlet {

	/**
	 * @see MainServlet#customExecute(String method, HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message message = new Message();
		message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
		message.setDescription(LanguageWorker.getMessage("registration.expert.message.error.general"));
		
		User user = getUserFromSession(request.getSession());
		if(!user.isExpert()){
		
			boolean registered = ExpertManager.getInstance(getServletContext()).registerExpert(user.getId(), user.getName(), user.getEmail());
			user.setExpert(registered);
			user.setFirstTimeExpert(registered);
		
			message.setNextToDo("MainUserPage");
			if(registered){
				
				message.setMessageType(Constants.JS_AJAX_RESULT_SUCCESS);
				message.setDescription(LanguageWorker.getMessage("registration.expert.message.success"));
			}
			else{
				message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
				message.setDescription(LanguageWorker.getMessage("registration.expert.message.error"));
			}
		}
		
		return message;
	}
	
	
	@Override
	public Message validate(HttpServletRequest request) {
		return null;
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("RegistrationExpertServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("RegistrationExpertServlet - init()");
	}

}
