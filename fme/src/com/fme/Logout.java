package com.fme;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		if(session !=null){
		session.invalidate();
		}
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		out.println("<script type=\"text/javascript\" src=\"assets/plugins/jquery/js/jquery-3.1.1.min.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"assets/custom/jquery-confirm.js\"></script>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/custom/jquery-confirm.css\" />");
		
		out.println("<script type=\"text/javascript\">");
	//   out.println("$.alert({ title: 'Enaql Alert',content: 'User Logged out Successfully!!', type: 'red',buttons: { OK: function(){ window.location.href = \"index.html\"; }}});");
		out.println("alert('logged out Successfully')");
		out.println("location='index.html';");
	   
	    out.println("</script>");
	}

}
