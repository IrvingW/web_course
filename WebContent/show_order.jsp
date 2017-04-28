<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.object.Order" %>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
<div style="margin-top:20px">
	<!-- nevigaton bar -->
	<div class="col-md-2">
	<ul class="nav nav-pills nav-stacked">
		<li><a href="Find_book_servlet">Books</a></li>
        <li><a href="Find_user_servlet">Users</a></li>
        <li class="active"><a href="Find_order_servlet">Orders</a></li>
      
    </ul>
	</div>
	<!-- list -->
    <div class="col-md-10">
    	<div>
			<table align="center" class="table table-striped table-bordered table-hover" >
        <tr>
            <td align="center" colspan="7">
                <h1>all order infomation</h1>
            </td>
        </tr>
        
        <tr align="center">
            <td><b>ID</b></td>
            <td><b>Book_id</b></td>
            <td><b>User_id</b></td>
            <td><b>time</b></td>
            <td><b>delete</b></td>
            <td><b>update</b></td>
        </tr>
        
        <%
        	List<Order> list = (List<Order>)request.getAttribute("list");
        	if(list == null || list.size() < 1){
        		out.print("no data");
        	}else{
        		for(Order order : list){
        			String row_id = "row"+order.getId();
        %>
         <tr align="center" id="<%= row_id %>">
            <td><%= order.getId() %></td>
            <td ondblclick="modify(this)"><%= order.getBook_id()%></td>
            <td ondblclick="modify(this)"><%= order.getUser_id()%></td>
            <td ondblclick="modify(this)"><%= order.getTime()%></td>
         
            <td>
            	<button class="btn btn-default">
            		<a href="Delete_order_servlet?id=<%= order.getId() %>">delete</a>
            	</button>
            </td>
            <td>
            	<button onclick="update('<%=row_id%>')" class="btn btn-success">update</button>
            </td>
        </tr>
        
        <%
        		}
        	}
        %>
        
    </table>
    	</div>
    
		<div>
			<h4 align="left"><b>add new book</b></h5>
			<form action = "Add_order_servlet" method="post" class="form-horizontal">
				<div class="col-md-3" class="form-group">
					<label class="control-label col-md-3">book_id</label>
					<div class="col-md-9">
						<input type = "text" name = "book_id" class="form-control">
					</div>
					
				</div>
				<div class="col-md-3" class="form-group">
					<label class="control-label col-md-3">user_id</label>
					<div class="col-md-9">
						<input type = "text" name = "user_id" class="form-control">
					</div>
					
				</div>
				<div class="col-md-3" class="form-group">
					<label class="control-label col-md-3">time</label>
					<div class="col-md-9">
						<input type = "text" name = "time" class="form-control">
					</div>
					
				</div>
				
				<div class="col-md-3" >
					<input type="submit" value="&nbsp &nbsp &nbsp submit &nbsp &nbsp &nbsp" class="btn btn-primary">
				</div>
			</form>
		</div>
	</div>
</div>
</body>

<script language="JavaScript" type="text/javascript">
function modify(td) {
    td.setAttribute("contenteditable","true");

}

function update(row_id){
	var row = document.getElementById(row_id);
    var td = row.firstElementChild;
    var id = td.innerHTML;
    td = td.nextElementSibling;
    var book_id = td.innerHTML;
    td = td.nextElementSibling;
    var user_id = td.innerHTML;
    td = td.nextElementSibling;
    var time = td.innerHTML;
   
    var url = "Update_order_servlet?id="+id+"&user_id="+user_id+"&book_id="+book_id+"&time="+time;

    window.location.href=url;
}
	


</script>

</html>