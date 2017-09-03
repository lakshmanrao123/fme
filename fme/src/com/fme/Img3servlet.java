package com.fme;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Img3servlet
 */
@WebServlet("/img3servlet")
public class Img3servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Img3servlet() {
        super();
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

System.out.println("Called");
			Connection con=null;
		  		
		  		try {
					con=DBConn.getConnection();
					PreparedStatement ps=con.prepareStatement("select * from clients where id=?");
					String id=request.getParameter("id");
					System.out.println(id);
					ps.setString(1, id);
					ResultSet rs=ps.executeQuery();
					rs.next();
					Blob b=rs.getBlob("aadharcard");
					response.setContentType("image/jpeg");
					response.setContentLength((int) b.length());
					InputStream is= b.getBinaryStream();
					OutputStream os=response.getOutputStream();
					byte buff[]=new byte[(int) b.length()];
					is.read(buff);
					os.write(buff);
					os.close();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
		  		


		
		}

}
