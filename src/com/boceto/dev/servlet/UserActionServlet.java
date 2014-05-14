package com.boceto.dev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boceto.Constants;
import com.boceto.dev.manager.UserManager;
import com.boceto.dev.servlet.common.MainLoggedServlet;
import com.boceto.dev.servlet.common.Message;
import com.boceto.lang.LanguageWorker;

@WebServlet( name="UserActionServlet", displayName="UserAction Servlet", urlPatterns = {"/UserAction"} )
public class UserActionServlet extends MainLoggedServlet {

	public static final String ACTION_USER_MAIN_VIEW = "ACTION_USER_MAIN_VIEW";
	
	@Override
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message message = new Message();
		message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
		message.setDescription(LanguageWorker.getMessage("login.message.error.general"));
		
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase(ACTION_USER_MAIN_VIEW)){
			int newPreferredView = Integer.parseInt(request.getParameter("newPreferredView"));
			
			UserManager.getInstance(getServletContext()).updateUserPreferredMainView(getUserFromSession(request.getSession()).getId(), newPreferredView);
			getUserFromSession(request.getSession()).getSettings().setPreferredMainView(newPreferredView);
			
			message.setMessageType(Constants.JS_AJAX_RESULT_SUCCESS);
			message.setDescription(LanguageWorker.getMessage("login.message.error.general"));
		}
		
		return message;
		
	}

	@Override
	public Message validate(HttpServletRequest request) {
		
		Message message = null;
		
		try{
			String action = request.getParameter("action");
			if(action.equalsIgnoreCase(ACTION_USER_MAIN_VIEW)){
				String newPreferredView = request.getParameter("newPreferredView");
				
				if(newPreferredView == null || newPreferredView.equalsIgnoreCase("")){
					message = new Message(true);
					message.setDescription(LanguageWorker.getMessage("login.message.emptydata"));
					message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
				}
			}
		}
		catch(Exception e){}
		
		return message;
	}

}
