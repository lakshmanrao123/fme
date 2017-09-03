package com.fme;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/updatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public UpdatePassword() {
        super();
         
    }

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newpass= request.getParameter("password");
		 
		Connection con = null;
		PreparedStatement ps= null;
		 
		try {
			con = DBConn.getConnection();
			 
            ps= con.prepareStatement("update admin set password=? where role=?");
            ps.setString(1, newpass);
            ps.setString(2, "admin");
            
            int rs= ps.executeUpdate();
             
            if(rs>0){
            	response.getWriter().write("Password Successfully Updated!!!");
            }
            else {
            	response.getWriter().write("Failed.,Try Again..");
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
