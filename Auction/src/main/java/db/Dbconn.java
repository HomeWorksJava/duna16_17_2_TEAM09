package db;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.*;

import config.Config;

public class Dbconn {
	private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	private static final String DB_Prefix = "jdbc:mysql://";

	private static final Logger Logger = LogManager.getLogger(Dbconn.class);
	
	public void bidding(int id, int bid, String usrn) {
		Connection con = null;
		Statement stmt = null;
		String query = "";

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
			
			Logger.info("MySQL Connection Created: host name: " + dbHost + ", Port: " + dbPort + ", Database:" + dbDatabase);
			
			
			String query2 = "SELECT Highest_bid FROM auctions WHERE id='" + id + "'";
			ResultSet rs = stmt.executeQuery(query2);
			int bidhelp=0;
			while(rs.next())
			{
				bidhelp = rs.getInt("Highest_bid");
			}
			double comp = 0;
			comp= bidhelp*1.05;

			if(bid != 0 && bid > comp)
			{
			query = "UPDATE auctions SET Highest_bid=?, Bidders_usrname=? WHERE id='" + id + "'";
			}
			
			PreparedStatement prestmt = con.prepareStatement(query);

			prestmt.setInt(1, bid);
			prestmt.setString(2, usrn);
			
			prestmt.executeUpdate();
			
			Logger.info("Updated Data: " + id + " " + bid + " " + usrn);

			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			Logger.error("Database Connection Error: bidding-action");
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.error("Failure execution: bidding-action");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {

	}
}
