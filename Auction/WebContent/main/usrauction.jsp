<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%
if(session.getAttribute("usrname") !=null) {
%>
<%
} else {
 response.sendRedirect("../index.html");
}%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Active Auctions</title>
<link rel="stylesheet" href="main.css" />
</head>

<div class="Col">
	<body> 
		<header class="top_header">
			<header>
				<i><cim><c3>Auction</c3></cim></i>
			</header>
		</header>
		<center>
			<c2 align="center">My Active Auctions:</c2>
			<p>
		
			<table border="2" width="80%" cellpadding="5">
				<tr>
					<th><c1>ID</c1></th>
					<th><c1>Item Name</c1></th>
					<th><c1>Categories</c1></th>
					<th><c1>Photo</c1></th>
					<th><c1>Highest Bid</c1></th>
					<th><c1>Bidders Name</c1></th>
					<th><c1>Time Limit</c1></th>
					<th><c1>Status</c1></th>
					<th><c1>Bidding</c1></th>
				</tr>

				<p>
				</center>
				<%
					String usrn= (String)request.getAttribute("struser");
				
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "auction", "auction");
					
					Statement st = con.createStatement();
					
					String query = "";
					
					query = "SELECT * FROM auctions WHERE Bidders_usrname='" + usrn + "'";
					
					ResultSet rs = st.executeQuery(query);

					while (rs.next()) {
						int id = rs.getInt("id");
						String Name = rs.getString("Item_name");
						String Categ = rs.getString("Categories");
						int bid = rs.getInt("Highest_bid");
						String bid_name = rs.getString("Bidders_usrname");
						String timelimit = rs.getString("Time_limit");
						String status = rs.getString("Status");

						// print the results
						out.println(String.format(
								"<tr> <td><sel><center>%d</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td align='center'><form method='post' action='admin/picdisplay.jsp'><input type='hidden' name='id' value='%s'><input type=submit class='btn btn-primary btn-block btn-large' value='Picture' ></form></td>" +
						"<td><sel><center>%d</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td align='center'><form method='post' action='main/bidding.jsp'><input type='hidden' name='biddingid' value='%s'><input type=submit class='btn btn-primary btn-block btn-large' value='Bidding' ></form></td> </tr>",
								id, Name, Categ, id, bid, bid_name, timelimit, status, id ));

					}
					st.close();
				%>
        	<p>
			
			<center>
			</table>
			<p>
			<p>
			<button class='btn btn-primary btn-block btn-large' onclick="window.location.href='main/main.jsp'">Back To Main Page</button>
			<p>
			<p>

</div>
</body>
</html>