package db;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import config.Config;

public class Dbconn_Reg {
	
	private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	private static final String DB_Prefix = "jdbc:mysql://";
	public static int indicator = 0;
	
	private static final Logger Logger = LogManager.getLogger(Dbconn.class);

	public void log(String username, String password) 
	{
		Connection con = null;
		Statement stmt = null;
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
			
			String sql = "SELECT username, password FROM user";
			ResultSet rs = stmt.executeQuery(sql);

			rs = stmt.executeQuery(
					"select * from user where username='" + username + "' and password='" + password + "'");
			
			if (username == null || password == null || username == "" || password == "") {
				indicator = 0;
			} else {
				if (rs.next()) {
					indicator = 1;
				}

				else {
					indicator = 0;
				}

			}
			
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			Logger.error("Database Connection Error: log-action");
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.error("Failure execution: log-action");
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
	public void adminlog(String username, String password) 
	{
		Connection con = null;
		Statement stmt = null;
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
			
			String sql = "SELECT username, password FROM adminlog";
			ResultSet rs = stmt.executeQuery(sql);

			rs = stmt.executeQuery(
					"select * from adminlog where username='" + username + "' and password='" + password + "'");
			
			if (username == null || password == null || username == "" || password == "") {
				indicator = 0;
			} else {
				if (rs.next()) {
					indicator = 1;
				}

				else {
					indicator = 0;
				}

			}
			
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			Logger.error("Database Connection Error: adminlog-action");
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.error("Failure execution: adminlog-action");
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
	public void reginsert(String first_name, String last_name, String email, String username, String password) {
		
		Connection con = null;
		Statement stmt = null;

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

			String query = " insert into user (first_name, last_name, email, username, password, regdate)" 
							+ " values ( ?, ?, ?, ?, ?, CURDATE())";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, first_name);
			preparedStmt.setString(2, last_name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, username);
			preparedStmt.setString(5, password);

			preparedStmt.execute();
			
			Logger.info("User Data Added:" + first_name + " " + last_name + " " + email + " " + username + " " + password);

			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			Logger.error("Database Connection Error: reginsert-action");
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.error("Failure execution: reginsert-action");
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
	public void regupdate(int id, String first_name, String last_name, String email, String username, String password) {
		Connection con = null;
		Statement stmt = null;

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

			String query = "UPDATE user SET first_name =?, last_name=?, email=?, username=?, password=?, regdate=CURDATE() WHERE id='" + id + "'";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, first_name);
			preparedStmt.setString(2, last_name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, username);
			preparedStmt.setString(5, password);

			preparedStmt.execute();
			
			Logger.info("User Data Updated: " + id + " " + first_name + " " + last_name + " " + email + " " + username + " " + password);

			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			Logger.error("Database Connection Error: regupdate-action");
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.error("Failure execution: regupdate-action");
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
	public void regdelete(int id) {
		Connection con = null;
		Statement stmt = null;

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

			String query = "DELETE from user WHERE id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, id);

			preparedStmt.execute();
			
			Logger.info("User Data Deleted: " + id);
			

			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			Logger.error("Database Connection Error: regdelete-action");
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.error("Failure execution: regdelete-action");
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




}
