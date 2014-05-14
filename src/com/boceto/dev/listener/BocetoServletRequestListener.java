package com.boceto.dev.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.boceto.dev.manager.SessionManager;

@WebListener
public class BocetoServletRequestListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		System.out.println("BocetoHttpSessionListener - requestInitialized");
		
		/*Si la sesión es nueva guardamos ciertos datos en la BBDD*/
		if(((HttpServletRequest) requestEvent.getServletRequest()).getSession().isNew()){
			SessionManager.getInstance(requestEvent.getServletContext()).associateUserAgentToSession(((HttpServletRequest) requestEvent.getServletRequest()).getSession().getId());
			System.out.println("BocetoHttpSessionListener - Actualizado User Agent");
		}
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent requestEvent) {
		System.out.println("BocetoHttpSessionListener - requestDestroyed");
	}
}
