package com.boceto.dev.manager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.boceto.Constants;
import com.boceto.dev.manager.common.MainManager;

public class ExpertManager extends MainManager {

	public ExpertManager(DataSource ds) {
		super(ds);
	}
	
	public static ExpertManager getInstance(ServletContext servletContext) {
		
		ExpertManager expertManager = (ExpertManager) servletContext.getAttribute(Constants.MANAGER_EXPERT);
		
		synchronized (servletContext){
			if(expertManager == null){
				expertManager = new ExpertManager((DataSource) servletContext.getAttribute(Constants.DATA_SOURCE));
				servletContext.setAttribute(Constants.MANAGER_EXPERT, expertManager);
			}
		}
		
		return expertManager;
	}
	
	/**
	 * Registra un nuevo usuario como experto en la BBDD
	 * @param name
	 * @param email
	 * @param pwd
	 * @return - true o false en funci�n de si se ha podido o no registrar el nuevo experto
	 */
	public boolean registerExpert(int id, String name, String email){
		boolean registered = false;
		
		PreparedStatement stmt = null;
		try {
			String insert = "INSERT INTO experts (EXPERT_ID, EXPERT_NAME, EXPERT_EMAIL) VALUES ('" + id + "', '" + name + "', '" + email + "')";
			
			stmt = getPreparedStatement(insert);
			stmt.executeUpdate();
			
			registered = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stmt.getConnection().close();
				stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return registered;
	}

}
