package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.Dbconn_Reg;

public class LogServlet extends HttpServlet {
	
    private static final long serialVersionUID = 3205355555474414718L;
    
	private static final Logger Logger = LogManager.getLogger(LogServlet.class);
	
	public static String struser = "";
	
	public static String struser2 = "";

    public void init() throws ServletException
    {
    }
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	    {
	    	String command = request.getParameter("command");
	  	
	    	if (command.equals("login"))
	        {
	    		Logger.info("userlog-action started");
	            request.setCharacterEncoding("UTF-8");
	            struser = request.getParameter("username");
	            String strpass = request.getParameter("password");
	            
	            
	            Dbconn_Reg dbConnection = new Dbconn_Reg();
	            response.setStatus(200);
	            response.setContentType("text/html");
	            response.setCharacterEncoding("UTF-8");
	            dbConnection.log( struser, strpass);
	            if(dbConnection.indicator == 1){
	            	HttpSession session = request.getSession(true);
	                session.setAttribute("usrname", struser);
	            	Logger.info("User Logged in: " + struser); 
	            	response.sendRedirect("/Auction/main/main.jsp");
	            }
	            else{
	            	Logger.info("Wrong indentifier (user)");
	            	response.sendRedirect("/Auction/error/error.html");
	            }
	            
	        }
	    	if (command.equals("adminlog"))
	        {
	    		Logger.info("adminlog-action started");
	            request.setCharacterEncoding("UTF-8");
	            String struser2 = request.getParameter("username");
	            String strpass = request.getParameter("password");

	            Dbconn_Reg dbConnection = new Dbconn_Reg();
	            response.setStatus(200);
	            response.setContentType("text/html");
	            response.setCharacterEncoding("UTF-8");
	            dbConnection.adminlog( struser2, strpass);
	            if(dbConnection.indicator == 1){
	            	HttpSession session = request.getSession(true);
	                session.setAttribute("admin", struser2);
	            	Logger.info("Admin Logged in: " + struser2);
	            	response.sendRedirect("/Auction/admin/admininf.jsp");
	            }
	            else{
	            	Logger.info("Wrong indentifier (admin)");
	            	response.sendRedirect("/Auction/error/error2.html");
	            }
	            
	        }
	    	if (command.equals("reginsert"))
	        {
	    		Logger.info("reginsert-action started");
	            request.setCharacterEncoding("UTF-8");
	            String first_name = request.getParameter("first_name");
	            String last_name = request.getParameter("last_name");
	            String email = request.getParameter("email");
	            String username = request.getParameter("username");
	            String password = request.getParameter("password");

	            Dbconn_Reg dbConnection = new Dbconn_Reg();
	            response.setStatus(200);
	            response.setContentType("text/html");
	            response.setCharacterEncoding("UTF-8");
	            dbConnection.reginsert( first_name, last_name, email, username, password);
	            response.sendRedirect("/Auction/success/regsuccess.html");
	            
	        }
	    	if (command.equals("reginsert_admin"))
	        {
	    		Logger.info("reginsert_admin-action started");
	            request.setCharacterEncoding("UTF-8");
	            String first_name = request.getParameter("first_name");
	            String last_name = request.getParameter("last_name");
	            String email = request.getParameter("email");
	            String username = request.getParameter("username");
	            String password = request.getParameter("password");

	            Dbconn_Reg dbConnection = new Dbconn_Reg();
	            response.setStatus(200);
	            response.setContentType("text/html");
	            response.setCharacterEncoding("UTF-8");
	            dbConnection.reginsert( first_name, last_name, email, username, password);
	            response.sendRedirect("/Auction/success/regsuccess2.html");
	            
	        }
	        if (command.equals("regupdate"))
	        {
	        	Logger.info("regupdate-action started");
	            request.setCharacterEncoding("UTF-8");
	             int id = Integer.parseInt(request.getParameter("id6"));
	            String first_name = request.getParameter("firstname");
	            String last_name = request.getParameter("lastname");
	            String email = request.getParameter("email");
	            String username = request.getParameter("username");
	            String password = request.getParameter("password");

	            Dbconn_Reg dbConnection = new Dbconn_Reg();
	            response.setStatus(200);
	            response.setContentType("text/html");
	            response.setCharacterEncoding("UTF-8");
	            dbConnection.regupdate(id, first_name, last_name, email, username, password);
	            response.sendRedirect("/Auction/success/updsuccess.html");
	        }
	        if (command.equals("regdelete"))
	        {
	        	Logger.info("regdelete-action started");
	            request.setCharacterEncoding("UTF-8");
	            int id = Integer.parseInt(request.getParameter("userid"));

	            Dbconn_Reg dbConnection = new Dbconn_Reg();
	            response.setStatus(200);
	            response.setContentType("text/html");
	            response.setCharacterEncoding("UTF-8");
	            dbConnection.regdelete(id);
	            response.sendRedirect("/Auction/success/delsuccess.html");
	        }
	    	
	     	if (command.equals("logout"))
	        {
	     		HttpSession session  = request.getSession();
	     		try
	     		{      
	     		    session.removeAttribute("logonSessData");
	     		    session.removeAttribute("usrname");
	     		    session.removeAttribute("admin");
	     		    session.invalidate();                               
	     		    String pageToForward = request.getContextPath();
	     		    response.sendRedirect(pageToForward);
	     		    Logger.info("Logout");
	     		}
	     		catch (Exception sqle)
	     		{
	     		    System.out.println("error UserValidateServlet message : " + sqle.getMessage());
	     		    System.out.println("error UserValidateServlet exception : " + sqle);
	     		}
	        }
	    }

	    public void destroy()
	    {

	    }


}
