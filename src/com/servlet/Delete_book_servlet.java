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
 * Servlet implementation class Delete_book_servlet
 */
@WebServlet("/Delete_book_servlet")
public class Delete_book_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection conn;
    private PrintWriter out;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_book_servlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
		
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
        
		String sql = "delete from book where id = ?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		response.sendRedirect("Find_book_servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
