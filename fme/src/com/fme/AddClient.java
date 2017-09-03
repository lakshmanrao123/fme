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
 
@WebServlet("/addClient")
public class AddClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public AddClient() {
         
    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name= request.getParameter("name");
		String fname= request.getParameter("fname");
		String dob= request.getParameter("dob");
		String mobile= request.getParameter("mobile");
		String mobile2= request.getParameter("mobile2");
		String email= request.getParameter("email");
		String address= request.getParameter("address");
		String aadhar= request.getParameter("aadhar");
		String serviceareaname= request.getParameter("servicearea");
		String surveyno= request.getParameter("surveyno");
		String landaddress= request.getParameter("landaddr");
		String other= request.getParameter("other");
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
			pst= con.prepareStatement("select substring(`id`,4) id from clients as L1 where L1.id=(select max(id) from clients)");
			ResultSet r=pst.executeQuery();
			if(r.next()){
				int i= Integer.parseInt(r.getString("id"));
				i++;
				id = "FME".concat(String.format("%04d", i));  
				
			}
			else{
				id = "FME".concat(String.format("%04d", 1)); 
			}
			
			ps=con.prepareStatement("insert into clients(`id`,`name`,`fathername`,`dob`,`mobile`,`phone`,`email`,`address`,`aadhar`,`servicearea`,`surveyno`,`landaddress`,`otherdetails`,`registereddate`,`status`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			 ps.setString(1, id);
			 ps.setString(2, name);
			 ps.setString(3, fname);
			 ps.setString(4, dob);
			 ps.setString(5, mobile);
			 ps.setString(6, mobile2);
			 ps.setString(7, email);
			 ps.setString(8, address);
			 ps.setString(9, aadhar);
			 ps.setString(10, serviceareaname);
			 ps.setString(11, surveyno);
			 ps.setString(12, landaddress);
			 ps.setString(13, other);
			 ps.setString(14, date);
			 ps.setInt(15, 1);
			
			int rs=ps.executeUpdate();
			
			String subject="Registration successful";
			String msg="Dear sir, Thank you for Registration with FM Enterprises. We will serve better";
			
			if(rs>0){
				request.getRequestDispatcher("client.html").include(request, response);
			 
				Mailer.send(email, subject, msg);
				 
				out.println("<script type=\"text/javascript\">");
				out.println("$.confirm({title: 'Client Added Successfully!',content: 'Do you want to Add one more Client?',type: 'green',buttons:{No:function () {location='dashboard.html';},Yes:function (){location='client.html';}}});");
			    out.println("</script>");
 			}
			else{
				request.getRequestDispatcher("client.html").include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("$.alert({ title: 'Error!',content: 'Failed',type: 'red',buttons:{Ok:function () {location='client.html';}} });");
				out.println("</script>");
 
			}
			
		} 
		catch (SQLException e) {
 
			e.printStackTrace();
			request.getRequestDispatcher("client.html").include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("$.alert({ title: 'Error!',content: 'Something went Wrong',type: 'red',buttons:{Ok:function () {location='client.html';}} });");
			out.println("</script>");
			
		} catch (Exception e) {
 			e.printStackTrace();
 			request.getRequestDispatcher("client.html").include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("$.alert({ title: 'Error!',content: 'Something went Wrong',type: 'red',buttons:{Ok:function () {location='client.html';}} });");
			out.println("</script>");
			
		}
		finally {
			
			DBConn.clean(con,ps);
		}
		
		
		
		
		
		
	}

}
