package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add_book_servlet
 */
@WebServlet("/Add_book_servlet")
public class Add_book_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn;
    private PrintWriter out;
    
    public Add_book_servlet() {
        super();
        
        //get connection
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// connect to database
		try{
        	Class.forName("com.mysql.jdbc.Driver");
        	String url = "jdbc:mysql://localhost:3306/book_store";
        	String username = "root";
        	String password = "mysql2016";
        	conn = DriverManager.getConnection(url, username, password);
        }catch(ClassNotFoundException e){
        	e.printStackTrace();
        }catch(SQLException e){
        	e.printStackTrace();
        }
		
		// initialize and check link to database
		out = response.getWriter(); // use ajax to return result
		if(conn == null){
			out.println("failed to link to database!");
		}
		// get the form
		String book_name = request.getParameter("book_name");
		String author = request.getParameter("author");
		double price = Double.valueOf(request.getParameter("price"));
		int stock = Integer.valueOf(request.getParameter("stock"));
		
		
		//insert the data into table book;
		String sql = "insert into book(name, author, price, stock) values(?,?,?,?)";
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, book_name);
			ps.setString(2, author);
			ps.setDouble(3, price);
			ps.setInt(4, stock);
			int row = ps.executeUpdate();
			if(row > 0){
				response.sendRedirect("Find_book_servlet");
			}
			else{
				out.println("failed!");
			}
			out.flush();
			out.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
	}

}
