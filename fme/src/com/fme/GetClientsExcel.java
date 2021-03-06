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


@WebServlet("/clientsExcel")
public class GetClientsExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetClientsExcel() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
		Connection con = null;
		PreparedStatement ps = null;	
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "inline; filename="+ "All Clients.xls");
		PrintWriter pw= response.getWriter();
		
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement("select * from clients");

			ResultSet rs = ps.executeQuery();
	        
			pw.println("<table align=\"left\" border=\"2\"> <thead> <tr bgcolor=\"lightgreen\"> <th>S.No.</th> <th>ClientId</th> <th>Name</th> <th>Mobile</th> <th>Email</th> <th>Address</th> <th>Land Address</th> </tr> </thead> <tbody>");
            int n=1;
			while(rs.next()) {	
            	pw.println("<tr bgcolor=\"lightblue\">");
            	pw.println("<td align=\"center\">"+n+"</td>");
            	pw.println("<td align=\"center\">"+rs.getString("id")+"</td>");
            	pw.println("<td align=\"center\">"+rs.getString("name")+"</td>");
            	pw.println("<td align=\"center\">"+rs.getString("mobile")+"</td>");
            	pw.println("<td align=\"center\">"+rs.getString("email")+"</td>");
            	pw.println("<td align=\"center\">"+rs.getString("address")+"</td>");
            	pw.println("<td align=\"center\">"+rs.getString("servicearea")+", "+rs.getString("surveyno")+", "+rs.getString("landaddress")+"</td>");
            	pw.println("</tr>");
            	n++; 
            }
			pw.println("</tbody></table>");
		 }
		 catch (SQLException e) {
			 
			e.printStackTrace();
		 } 
		 catch (Exception e) {

			e.printStackTrace();
		 }
		
	}
		
}
