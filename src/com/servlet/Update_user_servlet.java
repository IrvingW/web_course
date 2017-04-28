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
 * Servlet implementation class Update_user_servlet
 */
@WebServlet("/Update_user_servlet")
public class Update_user_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_user_servlet() {
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
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		
		try{
			String sql = "update users set user_name = ?, password = ?, phone = ?, email = ?, address = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, password);
			ps.setString(3, phone);
			ps.setString(4, email);
			ps.setString(5, address);
			ps.setInt(6, id);
			
			
			int row = ps.executeUpdate();
			if(row > 0){
				
				out.println("sucess");
				response.sendRedirect("Find_user_servlet");
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
