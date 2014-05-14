package com.boceto.dev.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.boceto.Constants;
import com.boceto.bean.Request;
import com.boceto.dev.manager.common.MainManager;

public class RequestManager extends MainManager {

	public RequestManager(DataSource ds) {
		super(ds);
	}
	
	public static RequestManager getInstance(ServletContext servletContext) {
		
		RequestManager requestManager = (RequestManager) servletContext.getAttribute(Constants.MANAGER_REQUEST);
		
		synchronized (servletContext){
			if(requestManager == null){
				requestManager = new RequestManager((DataSource) servletContext.getAttribute(Constants.DATA_SOURCE));
				servletContext.setAttribute(Constants.MANAGER_REQUEST, requestManager);
			}
		}
		
		return requestManager;
	}
	
	/**
	 * Registra una nueva peticion en la BBDD
	 * @param requesterId
	 * @param title
	 * @param email
	 * @param description
	 * @param city
	 * @param address
	 * @param postalCode
	 * @param imagesUrls
	 * @return
	 */
	public boolean newRequest(int requesterId, String title, String description, String email, String city, String address, String postalCode, String[] imagesUrls){
		boolean registered = false;
		
		int requestId = -1;
		PreparedStatement stmt = null;
		try {
			String insert = " INSERT INTO 0 (REQUESTER_ID, REQUEST_TITLE, REQUEST_DESC, REQUEST_MAIL, REQUEST_CITY, REQUEST_ADDRESS, REQUEST_POSTAL_CODE, REQUEST_CREATION_TIME, REQUEST_MODIFICATION_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE(), SYSDATE()) ";
			
			stmt = getPreparedStatement(insert);
			stmt.setInt(1, requesterId);
			stmt.setString(2, title);
			stmt.setString(3, description);
			stmt.setString(4, email);
			stmt.setString(5, city);
			stmt.setString(6, address);
			stmt.setString(7, postalCode);
			stmt.executeUpdate();
			stmt.close();
			
			String selectId = " SELECT MAX(REQUEST_ID) RID FROM requests WHERE REQUESTER_ID = ? ";
			stmt = getPreparedStatement(selectId);
			stmt.setInt(1, requesterId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				requestId = rs.getInt("RID");
			}
			
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
		
		if(registered && requestId != -1 && imagesUrls != null){
			attachImagesToRequest(requestId, imagesUrls);
		}
		
		return registered;
	}
	
	public boolean attachImagesToRequest(int requestId, String[] imagesUrls){
		boolean attached = true;
		if(imagesUrls != null){
			
			PreparedStatement stmt = null;
			for(String url : imagesUrls){
				try {
					String insert = " INSERT INTO requests_images (REQUEST_ID, REQUEST_IMAGE_URL) VALUES (?, ?) ";
					stmt = getPreparedStatement(insert);
					stmt.setInt(1, requestId);
					stmt.setString(2, url);
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
		
		return attached;
	}
	
	/**
	 * Obtiene las requests de un usuario
	 * @return - ArrayList con las requests
	 */
	public ArrayList<Request> getRequestsFromUser(int userId){
		ArrayList<Request> lista = new ArrayList<Request>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Request request = null;
		try {
			String select = " SELECT REQUEST_ID, REQUESTER_ID, REQUEST_STATUS_ID, REQUEST_MAIL, REQUEST_TITLE, REQUEST_DESC, REQUEST_ADDRESS, REQUEST_CITY, REQUEST_POSTAL_CODE, REQUEST_CREATION_TIME, REQUEST_MODIFICATION_TIME FROM requests WHERE REQUESTER_ID = " + userId + " ORDER BY REQUEST_CREATION_TIME DESC ";
			
			stmt = getPreparedStatement(select);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				request = new Request();
				request.setTitle(rs.getString("REQUEST_TITLE"));
				request.setDesc(rs.getString("REQUEST_DESC"));
				lista.add(request);
			}
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
		return lista;
	}

}
