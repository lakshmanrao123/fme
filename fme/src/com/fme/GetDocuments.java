package com.fme;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/getDocuments")
public class GetDocuments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public GetDocuments() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final int BUFFER_SIZE = 4096;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String id= request.getParameter("id");
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=\""+id+" Documents.zip\"");
	
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement("select * from clients where id=?");
			ps.setString(1, id);
			 
			ResultSet rs = ps.executeQuery();
			 
			OutputStream os=response.getOutputStream();

			ZipOutputStream zos = new ZipOutputStream(os);
			
			if(rs.next()){
				 
				 Blob blob = rs.getBlob("aadharcard");
				 if(blob != null){
	                InputStream inputStream = blob.getBinaryStream();
	 
	                zos.putNextEntry(new ZipEntry("Aadhar.jpg"));
	         //       response.setContentType("image/jpg");
	                int bytesRead = -1;
	                byte[] buffer = new byte[BUFFER_SIZE];
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    zos.write(buffer, 0, bytesRead);
	                    
	                }
	                zos.closeEntry();
	                inputStream.close();
				 }
	            
                Blob b = rs.getBlob("pancard");
                if(b != null){
	                InputStream i = b.getBinaryStream();
	 
	                zos.putNextEntry(new ZipEntry("pancard.jpg"));
	           
	                int br = -1;
	                byte[] buffer1 = new byte[BUFFER_SIZE];
	                while ((br = i.read(buffer1)) != -1) {
	                    zos.write(buffer1, 0, br);
	                    
	                }
	                zos.closeEntry();
	                i.close(); 
                }
                
                Blob b1 = rs.getBlob("ec");
                if(b1 != null){
	                InputStream i1 = b1.getBinaryStream();
	 
	                zos.putNextEntry(new ZipEntry("ec.jpg"));
	          
	                int br1 = -1;
	                byte[] buffer2 = new byte[BUFFER_SIZE];
	                while ((br1 = i1.read(buffer2)) != -1) {
	                    zos.write(buffer2, 0, br1);
	                    
	                }
	                zos.closeEntry();
	                i1.close(); 
                }
                
                Blob bb = rs.getBlob("gpa");
                if(bb != null){
	                InputStream ii = bb.getBinaryStream();
	 
	                zos.putNextEntry(new ZipEntry("GPA.pdf"));
	           
	                int brr = -1;
	                byte[] bufferr = new byte[BUFFER_SIZE];
	                while ((brr = ii.read(bufferr)) != -1) {
	                    zos.write(bufferr, 0, brr);
	                    
	                }
	                zos.closeEntry();
	                ii.close(); 
	            
                }
            
            
                Blob b2 = rs.getBlob("other");
                if(b2 != null){
	                InputStream i2 = b2.getBinaryStream();
	 
	                zos.putNextEntry(new ZipEntry("other.pdf"));
	           //     response.setContentType("image/jpg");
	                int br2 = -1;
	                byte[] buffer3 = new byte[BUFFER_SIZE];
	                while ((br2 = i2.read(buffer3)) != -1) {
	                    zos.write(buffer3, 0, br2);
	                    
	                }
	                zos.closeEntry();
	                i2.close(); 
	            
                }
                
                Blob bb2 = rs.getBlob("others");
                if(bb2 != null){
	                InputStream ii2 = bb2.getBinaryStream();
	 
	                zos.putNextEntry(new ZipEntry("others.pdf"));
	           
	                int brr2 = -1;
	                byte[] bufferr3 = new byte[BUFFER_SIZE];
	                while ((brr2 = ii2.read(bufferr3)) != -1) {
	                    zos.write(bufferr3, 0, brr2);
	                    
	                }
	                zos.closeEntry();
	                ii2.close(); 
	            
                }
                
                
                
		        
			}
			zos.close();
		}
		 catch (SQLException e) {
			 
			e.printStackTrace();
		 } 
		 catch (Exception e) {

			e.printStackTrace();
		 }
		
		
		
	}

}
