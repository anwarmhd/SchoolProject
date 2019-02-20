package com.cog;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String user = request.getParameter("username");

		String pwd = request.getParameter("password");

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521", "system", "system");

			String ps = "select emp_name,emp_dep from employee3 where emp_name=? and emp_dep=?";

			PreparedStatement ps1 = con.prepareStatement(ps);

			ps1.setString(1, user);
			ps1.setString(2, pwd);

			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				
				
				response.sendRedirect("/gmailServlet");
				return;
			}

			response.sendRedirect("error.html");
			return;
		} catch (Exception e) {

			out.println(e);

		}
	}

}