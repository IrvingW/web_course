<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.object.Book" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<title>Insert title here</title>

<style type="text/css">
	
</style>
</head>
<body>

<div style="margin-top:20px">
	<!-- nevigaton bar -->
	<div class="col-md-2">
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a href="##">Books</a></li>
        <li><a href="Find_user_servlet">Users</a></li>
        <li><a href="Find_order_servlet">Orders</a></li>
      
    </ul>
	</div>
	<!-- list -->
    <div class="col-md-10">
    	<div>
		<table align="center" class="table table-striped table-bordered table-hover" >
        <tr>
            <td align="center" colspan="7">
                <h1>all book infomation</h1>
            </td>
        </tr>
        
        <tr align="center">
            <td><b>ID</b></td>
            <td><b>name</b></td>
            <td><b>author</b></td>
            <td><b>price</b></td>
            <td><b>stock</b></td>
            <td><b>delete</b></td>
            <td><b>update</b></td>
        </tr>
        
        <%
        	List<Book> list = (List<Book>)request.getAttribute("list");
        	if(list == null || list.size() < 1){
        		out.print("no data");
        	}else{
        		for(Book book : list){
        			String row_id = "row"+book.getId();
        %>
         <tr align="center" id="<%= row_id %>">
            <td><%= book.getId() %></td>
            <td ondblclick="modify(this)"><%= book.getBook_name()%></td>
            <td ondblclick="modify(this)"><%= book.getAuthor()%></td>
            <td ondblclick="modify(this)"><%= book.getPrice()%></td>
            <td ondblclick="modify(this)"><%= book.getStock()%></td>
            <td>
            	<button class="btn btn-default">
            		<a href="Delete_book_servlet?id=<%= book.getId() %>">delete</a>
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
		
		<!-- insert new entry form -->
		<div>
			<h4 align="left"><b>add new book</b></h5>
			
			<form action = "Add_book_servlet" method="post" class="form-horizontal">
				<div class="col-md-3" class="form-group">
					<label class="control-label col-md-3">name</label>
					<div class="col-md-9">
						<input type = "text" name = "book_name" class="form-control">
					</div>
					
				</div>
				<div class="col-md-3" class="form-group">
					<label class="control-label col-md-3">author</label>
					<div class="col-md-9">
						<input type = "text" name = "author" class="form-control">
					</div>
					
				</div>
				<div class="col-md-3" class="form-group">
					<label class="control-label col-md-3">price</label>
					<div class="col-md-9">
						<input type = "text" name = "price" class="form-control">
					</div>
					
				</div>
				<div class="col-md-3" class="form-group">
					<label class="control-label col-md-3">stock</label>
					<div class="col-md-9">
						<input type = "text" name = "stock" class="form-control">
					</div>
					
				</div>
				
				<div class="col-md-4 col-md-offset-10" style="margin-top:25px">
					<input type="submit" value="&nbsp &nbsp &nbsp submit &nbsp &nbsp &nbsp" class="btn btn-primary">
				</div>
			</form>
			
			
		</div>
	
	</div>
	
</div>


<script language="JavaScript" type="text/javascript">
function modify(td) {
    td.setAttribute("contenteditable","true");

}

function update(row_id){
	var row = document.getElementById(row_id);
    var td = row.firstElementChild;
    var id = td.innerHTML;
    td = td.nextElementSibling;
    var name = td.innerHTML;
    td = td.nextElementSibling;
    var author = td.innerHTML;
    td = td.nextElementSibling;
    var price = td.innerHTML;
    td = td.nextElementSibling;
    var stock = td.innerHTML;
    var url = "Update_book_servlet?id="+id+"&name="+name+"&author="+author+"&price="+price+"&stock="+stock;

    window.location.href=url;
}
	


</script>



</html>