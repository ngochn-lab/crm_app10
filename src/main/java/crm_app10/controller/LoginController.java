package crm_app10.controller;

import java.io.IOException;
import java.sql.Connection;
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

import config.MySQLConfig;
import entity.Users;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		// Chuan bi cau truy van (khong truyen truc tiep '"email"' vao vi bi SQL Injection)
		String query = "SELECT * \n"
				+ "FROM users u\n"
				+ "WHERE u.email = ? AND u.password = ?";
		
		// Mo ket noi CSDL
		Connection connection = MySQLConfig.getConnection();
		try {
			// Truyen cau truy van vao connection moi vua ket noi
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// Set tham so cho dau cham hoi ben trong cau query
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			/*
			 * executeQuery: SELECT
			 * executeUpdate: Khong phai la cau SELECT
			 */
			ResultSet resultSet = preparedStatement.executeQuery();
			// Tao mot danh sach rong de bien du lieu tu cau truy van trong result set thanhf mang/danh sach
			List<Users> listUsers = new ArrayList<Users>();
			while(resultSet.next()) {
				Users users = new Users();
				users.setId(resultSet.getInt("id"));
				users.setFullname(resultSet.getString("fullname"));
				
				listUsers.add(users);
			}
			if (listUsers.isEmpty()) {
				System.out.println("Dang nhap that bai");
			} else {
				System.out.println("Dang nhap thanh cong");
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi thực thi câu truy vấn: " + e.getMessage());
		}
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
}

