package com.boceto.dev.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BocetoServletRequestAttributeListener implements
		ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println("BocetoServletRequestAttributeListener - attributeAdded");
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		System.out.println("BocetoServletRequestAttributeListener - attributeRemoved");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		System.out.println("BocetoServletRequestAttributeListener - attributeReplaced");
	}

}
