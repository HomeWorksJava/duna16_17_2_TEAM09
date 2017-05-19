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
<script>
function goBack() {
    window.history.back()
}
</script>
<title>Bidding</title>
<link rel="stylesheet" href="../main.css" />
</head>

<div class="Col">
	<body>
		<header class="top_header">
			<header><cim><i><c3>Auction</c3></i></cim></header>
		</header>	
		<center>
		<p>
			<form method="post" action="http://localhost:8080/Auction/Servlet">
			<input type="hidden" name="command" value="bidding"><br />
				<p><c2>Bidding: </c2></p>
				<table border="2" width="0%" cellpadding="6">
				<tr>
					<th><c1>ID</c1></th>
					<th><c1>Item Name</c1></th>
					<th><c1>Categories</c1></th>
					<th><c1>Highest Bid</c1></th>
					<th><c1>Bidders Name</c1></th>
					<th><c1>Time Limit</c1></th>
					<th><c1>Status</c1></th>
				<p>
				<p>
			<%
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "auction", "auction");

			Statement st = con.createStatement();
				
			String id = request.getParameter("biddingid");
			
			String query = "SELECT * FROM auctions WHERE id='" + id + "'";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				int id2 = rs.getInt("id");
				String Item_name = rs.getString("Item_name");
				String Categ = rs.getString("Categories");
				int bid = rs.getInt("Highest_bid");
				String bidusrn= rs.getString("Bidders_usrname");
				String time= rs.getString("Time_limit");
				String stat= rs.getString("Status");
				
					
				out.println("<tr> <td><sel> " + id2 + "</sel></td>" + 
				"<td><sel> " + Item_name + "</sel></td>" + 
				"<td><sel> " + Categ + "</sel></td> " +
				"<td><sel> " + "<input type='text' id='name' name='bid2' required value='"+bid+"'>" + "</sel></td> " +
				"<td><sel> " + bidusrn + "</sel></td>" +
				"<td><sel> " + time + "</sel></td>" +
				"<td><sel> " + stat + "</sel></td> </tr>"
				);

			}
			out.println("<input type='hidden' name='id8' value='"+id+"' >");
			
			st.close();
			
			%>
			</tr>
			</table>
			<p>
			<p>
			<p>
			<td><input type="submit" class='btn btn-primary btn-block btn-large' value="Save" /></td>
				<p>
			</form>
			 <button class='btn btn-primary btn-block btn-large' onclick="goBack()">Back</button>
			<p>
			<p>
			<p>
			
		</center>
		</table>
		<p>
		<p>
</form>
</div>
</body>
</html>