package com.boceto.dev.listener;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.boceto.Constants;

@WebListener
public class BocetoServletContextListener implements ServletContextListener {

	@Resource(name="jdbc/proyecto")
	private DataSource ds;
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("BocetoServletContextListener - contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("BocetoServletContextListener - contextInitialized");
		
		try{
			/*Con la inyección de código del atributo ds ya no necesito hacer esto*/
			//Context context = new InitialContext();
			//DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/projecto");
			
			ServletContext servletContext = servletContextEvent.getServletContext();
			servletContext.setAttribute(Constants.DATA_SOURCE, ds);
			
			//Language parameter
			String lp = servletContext.getInitParameter("lang_package");
			servletContext.setAttribute(Constants.LANG_PACKAGE, lp);
		}
		catch(Exception e){
			System.out.println("BocetoServletContextListener - contextInitialized - ERROR DATASOURCE");
		}
	}

}
