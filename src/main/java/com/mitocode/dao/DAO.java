package com.mitocode.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	public Connection cn;

	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}
	
	public void conectar() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitocode?user=root&password=root");	
		
    } catch (ClassNotFoundException | SQLException e) {
	        throw e;
	    }
	}
	
	public void cerrar() throws Exception {
		try {
			if (cn != null) {
				
				if (cn.isClosed() == false) {
					cn.close();
				}
			}	
		} catch (Exception e) {		
			throw e;
		}
	}

}
