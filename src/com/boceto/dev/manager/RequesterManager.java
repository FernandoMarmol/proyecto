package com.boceto.dev.manager;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.boceto.Constants;
import com.boceto.dev.manager.common.MainManager;

public class RequesterManager extends MainManager {

	public RequesterManager(DataSource ds) {
		super(ds);
	}
	
	public static RequesterManager getInstance(ServletContext servletContext) {
		
		RequesterManager requesterManager = (RequesterManager) servletContext.getAttribute(Constants.MANAGER_REQUESTER);
		
		synchronized (servletContext){
			if(requesterManager == null){
				requesterManager = new RequesterManager((DataSource) servletContext.getAttribute(Constants.DATA_SOURCE));
				servletContext.setAttribute(Constants.MANAGER_REQUESTER, requesterManager);
			}
		}
		
		return requesterManager;
	}

}
