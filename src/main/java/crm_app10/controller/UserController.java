package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app10.services.UserServices;
import entity.Users;

// /user
@WebServlet(name = "userController", urlPatterns = {"/user"})
public class UserController extends HttpServlet{
	
	private UserServices userServices = new UserServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Users> listUsers = userServices.getAllUsers();
		
		System.out.println("Danh sach co " + listUsers.size() + " nguoi dung");
		
		req.setAttribute("listUser", listUsers);
		
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
}
