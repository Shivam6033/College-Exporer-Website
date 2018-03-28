package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * Servlet implementation class Login
 */
@WebServlet("/com.controller.Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
int status=0;
	String username= request.getParameter("uname");
	String password= request.getParameter("psw");
	Connection con = Provider.getOracleConnection();
	try {
	
		PreparedStatement pst = con.prepareStatement("select * from mpregister where name=? and password=?");
		pst.setString(1, username);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
		
			response.sendRedirect("home_.html");
		}
		if(!rs.next()) {
			out.print("<h3> Invalid USername/ Password........ <h3>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block

			
		
		e.printStackTrace();
		
		}
	}
	
	}

	


