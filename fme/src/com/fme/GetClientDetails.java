package com.fme;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;


@WebServlet("/getClientDetails")
public class GetClientDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetClientDetails() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		ArrayList<ClientsBean> client=new ArrayList<ClientsBean>();
    	
		String clientid= request.getParameter("clientid");
		
		Connection con = null;
		PreparedStatement ps = null;	
		
	  
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement("select * from clients where id=?");
			ps.setString(1, clientid);

			ResultSet rs = ps.executeQuery();
	        
            while(rs.next()) {	
            	/*Blob b = rs.getBlob("aadhar");
                byte buff[] = b.getBytes(1, (int)b.length());
                
                      String encodedVersion = Base64.encodeBase64URLSafeString(buff);
                   System.out.println("Encoded Version is " + encodedVersion);
                   System.out.println(buff);
                   */
              
                
            	ClientsBean cb=new ClientsBean();
            	cb.setId(rs.getString("id"));
            	cb.setName(rs.getString("name"));
            	cb.setMobile(rs.getString("mobile"));
            	cb.setFathername(rs.getString("fathername"));
            	cb.setMobile2(rs.getString("phone"));
            	cb.setDob(rs.getString("dob"));
            	cb.setEmail(rs.getString("email"));
            	cb.setSharedlink(rs.getString("sharedlink"));
            	cb.setAddress(rs.getString("address"));
            	cb.setAadhar(rs.getString("aadhar"));
            	cb.setLandaddr(rs.getString("servicearea")+", "+rs.getString("surveyno")+", "+rs.getString("landaddress"));
            	
            	client.add(cb);
            	
            }
		 }
		 catch (SQLException e) {
			 
			e.printStackTrace();
		 } 
		 catch (Exception e) {

			e.printStackTrace();
		 }
		


			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(client, new TypeToken<List<ClientsBean>>() {}.getType());
	
			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
	//		System.out.println("\nJSON Object: " + jsonArray);
			
		
	}
		
}
