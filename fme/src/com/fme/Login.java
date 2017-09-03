package com.fme;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public Login() {
        super();
         
    }

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		Connection con = null;
		PreparedStatement ps= null;
		String uname= request.getParameter("username");
		String pass= request.getParameter("password");
		 PrintWriter out= response.getWriter();
		try {
			con = DBConn.getConnection();
			 
            ps= con.prepareStatement("select userid from admin where role=? and name=? and password=?");
             ps.setString(1, "admin");
             ps.setString(2, uname);
             ps.setString(3, pass);
            ResultSet rs= ps.executeQuery();
             
            if(rs.next()){
            	HttpSession session= request.getSession();
            	session.setAttribute("userid", rs.getString("userid"));
            	response.sendRedirect("dashboard.html");
            }
            else{
            	request.getRequestDispatcher("index.html").include(request, response);
            		out.println("<center><h4 style=\"color:red\">Incorrect UserName/Password</h4></center>");
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
