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
import com.object.Order;

/**
 * Servlet implementation class Find_order_servlet
 */
@WebServlet("/Find_order_servlet")
public class Find_order_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
    private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Find_order_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		try{
			String sql = "select * from orders";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Order> list = new ArrayList<Order>();
			while(rs.next()){
				Order aOrder = new Order();
				aOrder.setId(rs.getInt("id"));
				aOrder.setUser_id(rs.getInt("user_id"));
				aOrder.setBook_id(rs.getInt("book_id"));
				aOrder.setTime(rs.getString("time"));
				list.add(aOrder);
			}
			request.setAttribute("list", list);
			rs.close();
			ps.close();
			conn.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("show_order.jsp").forward(request, response);
		// forward the request to show_book page
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
