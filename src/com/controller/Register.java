package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.Provider;

/**
 * Servlet implementation class Register
 */
@WebServlet("/com.controller.Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int status=0;
		String name = request.getParameter("username");
		String password= request.getParameter("psw");
		String repassword= request.getParameter("psw-repeat");
		if (password.equals(repassword)) {
			Connection con = Provider.getOracleConnection();
			try {
				PreparedStatement pst = con.prepareStatement("insert into mpregister values(?,?)");
				pst.setString(1, name);
				pst.setString(2, password);
				 status= pst.executeUpdate();
				
				if(status>0) {
					out.println("<h3> Registered Successfully  </h3>");
					RequestDispatcher rd = request.getRequestDispatcher("login.html");
					rd.include(request, response);
				}
				
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(status==0) {
					out.print("<h3> Username Already Exists.......<h3>");
					RequestDispatcher rd = request.getRequestDispatcher("register.html");
					rd.include(request, response);
				}
				
			}
			
			
		}
		else {
			response.sendRedirect("com.controller.RError");
		}
		
		
	}

	
}
