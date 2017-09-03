package com.fme;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fme.notify.Mailer;
 
@WebServlet("/addExecutive")
public class AddExecutive extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddExecutive() {
         
    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name= request.getParameter("name");
		String fname= request.getParameter("fname");
		String dob= request.getParameter("dob");
		String mobile= request.getParameter("mobile");
		String email= request.getParameter("email");
		String address= request.getParameter("address");
		String aadhar= request.getParameter("aadhar");
		String id;
		
		Date date1 =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String date=sdf.format(date1);
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement pst = null;
		 
		
		try {
			
			con = DBConn.getConnection();
			pst= con.prepareStatement("select substring(`userid`,4) userid from admin as L1 where L1.userid=(select max(userid) from admin)");
			ResultSet r=pst.executeQuery();
			if(r.next()){
				int i= Integer.parseInt(r.getString("userid"));
				i++;
				id = "FEX".concat(String.format("%03d", i));  
				
			}
			else{
				id = "FEX".concat(String.format("%03d", 1)); 
			}
			 
			ps=con.prepareStatement("insert into admin(`userid`,`name`,`role`,`fathername`,`dob`,`mobile`,`email`,`address`,`aadhar`,`date`) values(?,?,?,?,?,?,?,?,?,?)");
			
			 ps.setString(1, id);
			 ps.setString(2, name);
			 ps.setString(3, "executive");
			 ps.setString(4, fname);
			 ps.setString(5, dob);
			 ps.setString(6, mobile);
			 ps.setString(7, email);
			 ps.setString(8, address);
			 ps.setString(9, aadhar);
			 ps.setString(10, date);
			
			int rs=ps.executeUpdate();
			String subject="Nofication from FM Enterprises";
    		String msg="Dear "+name+", Thank you For joining in FME..";
			
			if(rs>0){
				
				Mailer.send(email, subject, msg);
				request.getRequestDispatcher("executive.html").include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("$.confirm({title: 'Executive Added Successfully!',content: 'Do you want to Add one more Executive?',type: 'green',buttons:{No:function () {location='dashboard.html';},Yes:function (){location='executive.html';}}});");
			    out.println("</script>");
				
 			}
			else{
				request.getRequestDispatcher("executive.html").include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("$.alert({ title: 'Error!',content: 'Failed',type: 'dark',buttons:{Ok:function () {location='executive.html';}} });");
				out.println("</script>");
 
			}
			
		} 
		catch (SQLException e) {
 
			e.printStackTrace();
			request.getRequestDispatcher("executive.html").include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("$.alert({ title: 'Error!',content: 'Something went Wrong',type: 'dark',buttons:{Ok:function () {location='executive.html';}} });");
			out.println("</script>");
			
			
		} catch (Exception e) {
 			e.printStackTrace();
 			request.getRequestDispatcher("executive.html").include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("$.alert({ title: 'Error!',content: 'Something went Wrong',type: 'dark',buttons:{Ok:function () {location='executive.html';}} });");
			out.println("</script>");
		}
		finally {
			
			DBConn.clean(con,ps);
		}
		
		
		
		
		
		
	}

}
