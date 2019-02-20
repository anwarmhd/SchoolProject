package com.cog;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {

			Class.forName("oracle.jdbc.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521", "system", "system");

			String s = "insert into user1 values(?,?,?,?,?) ";

			PreparedStatement ps = con.prepareStatement(s);

			RegisterBean rb = new RegisterBean();
			
			//String name=rb.setFirstname(firstname);
			

			ps.setString(1,rb.getFirstname());
			ps.setString(2,rb.getLastname());
			ps.setString(3,rb.getUsername());
			ps.setString(4,rb.getPassword());
			ps.setString(5,rb.getConfirmpassword());

			// if (pwd.equals(cpwd)) {

			int count = ps.executeUpdate();
			out.println(count + "rows updated");

			// }

			// else {
			out.println("try again");
			// }

		}

		catch (Exception e) {

			out.println(e);
		}

	}

}
