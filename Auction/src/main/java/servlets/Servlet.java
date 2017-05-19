package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.Dbconn;

public class Servlet extends HttpServlet
{

    private static final long serialVersionUID = 3205355555474414718L;
    
    private static final Logger Logger = LogManager.getLogger(Servlet.class);

    public void init() throws ServletException
    {
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	request.setCharacterEncoding("UTF-8");
    	String command = request.getParameter("command");
    	
        if (command.equals("search"))
        {
            String search = request.getParameter("search");
            String searchcategories = request.getParameter("searchcategories");
            request.setAttribute("search", search);
            request.setAttribute("searchcategories", searchcategories);
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main/search.jsp");
    		dispatcher.forward(request, response);
        }
    	
        if (command.equals("myauction"))
        {
            String usrn = LogServlet.struser;
            
            request.setAttribute("struser", usrn);
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main/usrauction.jsp");
    		dispatcher.forward(request, response);
        }
        
        if (command.equals("myauction_closed"))
        {
            String usrn = LogServlet.struser;
            
            request.setAttribute("struser", usrn);
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main/usrauction_closed.jsp");
    		dispatcher.forward(request, response);
        }

        if (command.equals("bidding"))
        {
        	Logger.info("bidding-action started");
            int id = Integer.parseInt(request.getParameter("id8"));
            int bid = Integer.parseInt(request.getParameter("bid2"));
            String usrn = LogServlet.struser;
            
            Dbconn dbConnection = new Dbconn();
            response.setStatus(200);
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            dbConnection.bidding( id, bid, usrn);
            response.sendRedirect("/Auction/success/bidsuccess.html");
        }

    }
    
    public void destroy()
    {

    }

}
