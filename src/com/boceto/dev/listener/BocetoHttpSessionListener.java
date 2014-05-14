package com.boceto.dev.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.boceto.dev.manager.SessionManager;

@WebListener
public class BocetoHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		System.out.println("BocetoHttpSessionListener - sessionCreated");
		
		if(sessionEvent.getSession().isNew()){
			SessionManager.getInstance(sessionEvent.getSession().getServletContext()).saveSession(sessionEvent.getSession().getId());
			System.out.println("BocetoHttpSessionListener - sessionCreated - inserted");
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		System.out.println("BocetoHttpSessionListener - sessionDestroyed");
		
		SessionManager.getInstance(sessionEvent.getSession().getServletContext()).updateEndSessionTime(sessionEvent.getSession().getId());
		System.out.println("BocetoHttpSessionListener - sessionDestroyed - updated");
	}

}
