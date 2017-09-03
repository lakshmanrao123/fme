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

 
@WebServlet("/updateEmail")
public class UpdateEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public UpdateEmail() {
        super();
         
    }

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientid= request.getParameter("clientid");
		String email= request.getParameter("email");
		
		Connection con = null;
		PreparedStatement ps= null;
		 
		try {
			con = DBConn.getConnection();
			 
            ps= con.prepareStatement("update clients set email=? where id=?");
            ps.setString(1, email);
            ps.setString(2, clientid);
            
            int rs= ps.executeUpdate();
             
            if(rs>0){
            	response.getWriter().write("success");
            }
            else {
            	response.getWriter().write("fail");
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
