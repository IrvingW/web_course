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
 * Servlet implementation class Add_order_servlet
 */
@WebServlet("/Add_order_servlet")
public class Add_order_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
    private PrintWriter out;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_order_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		int book_id = Integer.valueOf(request.getParameter("book_id"));
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		String time = request.getParameter("time");

		//insert the data into table book;
		String sql = "insert into orders(user_id, book_id, time) values(?,?,?)";
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, book_id);
			ps.setString(3, time);
			
			int row = ps.executeUpdate();
			if(row > 0){
				response.sendRedirect("Find_order_servlet");
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
