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
 * Servlet implementation class Update_order_servlet
 */
@WebServlet("/Update_order_servlet")
public class Update_order_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_order_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
		int id = Integer.valueOf(request.getParameter("id"));
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		int book_id = Integer.valueOf(request.getParameter("book_id"));
		String time = request.getParameter("time");
		
		try{
			String sql = "update orders set user_id = ?, book_id = ?, time = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, book_id);
			ps.setString(3, time);
			ps.setInt(4, id);
			
			
			int row = ps.executeUpdate();
			if(row > 0){
				out.println("sucess");
				response.sendRedirect("Find_order_servlet");
			}
			else{
				out.println("failed");
			}
			ps.close();
			conn.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
