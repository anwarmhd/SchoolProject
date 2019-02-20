package com.cog;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

	public AddServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		int emp_id1 = Integer.parseInt(request.getParameter("emp_id"));

		String emp_name1 = request.getParameter("emp_name");

		String emp_dep1 = request.getParameter("emp_dep");

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521", "system", "system");

			String sql = "insert into employee3 values(?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, emp_id1);

			pstmt.setString(2, emp_name1);

			pstmt.setString(3, emp_dep1);

			int i = pstmt.executeUpdate();

			out.println(i + "records updated");

		} catch (Exception ex) {
			out.println(ex);
		}
	}
}
