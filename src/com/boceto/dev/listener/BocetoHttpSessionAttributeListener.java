package com.boceto.dev.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class BocetoHttpSessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("BocetoHttpSessionAttributeListener - attributeAdded");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("BocetoHttpSessionAttributeListener - attributeRemoved");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("BocetoHttpSessionAttributeListener - attributeReplaced");
	}

}
