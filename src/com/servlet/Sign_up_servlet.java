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
 * Servlet implementation class Log_in
 */
@WebServlet("/Sign_up_servlet")
public class Sign_up_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn;
    private PrintWriter out;
	
	
    public Sign_up_servlet() {
        super();
        
        //connect to the database
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
		// use to check if the user name is exsit in the database
		PrintWriter out = response.getWriter();
		String user=new String(request.getParameter("user_name").getBytes("ISO-8859-1"),"GB18030");	//获取用户名
		String sql = "select count(*) from users where user_name = ?";
		int result = 1;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,user);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == 1){
			out.println("no");			//输出检测结果
		}else{
			out.println("ok");			//输出检测结果
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out = response.getWriter();
		if(conn == null)
			out.println("database link erro");
		// read data form the form 
		
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		String sql = "insert into users(user_name, password, phone, email, address) values(?,?,?,?,?)";
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, password);
			ps.setString(3, phone);
			ps.setString(4, email);
			ps.setString(5, address);
			
			int row = ps.executeUpdate();
			if(row > 0){
				out.println("sucessed!");
			}else 
				out.println("failed!");
			
			out.flush();
			out.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
