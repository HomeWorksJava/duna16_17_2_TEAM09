package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Update extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.addHeader("Refresh", "5");
		HttpSession session = request.getSession(true);
		String sqlquery = "";
		String sqlquery2 = "";
		String sqlquery3 = "";
		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/";
		String db = "auction";
		String driver = "com.mysql.jdbc.Driver";
		String user = "auction";
		String pass = "auction";
		

		sqlquery="INSERT INTO `auctions2` select * from auctions where Time_limit <= now() AND `Status`='ACTIVE'";

		sqlquery2="UPDATE `auctions2` SET `Status`='CLOSED' WHERE `Status`='ACTIVE'";

		sqlquery3="DELETE FROM `auctions` WHERE EXISTS( SELECT 1 FROM auctions2 Where auctions.id = auctions2.id)";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + db, user, pass);
			try {
				 Statement stmt = con.createStatement();
			     stmt.execute(sqlquery);
			     stmt.executeUpdate(sqlquery2);
			     stmt.execute(sqlquery3);

			} catch (SQLException s) {
				System.out.println("SQL-command not execute!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main/main.jsp");
	}

}
