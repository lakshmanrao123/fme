package com.fme;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet("/checkSession")
public class CheckSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public CheckSession() {
        super();
         
    }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		HttpSession session= request.getSession();
		
		if(session.getAttribute("userid")==null){
 			
			response.getWriter().write("fail");
	 	}
		else{
			response.getWriter().write("pass");
		}
		
	}

}
