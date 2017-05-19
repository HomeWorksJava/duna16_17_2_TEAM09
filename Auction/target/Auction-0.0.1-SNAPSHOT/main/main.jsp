<%@ page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.getAttribute("usrname") !=null) {
%>
<%
} else {
 response.sendRedirect("../index.html");
}%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
<link rel="stylesheet" href="../main.css" />
</head>
<meta http-equiv="refresh" content="20" />
</head>

<div class="Col">
	<body> 
		<header class="top_header">
			<header>
				<i><cim><c3>Auction</c3></cim></i>
			</header>
		</header>
 		<jsp:include page="/Update" />
		<center>
			<p>
			<c2>Search:</c2>
			<form method="post" action="http://localhost:8080/Auction/Servlet">
			<input type="hidden" name="command" value="search"><br />
				<table>
					<tr>
							<td><c2>Sample:</c2></td>
						<td><input type="text" name="search" vertical-align="center" size="10" /></td>
							<td><c2>Categories :</c2></td>
						<td><select type="text" name="searchcategories">
								<option value="">-Choose-</option>
								<option value="Electronics">Electronics</option>
								<option value="Pictures">Pictures</option>
								<option value="Furnitures">Furnitures</option>
								<option value="Jewelry">Jewelry</option>
								<option value="Vehicles">Vehicles</option>
						</select></td>
						<td><input type="submit" class="btn btn-primary btn-block btn-large" value="Searching" /></td>
					</tr>
				</table>
			</form>
			
			<form method="post" action="http://localhost:8080/Auction/Servlet">
			<input type="hidden" name="command" value="myauction"><br />
					<input type="submit"  class="btn btn-primary btn-block btn-large" value="My Active Auctions" />
			</form>			
			<form method="post" action="http://localhost:8080/Auction/Servlet">
			<input type="hidden" name="command" value="myauction_closed"><br />
					<input type="submit" class="btn btn-primary btn-block btn-large" value="My Closed Auctions" />
			</form>		
			<p>
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
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "auction", "auction");
					
					Statement st = con.createStatement();

					String query = "SELECT * FROM auctions";

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
						"<td align='center'><form method='get' action='../admin/picdisplay.jsp'><input type='hidden' name='id' value='%s'><input type=submit class='btn btn-primary btn-block btn-large' value='Picture' ></form></td>" +
						"<td><sel><center>%d</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td align='center'><form method='post' action='../main/bidding.jsp'><input type='hidden' name='biddingid' value='%s'><input type=submit class='btn btn-primary btn-block btn-large' value='Bidding' ></form></td> </tr>",
								id, Name, Categ, id, bid, bid_name, timelimit, status, id ));

					}
					st.close();
				%>
			</table>
			<p>
			<p>
			<form method="post" action="http://localhost:8080/Auction/LogServlet">
				<input type="hidden" name="command" value="logout"><br />
					<input type="submit" class="btn btn-primary btn-block btn-large" value="Logout" />
			</form>
</div>
</body>
</html>