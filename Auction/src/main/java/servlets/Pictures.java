package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;

@WebServlet(urlPatterns = { "/Pictures" })
public class Pictures  extends HttpServlet {
	
	private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	private static final String DB_Prefix = "jdbc:mysql://";

	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
	            ServletException {
	        String id = request.getParameter("id");
	        String id2 = request.getParameter("id2");
	        String query="";
	        Blob photo = null;
	        Connection con = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        
	        if(id == null && id2 != null)
	        {
	        query = "select Photo from auctions2 where id = '" + id2 + "'";	
	        }else
	        {
	        query = "select Photo from auctions where id = '" + id + "'";
	        }
	        
	        ServletOutputStream out = response.getOutputStream();

	        try {
				String dbHost = Config.getProperty("mysql.host", "localhost");
				String dbPort = Config.getProperty("mysql.port", "3306");
				String dbDatabase = Config.getProperty("mysql.database", "auction");
				String dbUsername = Config.getProperty("mysql.username", "auction");
				String dbPassword = Config.getProperty("mysql.password", "auction");
				
				Class.forName(JDBC_Driver);

				con = (Connection) DriverManager.getConnection(DB_Prefix + dbHost + ":" + dbPort + "/" + dbDatabase,
						dbUsername, dbPassword);
				stmt = (Statement) con.createStatement();
	        } catch (Exception e) {
	            response.setContentType("text/html");
	            out.println("<body><h1>Database Connection Problem.</h1></body></html>");
	            return;
	        }

	        try {
	            stmt = con.createStatement();
	            rs = stmt.executeQuery(query);
	            if (rs.next()) {
	                photo = rs.getBlob(1);

	            } else {
	                response.setContentType("text/html");
	                out.println("<body><h1>Not Found Photo.</h1></body></html>");
	                return;
	            }

	            response.setContentType("image/gif");
	            InputStream in = photo.getBinaryStream();
	            int length = (int) photo.length();

	            int bufferSize = 1024;
	            byte[] buffer = new byte[bufferSize];

	            while ((length = in.read(buffer)) != -1) {
	                out.write(buffer, 0, length);
	            }

	            in.close();
	            out.flush();
	        } catch (SQLException e) {
	            response.setContentType("text/html");
	            out.println("<body><h1>Error=" + e.getMessage() + "</h1></body></html>");
	            return;
	        } finally {
	            try {
	                rs.close();
	                stmt.close();
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}