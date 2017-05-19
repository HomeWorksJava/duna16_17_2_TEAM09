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
<title>Users Data Modification</title>
<link rel="stylesheet" href="../main.css" />
</head>

<div class="Col">
	<body>
		<header class="top_header">
			<header><i><cim><c3>Auction</c3></cim></i></header>
		</header>	
		<center>
		<p>
			<form method="post" action="http://localhost:8080/Auction/LogServlet">
			<input type="hidden" name="command" value="regupdate"><br />
				<p><c2>Data Modification: </c2></p>
				<table border="2" width="0%" cellpadding="2">
				<tr>
					<th><c1>ID </c1></th>
					<th><c1>First Name </c1></th>
					<th><c1>Last Name </c1></th>
					<th><c1>Email </c1></th>
					<th><c1>Username</c1></th>
					<th><c1>Password</c1></th>
					<th><c1>Registration Date</c1></th>
				<p>
				<p>
			<%
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "auction", "auction");

			Statement st = con.createStatement();
				
			String id = request.getParameter("userid");
			
			String query = "SELECT * FROM user WHERE id='" + id + "'";;

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				int id2 = rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String username= rs.getString("username");
				String password= rs.getString("password");
				String Date= rs.getString("regdate");

				out.println("<tr> <td><sel> " + id2 + "</sel></td>" + 
				"<td><sel> " + "<input type='text' id='name' name='firstname' value='"+first_name+"'>" + "</sel></td> " + 
				"<td><sel> " + "<input type='text' id='name' name='lastname' value='"+last_name+"'>" + "</sel></td> " +
				"<td><sel> " + "<input type='text' id='name' name='email' value='"+email+"'>" + "</sel></td> " +
				"<td><sel> " + "<input type='text' id='name' name='username' value='"+username+"'>" + "</sel></td> " +
				"<td><sel> " + "<input type='text' id='name' name='password' value='"+password+"'>" + "</sel></td> " +
				"<td><sel> " + Date + "</sel></td></tr>");
			}
			out.println("<input type='hidden' name='id6' value='"+id+"' >");
			
			st.close();
			
			%>
			</tr>
			</table>
			<p>
			<p>
			<p>
			<td><input type="submit" class='btn btn-primary btn-block btn-large' value="Update" /></td>
				<p>
			</form>
			 <button class='btn btn-primary btn-block btn-large' onclick="window.location.href='../admin/usermodify.jsp'">Back</button>
			<p>
			<p>
			<p>
			
		</center>
		<p>
		<p>
</form>
</div>
</body>
</html>