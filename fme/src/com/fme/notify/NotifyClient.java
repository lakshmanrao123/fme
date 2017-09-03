package com.fme.notify;

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

import com.fme.DBConn;

@WebServlet("/notifyClient")
public class NotifyClient extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		String id= request.getParameter("clientid");
		
		Connection con = null;
		PreparedStatement ps = null;	
		
	  
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement("select * from clients where id=?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			String to;
            if(rs.next()) {
            	to= rs.getString("email");
            	String link=rs.getString("sharedlink");
            	if(link==null){
            		response.getWriter().write("Please Update Shared Link");
            	}
            	else{
            		response.getWriter().write("Mail has been sent successfully");
            		String subject="Nofication from FM Enterprises";
            		String msg="New Video Has Been Uploaded Please Go through this link "+link;
            		Mailer.send(to, subject, msg);
            		
            		PreparedStatement pst= con.prepareStatement("update assigns set status= ? where clientid=? and status=?");
                    pst.setInt(1, 2);
            		pst.setString(2, id);
                    pst.setInt(3, 1);
            		pst.executeUpdate();
            		
            		
            	}
            	
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
