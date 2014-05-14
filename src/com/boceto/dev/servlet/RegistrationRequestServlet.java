package com.boceto.dev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import com.boceto.Constants;
import com.boceto.bean.User;
import com.boceto.dev.manager.RequestManager;
import com.boceto.dev.servlet.common.MainLoggedServlet;
import com.boceto.dev.servlet.common.MainServlet;
import com.boceto.dev.servlet.common.Message;
import com.boceto.lang.LanguageWorker;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet( name="RegistrationRequestServlet", displayName="RegistrationRequest Servlet", urlPatterns = {"/RegistrationRequest"} )
public class RegistrationRequestServlet extends MainLoggedServlet {

	
	/**
	 * @see MainServlet#customExecute(String method, HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean registered = false;
		
		Message message = new Message();
		message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
		message.setDescription(LanguageWorker.getMessage("registration.request.message.error.general"));
		message.setNextToDo("MainUserPage");
		
		User user = getUserFromSession(request.getSession());
		if(user.isRequester()){
		
			String title = request.getParameter("nrTitle");
			String description = request.getParameter("nrDesc");
			String city = request.getParameter("nrCity");
			String address = request.getParameter("nrAddress");
			String postalCode = request.getParameter("nrPostalCode");
			String email = request.getParameter("nrEmail");
			
			String[] images = null;
			String imagesUrls = request.getParameter("nrImagesURLs");
			if(imagesUrls != null && !imagesUrls.equalsIgnoreCase(""))
				images = imagesUrls.split(Constants.STRING_SPLITTER_SEPARATOR);
			
			registered = RequestManager.getInstance(getServletContext()).newRequest(user.getId(), title, description, email, city, address, postalCode, images);
		
			message.setNextToDo("MainUserPage");
			if(registered){
				message.setMessageType(Constants.JS_AJAX_RESULT_SUCCESS);
				message.setDescription(LanguageWorker.getMessage("registration.request.message.success"));
			}
			else{
				message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
				message.setDescription(LanguageWorker.getMessage("registration.request.message.error"));
			}
		}
		
		return message;
	}
	
	@Override
	public Message validate(HttpServletRequest request) {
		
		Message message = null;
		
		try{
			String title = request.getParameter("nrTitle");
			String description = request.getParameter("nrDesc");
			
			String email = request.getParameter("nrEmail");
			
			if(title.equalsIgnoreCase("") || description.equalsIgnoreCase("")){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("registration.request.message.emptydata"));
				message.setMessageType(Constants.JS_AJAX_RESULT_INFO);
				message.setRelatedField("nrTitle");
			}
			
			if(message == null && email != null && !email.equalsIgnoreCase("") && !EmailValidator.getInstance().isValid(email)){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("registration.request.message.error.email"));
				message.setMessageType(Constants.JS_AJAX_RESULT_ERROR);
				message.setRelatedField("nrEmail");
			}
		}
		catch(Exception e){}
		
		return message;
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("RegistrationRequestServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("RegistrationRequestServlet - init()");
	}
	
}
