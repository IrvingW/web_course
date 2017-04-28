package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sign_in
 */
@WebServlet("/Sign_in_servlet")
public class Sign_in_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
    private PrintWriter out;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sign_in_servlet() {
        super();
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
		// TODO Auto-generated method stub
		String user_name = new String(request.getParameter("user_name"));
		String password = new String(request.getParameter("password"));
		out = response.getWriter();
		
		String sql = "select count(*) from users where user_name = ? and password = ?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			int result = rs.getInt(1);
			if(result == 1){
				out.println("success");
				
			}
			else{
				out.println("failed");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
