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


@WebServlet("/getAssignedHistory")
public class GetAssignedHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetAssignedHistory() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		ArrayList<AssignsBean> assigns=new ArrayList<AssignsBean>();
    	
		String clientid= request.getParameter("clientid");
		
		Connection con = null;
		PreparedStatement ps = null;	
		
	  
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement("select * from assigns where clientid=?");
			ps.setString(1, clientid);

			ResultSet rs = ps.executeQuery();
	        
            while(rs.next()) {	
            	
            	
            	AssignsBean ab=new AssignsBean();
            	
            	ab.setExecutiveid(rs.getString("executiveid"));
            	ab.setExecutive(rs.getString("executive"));
            	ab.setDate(rs.getString("date"));
            	
            	assigns.add(ab);
            	
            }
		 }
		 catch (SQLException e) {
			 
			e.printStackTrace();
		 } 
		 catch (Exception e) {

			e.printStackTrace();
		 }
		


			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(assigns, new TypeToken<List<AssignsBean>>() {}.getType());
	
			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
	//		System.out.println("\nJSON Object: " + jsonArray);
			
		
	}
		
}
