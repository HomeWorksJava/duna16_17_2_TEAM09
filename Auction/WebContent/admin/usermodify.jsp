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
</head>

<div class="Col">
	<body>
		<header class="top_header">
			<header><i><cim><c3>Auction</c3></cim></i></header>
		</header>	
		<center>
		<p>
			<p>
             <td><c2>Admin-interface</c2></td>
            <p>

		<td><c2>Users:</c2></td>
			<table border="2" width="30%" cellpadding="5">
				<tr>
					<th><c1>ID </c1></th>
					<th><c1>First Name </c1></th>
					<th><c1>Last Name </c1></th>
					<th><c1>Email </c1></th>
					<th><c1>Username</c1></th>
					<th><c1>Password</c1></th>
					<th><c1>Registration Date</c1></th>
					<th><c1>Update</c1></th>
					<th><c1>Delete</c1></th>
				</tr>
			
			<p>
		</center>

		<%
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "auction", "auction");

			Statement st = con.createStatement();

			String query = "SELECT * FROM user";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String username= rs.getString("username");
				String password= rs.getString("password");
				String Date= rs.getString("regdate");

				// print the results
				out.println(String.format("<tr> <td><sel><center>%d</center></sel></td> " +  
				"<td><sel><center>%s</center></sel></td> <td><sel><center>%s</center></sel></td>" +
				"<td><sel><center>%s</center></sel></td> <td><sel><center>%s</center></sel></td>" + 
				"<td><sel><center>%s</center></sel></td> <td><sel><center>%s</center></sel></td>" +
				"<td><form method='post' action='../admin/userupdate.jsp'><input type='hidden' name='userid' value='%s'><input type=submit class='btn btn-primary btn-block btn-large' value='Update' ></form></td>" + 
				"<td><form method='post' action='http://localhost:8080/Auction/LogServlet'><input type='hidden' name='command' value='regdelete'><input type='hidden' name='userid' value='%s'><input type=submit class='btn btn-primary btn-block btn-large' value='Delete' ></form></td> </tr>", + 
				id, first_name, last_name, email, username, password, Date, id, id));
			}

			st.close();
			
		%>
		</table>
		<p>
			<p>
				<p><c2>Users Data Update </c2></p>
				<p>		
				<button class='btn btn-primary btn-block btn-large' onclick="window.location.href='../admin/userreg.html'">New user</button>
		<p>
		<p>
		<p>
	
</form>
		<button class='btn btn-primary btn-block btn-large' onclick="window.location.href='../admin/admininf.jsp'">Back</button>
</div>
</body>
</html>