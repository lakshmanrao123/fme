package com.fme;

import java.io.IOException;
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
 
@WebServlet("/assignExecutive")
public class AssignExecutive extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public AssignExecutive() {
        super();
        
    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientid= request.getParameter("clientid");
		String exeid = request.getParameter("eid");
		String exectivename=null;
	//	String mobile;
		String email = null;
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement pst = null;	
		
		Date date1 =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String date=sdf.format(date1);
		
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement("select * from admin where userid=? and role=?");
			ps.setString(1, exeid);
			ps.setString(2, "executive");

			ResultSet rs = ps.executeQuery();
	        
            if(rs.next()) {	
            	exectivename= rs.getString("name");
            //	mobile= rs.getString("mobile");
            	email= rs.getString("email");
            }
            
            pst= con.prepareStatement("insert into assigns(`clientid`,`executiveid`,`executive`,`date`,`status`) values(?,?,?,?,?)");
            pst.setString(1, clientid);
            pst.setString(2, exeid);
            pst.setString(3, exectivename);
            pst.setString(4, date);
            pst.setInt(5, 1);
            
            PreparedStatement p= con.prepareStatement("update clients set lastassigned=?, executive=? where id=?");
            p.setString(1, date);
            p.setString(2, exectivename);
            p.setString(3, clientid);
            
            int s= pst.executeUpdate();
            int s1=p.executeUpdate();

            if(s>0 && s1>0){
            	String subject="New Assignment";
    			String msg="You are Assigned to this "+clientid+" client.";
    			
    			Mailer.send(email, subject, msg);
    			
    			
            	response.getWriter().write("Successfully Assigned");
            }
            else {
            	response.getWriter().write("Failed Try Again");
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
