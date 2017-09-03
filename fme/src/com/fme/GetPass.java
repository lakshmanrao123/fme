package com.fme;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/getPass")
public class GetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public GetPass() {
        super();
         
    }

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		Connection con = null;
		PreparedStatement ps= null;
		 
		try {
			con = DBConn.getConnection();
			 
            ps= con.prepareStatement("select password from admin where role=?");
             ps.setString(1, "admin");
            ResultSet rs= ps.executeQuery();
             
            if(rs.next()){
            	response.getWriter().write(rs.getString("password"));
            }
            
		 }
		 catch (SQLException e) {
			 
			e.printStackTrace();
		 } 
		 catch (Exception e) {

			e.printStackTrace();
		 }
		
		
	}

}
