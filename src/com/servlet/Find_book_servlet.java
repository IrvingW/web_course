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
 * Servlet implementation class Find_book_servlet
 */
@WebServlet("/Find_book_servlet")
public class Find_book_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
    private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Find_book_servlet() {
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
			String sql = "select * from book";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Book> list = new ArrayList<Book>();
			while(rs.next()){
				Book abook = new Book();
				abook.setId(rs.getInt("id"));
				abook.setBook_name(rs.getString("name"));
				abook.setAuthor(rs.getString("author"));
				abook.setPrice(rs.getDouble("price"));
				abook.setStock(rs.getInt("stock"));
				list.add(abook);
			}
			request.setAttribute("list", list);
			rs.close();
			ps.close();
			conn.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("show_book.jsp").forward(request, response);
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
