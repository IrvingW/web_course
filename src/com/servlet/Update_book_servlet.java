package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.object.Book;

/**
 * Servlet implementation class Update_book_servlet
 */
@WebServlet("/Update_book_servlet")
public class Update_book_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_book_servlet() {
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
		String book_name = request.getParameter("name");
		String author = request.getParameter("author");
		double price = Double.valueOf(request.getParameter("price"));
		int stock = Integer.valueOf(request.getParameter("stock"));
		
		try{
			String sql = "update book set name = ?, author = ?, price = ?, stock = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, book_name);
			ps.setString(2, author);
			ps.setDouble(3, price);
			ps.setInt(4, stock);
			ps.setInt(5, id);
			
			
			int row = ps.executeUpdate();
			if(row > 0){
				
				out.println("sucess");
				response.sendRedirect("Find_book_servlet");
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
