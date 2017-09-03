package com.fme;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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


@WebServlet("/allClients")
public class GetAllClients extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetAllClients() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		ArrayList<ClientsBean> clients=new ArrayList<ClientsBean>();
    		
		Connection con = null;
		PreparedStatement ps = null;	
		
		Date today =new Date();
	//	SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		 
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement("select * from clients");

			ResultSet rs = ps.executeQuery();
	        
            while(rs.next()) {	
            	
            	
            	ClientsBean cb=new ClientsBean();
            	cb.setId(rs.getString("id"));
            	cb.setName(rs.getString("name"));
            	cb.setMobile(rs.getString("mobile"));
            	int days,status = 1;
           // 	Date d1;
            	if(rs.getString("lastassigned")==null){
            		// d1= today;//rs.getDate("registereddate");
            	 
            		days=0;
            	}
            	else{
            		Date d1= rs.getDate("lastassigned");
            		days =15-(int) ( today.getTime() - d1.getTime())/(24 * 60 * 60*1000 );
            		PreparedStatement p=con.prepareStatement("select status from assigns where clientid=? and date=?");
            		p.setString(1, rs.getString("id"));
            		p.setString(2, rs.getString("lastassigned"));
            		ResultSet r= p.executeQuery();
            		
            		if(r.next()){
            			status= r.getInt("status");
            		}
            		 
            	}
            	 
            	cb.setDays(days);
            	cb.setStatus(status);
            	
            	clients.add(cb);
            	
            }
		 }
		 catch (SQLException e) {
			 
			e.printStackTrace();
		 } 
		 catch (Exception e) {

			e.printStackTrace();
		 }
		


			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(clients, new TypeToken<List<ClientsBean>>() {}.getType());
	
			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
	//		System.out.println("\nJSON Object: " + jsonArray);
			
		
	}
		
}
