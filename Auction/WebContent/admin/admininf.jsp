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
		 <tbody id="auctionstable">

                                </tbody>

				
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
                <script>
                    function loadTable(url, id)
                    {
                        var xmlHttp = new XMLHttpRequest();
                         xmlHttp.open("GET", url , true);
                         xmlHttp.setRequestHeader("Content-Type","application/json");
                         xmlHttp.responseType = "json";

                         xmlHttp.onload = function()
                         {
                          var table = document.getElementById(id);
                          
                            console.log(xmlHttp.response[0].categories);
                            for(var x in xmlHttp.response)
                            {
                                var tr = document.createElement("tr");
                                
                                    var td1 = document.createElement("td");
                                    td1.innerHTML = "<sel><center>" + xmlHttp.response[x].id + "</center></sel>";
                                    
                                    var td2 = document.createElement("td");
                                    td2.innerHTML = "<sel><center>" + xmlHttp.response[x].itemName + "</center></sel>";
                                    
                                    var td3 = document.createElement("td");
                                    if(xmlHttp.response[x].categories == null)
                                        td3.innerHTML = "NULL";
                                    else
                                    td3.innerHTML = "<sel><center>" + xmlHttp.response[x].categories + "</center></sel>";
                                                                       
                                    var td4 = document.createElement("td");
                                    td4.innerHTML = "<form method='get' action='../admin/picdisplay.jsp'><input type='hidden' name='id' value='" + xmlHttp.response[x].id + "'><input type=submit class='btn btn-primary btn-block btn-large' value='Picture' ></form>";
                                    
                                    var td5 = document.createElement("td");
                                    td5.innerHTML = "<sel><center>" + xmlHttp.response[x].highestBid + "</center></sel>";
                                    
                                    var td6 = document.createElement("td");
                                    if(xmlHttp.response[x].bidder == null)
                                    td6.innerHTML = "<sel><center>" + "NULL" + "</center></sel>";
                                    else
                                    td6.innerHTML = "<sel><center>" + xmlHttp.response[x].bidder + "</center></sel>";
                                    
                                    var td7 = document.createElement("td");
                                    td7.innerHTML = "<sel><center>" + xmlHttp.response[x].timeLimit + "</center></sel>";
                                    
                                    var td8 = document.createElement("td");
                                    td8.innerHTML = "<sel><center>" + xmlHttp.response[x].status + "</center></sel>";
                                    
                                   

                                 tr.appendChild(td1);
                                 tr.appendChild(td2);
                                 tr.appendChild(td3);
                                 tr.appendChild(td4);
                                 tr.appendChild(td5);
                                 tr.appendChild(td6);
                                 tr.appendChild(td7);
                                 tr.appendChild(td8);
                                 
                              table.appendChild(tr);  
                            }
                         };                 
                         xmlHttp.send();
                    }                    
            </script>
             <script> loadTable("http://localhost:8080/Auction/rest/auction/osszes","auctionstable"); </script>
            <script>
                    function clearNodeById(id)
                    {
                        var myNode = document.getElementById(id);
                            while (myNode.firstChild) {
                                myNode.removeChild(myNode.firstChild);
                            }
                    }
                    </script>
</body>
</html>