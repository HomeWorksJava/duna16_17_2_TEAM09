<%@ page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.getAttribute("admin") !=null) {
%>
<%
} else {
 response.sendRedirect("../index.html");
}%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page(Admin)</title>
<link rel="stylesheet" href="../main.css" />
<meta http-equiv="refresh" content="20" />
</head>

<div class="Col">
	<body>
		<header class="top_header">
			<header><cim><i><c3>Auction</c3></i></cim></header>
		</header>
		<jsp:include page="/Update" />
		<center>
		<p>
			<p>
             <td><c2>Admin-interface</c2></td>
            <p>
		<td><c2>Items:</c2></td>
			<table border="2" width="30%" cellpadding="5">
				<tr>
					<th><c1>ID</c1></th>
					<th><c1>Item Name</c1></th>
					<th><c1>Categories</c1></th>
					<th><c1>Photo</c1></th>
					<th><c1>Highest Bid</c1></th>
					<th><c1>Bidders Name</c1></th>
					<th><c1>Time Limit</c1></th>
					<th><c1>Status</c1></th>
				</tr>

				<p>
				</center>
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
						"<td align='center'><form method='post' action='../admin/picdisplay.jsp'><input type='hidden' name='id' value='%s'><input type=submit class='btn btn-primary btn-block btn-large' value='Picture' ></form></td>" +
						"<td><sel><center>%d</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>" +
						"<td><sel><center>%s</center></sel></td>",
								id, Name, Categ, id, bid, bid_name, timelimit, status));

					}
					st.close();
				%>
		</table>
		<p>
			<p>
			<button class='btn btn-primary btn-block btn-large' onclick="window.location.href='itemadd.jsp'">New Item Add</button>
			<p>
			<button class='btn btn-primary btn-block btn-large' onclick="window.location.href='usermodify.jsp'">Users</button>
		<center>
		<p>
		<p>
		</center>
		<p>
		<p>
		<p>
<form method="post" action="http://localhost:8080/Auction/LogServlet">
	<input type="hidden" name="command" value="logout"><br />
	<td><input type="submit" class='btn btn-primary btn-block btn-large'  value="Logout" /></td>
</form>
</div>
</body>
</html>