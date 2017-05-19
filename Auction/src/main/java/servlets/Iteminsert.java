package servlets;
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class Iteminsert extends HttpServlet {
    
	private static final Logger Logger = LogManager.getLogger(Iteminsert.class);
	
    private String dbURL = "jdbc:mysql://localhost:3306/auction";
    private String dbUser = "auction";
    private String dbPass = "auction";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String stritem = request.getParameter("Item_name");
        String strcategories = request.getParameter("Categories");

        InputStream inputStream = null;

        Part filePart = request.getPart("Photo");
        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            inputStream = filePart.getInputStream();
        }
        int strhigh = Integer.parseInt(request.getParameter("Highest_bid"));
        String strtime = request.getParameter("Time_limit"); 
        
         
        Connection conn = null;
         
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            Logger.info("MySQL Connection Created: host name: " + dbURL + ", Port: " + dbUser);
 
            String query = " insert into auctions ( Item_name, Categories, Photo, Highest_bid, Time_limit)" + " values ( ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, stritem);
			statement.setString(2, strcategories);
            if (inputStream != null) {
                statement.setBlob(3, inputStream);
            }
			statement.setInt(4, strhigh);
			statement.setString(5, strtime);
             

            int row = statement.executeUpdate();
            if (row > 0) {     	
            	Logger.info("Added Data: " + stritem + " " + strcategories + " " + filePart.getName() + " " + filePart.getContentType() + " " + strtime);
            }
        } catch (SQLException ex) {
        	Logger.error("Database Connection Error: Iteminsert-action");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                	Logger.error("Failure execution: Iteminsert-action");
                    ex.printStackTrace();
                }
            }
            response.sendRedirect("/Auction/success/inssuccess.html");
        }
    }
}