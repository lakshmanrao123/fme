package com.fme;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

 
@WebServlet("/uploadFiles")
@MultipartConfig(maxFileSize = 101177215)
public class UploadFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public UploadFiles() {
        super();
         
    }

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientid = request.getParameter("clientid");
		String type = request.getParameter("type");
	//	System.out.println(clientid+"::"+type);
		InputStream inputStream = null;

		Part filePart = request.getPart("photo");
		inputStream = filePart.getInputStream();
	/*	if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			inputStream = filePart.getInputStream();
		}
*/			
		Connection con = null;
		PreparedStatement ps = null;

		try {
		 	
			con = DBConn.getConnection();
			String sql = null;
			if(type.equals("aadhar")){
				sql = "update clients set aadharcard=? where id=?";
			}
			else if(type.equals("pancard")){
				sql = "update clients set pancard=? where id=?";
			}
			else if(type.equals("ec")){
				sql = "update clients set ec=? where id=?";
			}
			else if(type.equals("gpa")){
				sql = "update clients set gpa=? where id=?";
			}
			else if(type.equals("other")){
				sql = "update clients set other=? where id=?";
			}
			else if(type.equals("others")){
				sql = "update clients set others=? where id=?";
			}
			
			ps = con.prepareStatement(sql);
			ps.setBlob(1, inputStream);
			ps.setString(2, clientid);
			
			@SuppressWarnings("unused")
			int r= ps.executeUpdate();
		//	System.out.println(r);
			
			 
		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
		}
		
	}

}
