package com.boceto.dev.manager.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public abstract class MainManager {

	protected DataSource dataSource;
	
	public MainManager(DataSource ds){ dataSource = ds; }
	
	protected Connection getConnection(boolean autoCommit) throws SQLException {

		Connection con = null;
		try{
			con = dataSource.getConnection();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			throw ex;
		}

		con.setAutoCommit(autoCommit);
		return con;
	}
	
	/**
	 * @return - Autocommit Connection
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		return getConnection(true);
	}

	/**
	 * Cierra una conexion
	 * @param con
	 */
	protected void closeConnection(Connection con) {
		try {
			if ((con!=null) && !con.isClosed())
				con.close();
		}
		catch (SQLException se) {}
	}

	/**
	 * Cierra un statement
	 * @param stmt
	 */
	protected void closeStatement(Statement stmt) {
		if (stmt!=null) {
			try {
				Connection con = stmt.getConnection();
				stmt.close();
				if (con.getAutoCommit())
					closeConnection(con);
			}
			catch (SQLException e) {}
		}
	}

	protected PreparedStatement getPreparedStatement(String call) throws SQLException {
		return getPreparedStatement(call, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, true);
	}
	protected PreparedStatement getPreparedStatement(String call, boolean autoCommit) throws SQLException {
		return getConnection(autoCommit).prepareStatement(call, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	}
	protected PreparedStatement getPreparedStatement(String call, Connection con) throws SQLException {
		return con.prepareStatement(call, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	}
	protected PreparedStatement getPreparedStatement(String call,int rsType,int rsConcurrency,boolean autoCommit) throws SQLException {
		return getConnection(autoCommit).prepareStatement(call, rsType, rsConcurrency);
	}
	protected PreparedStatement getPreparedStatement(String call,int rsType,int rsConcurrency) throws SQLException {
		return getPreparedStatement(call, rsType, rsConcurrency, true);
	}
	protected Statement getStatement() throws SQLException{
		return getConnection(true).createStatement();
	}

}
