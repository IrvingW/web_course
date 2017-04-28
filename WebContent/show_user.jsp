<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.object.User" %>
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
        <li class="active"><a href="##">Users</a></li>
        <li><a href="Find_order_servlet">Orders</a></li>
      
    </ul>
	</div>
	<!-- list -->
    <div class="col-md-10">
    	<div>
<table align="center" class="table table-striped table-bordered table-hover" >
        <tr>
            <td align="center" colspan="8">
                <h1>all user infomation</h1>
            </td>
        </tr>
        
        <tr align="center">
            <td><b>ID</b></td>
            <td><b>name</b></td>
            <td><b>password</b></td>
            <td><b>phone</b></td>
            <td><b>email</b></td>
            <td><b>address</b></td>
            <td><b>delete</b></td>
            <td><b>update</b></td>
        </tr>
        
        <%
        	List<User> list = (List<User>)request.getAttribute("list");
        	if(list == null || list.size() < 1){
        		out.print("no data");
        	}else{
        		for(User user : list){
        			String row_id = "row"+user.getId();
        %>
         <tr align="center" id="<%= row_id %>">
            <td><%= user.getId() %></td>
            <td ondblclick="modify(this)"><%= user.getUser_name()%></td>
            <td ondblclick="modify(this)"><%= user.getPassword()%></td>
            <td ondblclick="modify(this)"><%= user.getPhone()%></td>
            <td ondblclick="modify(this)"><%= user.getEmail()%></td>
            <td ondblclick="modify(this)"><%= user.getAddress()%></td>
            <td>
            	<button class="btn btn-default">
            		<a href="Delete_user_servlet?id=<%= user.getId() %>">delete</a>
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
    <!-- insert form  -->
    <div>
			<h4 align="left"><b>add new user</b></h5>
			
			<form action = "Add_user_servlet" method="post" class="form-horizontal">
				<div class="col-md-4" class="form-group"  style="margin-top:25px">
					<label class="control-label col-md-3">name</label>
					<div class="col-md-9">
						<input type = "text" name = "user_name" class="form-control">
					</div>
					
				</div>
				<div class="col-md-4" class="form-group"  style="margin-top:25px">
					<label class="control-label col-md-3">password</label>
					<div class="col-md-9">
						<input type = "text" name = "password" class="form-control">
					</div>
					
				</div>
				<div class="col-md-4" class="form-group"  style="margin-top:25px">
					<label class="control-label col-md-3">phone</label>
					<div class="col-md-9">
						<input type = "text" name = "phone" class="form-control">
					</div>
					
				</div>
				<div class="col-md-4" class="form-group"  style="margin-top:25px">
					<label class="control-label col-md-3">email</label>
					<div class="col-md-9">
						<input type = "text" name = "email" class="form-control">
					</div>
					
				</div>
				<div class="col-md-4" class="form-group"  style="margin-top:25px">
					<label class="control-label col-md-3">address</label>
					<div class="col-md-9">
						<input type = "text" name = "address" class="form-control">
					</div>
					
				</div>
				
				<div class="col-md-4"  style="margin-top:25px">
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
    var user_name = td.innerHTML;
    td = td.nextElementSibling;
    var password = td.innerHTML;
    td = td.nextElementSibling;
    var phone = td.innerHTML;
    td = td.nextElementSibling;
    var email = td.innerHTML;
    td = td.nextElementSibling;
    var address = td.innerHTML;
    
    var url = "Update_user_servlet?id="+id+"&user_name="+user_name+"&password="+password+"&phone="+phone+"&email="+email+"&address="+address;
    

    window.location.href=url;
}
	


</script>
</html>