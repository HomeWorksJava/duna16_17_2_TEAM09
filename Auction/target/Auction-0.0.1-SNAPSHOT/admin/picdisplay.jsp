<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.getAttribute("usrname") !=null || session.getAttribute("admin") !=null) {
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
<title>Photo View</title>
<link rel="stylesheet" href="../main.css" />
</head>

<div class="Col">
	<body> 
		<header class="top_header">
			<header>
				<i><cim><c3>Auction</c3></cim></i>
			</header>
		</header>
		<center>
			<p>
			<c2>Photo:</c2>
<%
String id= (String)request.getParameter("id");
String id2= (String)request.getParameter("id2");
String URL="";
if(id !=null && id2 == null)	
{
	URL = "http://localhost:8080/Auction/Pictures?id=" + id;
}else
{
	URL = "http://localhost:8080/Auction/Pictures?id2=" + id2;
}
%>
<table>
<td><IMG src="<%=URL%>" width="800" height="600"></td> 
		
</table>
<p>
<button class='btn btn-primary btn-block btn-large' onclick="goBack()">Back</button>
<p>			 
</div>
</body>
</html>