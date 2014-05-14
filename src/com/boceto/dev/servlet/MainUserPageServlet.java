package com.boceto.dev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boceto.Constants;
import com.boceto.bean.User;
import com.boceto.dev.manager.RequestManager;
import com.boceto.dev.servlet.common.MainLoggedServlet;
import com.boceto.dev.servlet.common.MainServlet;
import com.boceto.dev.servlet.common.Message;

/**
 * Servlet implementation class BranchRegistrationServlet
 */
@WebServlet( name="MainUserPageServlet", displayName="MainUserPage Servlet", urlPatterns = {"/MainUserPage"} )
public class MainUserPageServlet extends MainLoggedServlet {
	
	/**
	 * @see MainServlet#customExecute(HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = getUserFromSession(request.getSession());
		
		Message message = new Message();
		message.setNextToDo("/MainUserPageLayout");
		
		if(user.isRequester()){
			request.setAttribute(Constants.LIST_REQUESTS, RequestManager.getInstance(getServletContext()).getRequestsFromUser(user.getId()));
		}
		
		if(user.isExpert()){
			
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
		System.out.println("MainUserPageServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("MainUserPageServlet - init()");
	}
	
}
