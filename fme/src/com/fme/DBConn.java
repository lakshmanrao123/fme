package com.fme;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConn {
	
	public static void clean(Connection con, PreparedStatement ps)
	 {
	   try
	   { if ( ps != null )  ps.close();
	     if ( con != null) con.close();
	   }
	    catch(Exception ex) { 
	    	ex.getMessage(); 
	    }
	 }

	 public static Connection  getConnection() throws Exception
	 
	 {
		 
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());	
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fme","root","");
	    return con;
	 }
	 
	 public Connection  getConnection1() throws Exception
	 {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());	
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fme","root","");
	    return con;
	 }

}
