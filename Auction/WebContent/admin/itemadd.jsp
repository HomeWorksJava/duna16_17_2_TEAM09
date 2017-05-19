<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>New Item Add</title>

<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
     href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../main.css" />
</head>

<div class="Col">
		<header class="top_header">
			<header>
				<i><cim><c3>Auction</c3></cim></i>
			</header>
		</header>
		<center>
	<form method="post" action="http://localhost:8080/Auction/Iteminsert" enctype="multipart/form-data">
		<center>
		<p>
			<table border="1" width="100%" cellpadding="25">
				<thead>
					<tr>
						<th colspan="2"><c2>Item Save</c2></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c1>Item Name: </c1></td>
						<td><input type="text" name="Item_name" pattern="[a-zA-Z0-9]{4,80}" title="Use Only Letters, And Numbers. Minimum 4 Character." required value="" /></td>
					</tr>
					<tr>
						<td><c1>Categories:</c1></td>
						<td><select type="text" name="Categories" required >
								<option value="">-Choose-</option>
								<option value="Electronics">Electronics</option>
								<option value="Pictures">Pictures</option>
								<option value="Furnitures">Furnitures</option>
								<option value="Jewelry">Jewelry</option>
								<option value="Vehicles">Vehicles</option>
						</select></td>
					</tr>
					 <tr>
					<td><c1>Photo:</c1></td>
					<td><input type="file" name="Photo" accept="image/*" required/></td>
					<tr>
						<td><c1>Starting Bid: </c1></td>
						<td><input type="text" name="Highest_bid" pattern="[0-9]{3,20}" title="Only Numbers." required value="" /></td>
					</tr>
					<tr>
					<td><c1>Time Limit: </c1></td>

					<td><div id="datetimepicker" class="input-append date">
      					<input type="text" name="Time_limit" required></input>
      					<span class="add-on">
        				<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
      					</span>
    					</div></td>
					</tr>
					</table>
					<p>
					<p>
						<input type="submit" class='btn btn-primary btn-block btn-large' value="Save" /> <input type="reset" class='btn btn-primary btn-block btn-large' value="Delete" />
					<p>
				</tbody>
			
		</center>
	</form>
		<button class='btn btn-primary btn-block btn-large' onclick="window.location.href='../admin/admininf.jsp'">Back to Admin Page</button>
</div>
 <script type="text/javascript"
     src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script> 
    <script type="text/javascript"
     src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
    </script>
    <script type="text/javascript"
     src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript">
      $('#datetimepicker').datetimepicker({
        format: 'yyyy-MM-dd hh:mm:ss',
        language: 'en'
      });
    </script>
</body>
</html>