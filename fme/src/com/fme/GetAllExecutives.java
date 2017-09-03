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


@WebServlet("/allExecutives")
public class GetAllExecutives extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetAllExecutives() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		ArrayList<ExecutiveBean> executives=new ArrayList<ExecutiveBean>();
    		
		Connection con = null;
		PreparedStatement ps = null;	
		 
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement("select userid, name,mobile from admin where role=?");
			ps.setString(1, "executive");

			ResultSet rs = ps.executeQuery();
	        
            while(rs.next()) {	
            	
            	
            	ExecutiveBean eb=new ExecutiveBean();
            	
            	eb.setUserid(rs.getString("userid"));
            	eb.setName(rs.getString("name"));
            	eb.setMobile(rs.getString("mobile"));
            	
            	executives.add(eb);
            	
            }
		 }
		 catch (SQLException e) {
			 
			e.printStackTrace();
		 } 
		 catch (Exception e) {

			e.printStackTrace();
		 }
		


			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(executives, new TypeToken<List<ExecutiveBean>>() {}.getType());
	
			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			 
		
	}
		
}
