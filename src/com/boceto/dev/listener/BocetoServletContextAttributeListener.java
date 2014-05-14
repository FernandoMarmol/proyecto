package com.boceto.dev.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BocetoServletContextAttributeListener implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("BocetoServletContextAttributeListener - attributeAdded");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		System.out.println("BocetoServletContextAttributeListener - attributeRemoved");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		System.out.println("BocetoServletContextAttributeListener - attributeReplaced");
	}

}
