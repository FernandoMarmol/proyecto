package com.boceto.dev.manager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.boceto.Constants;
import com.boceto.dev.manager.common.MainManager;

public class SessionManager extends MainManager {

	public SessionManager(DataSource ds) {
		super(ds);
	}
	
	public static SessionManager getInstance(ServletContext servletContext) {
		
		SessionManager sessionManager = (SessionManager) servletContext.getAttribute(Constants.MANAGER_SESSION);
		
		synchronized (servletContext){
			if(sessionManager == null){
				sessionManager = new SessionManager((DataSource) servletContext.getAttribute(Constants.DATA_SOURCE));
				servletContext.setAttribute(Constants.MANAGER_SESSION, sessionManager);
			}
		}
		
		return sessionManager;
	}
	
	/**
	 * Guarda la session en base de datos
	 * @param sessionId
	 */
	public void saveSession(String sessionId){
		
		PreparedStatement stmt = null;
		try{
			String insert = "INSERT INTO sessions (SESSION_ID, SESSION_BEGINS) VALUES ('" + sessionId + "', sysdate())";
			stmt = getPreparedStatement(insert);
			stmt.executeUpdate();
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
	}
	
	/**
	 * Guarda el momento en el que una sessi�n termina
	 * @param sessionId
	 */
	public void updateEndSessionTime(String sessionId){
		
		PreparedStatement stmt = null;
		try{
			String update = "UPDATE sessions SET SESSION_ENDS = SYSDATE() WHERE SESSION_ID = '" + sessionId + "'";
			stmt = getPreparedStatement(update);
			stmt.executeUpdate();
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
	}
	
	/**
	 * Asocia el usuario a la sesion cuando este se loga
	 * @param userId
	 * @param sessionId
	 */
	public void associateUserToSession(int userId, String sessionId){
		
		PreparedStatement stmt = null;
		try{
			String update = "UPDATE sessions SET USER_ID = " + userId + ", USER_LOGS_IN = SYSDATE() WHERE SESSION_ID = '" + sessionId + "'";
			stmt = getPreparedStatement(update);
			stmt.executeUpdate();
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
	}
	
	/**
	 * Guarda el userAgent (identificador del navegador) asociado a la session
	 * @param userAgent
	 */
	public void associateUserAgentToSession(String userAgent){
		
		PreparedStatement stmt = null;
		try{
			String update = "UPDATE sessions SET USER_AGENT = '" + userAgent + "'";
			stmt = getPreparedStatement(update);
			stmt.executeUpdate();
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
	}

}
