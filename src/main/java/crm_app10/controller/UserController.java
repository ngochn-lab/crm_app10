package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app10.services.RoleServices;
import crm_app10.services.UserServices;
import entity.Roles;
import entity.Users;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user-table", "/user-add", "/user-edit", "/user-delete"})
public class UserController extends HttpServlet{
	
	private UserServices userServices = new UserServices();
	private RoleServices roleServices = new RoleServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/user":
			case "/user-table":
				showUserList(req, resp);
				break;
			case "/user-add":
				showAddForm(req, resp);
				break;
			case "/user-edit":
				showEditForm(req, resp);
				break;
			case "/user-delete":
				deleteUser(req, resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/user-table");
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		
		switch (path) {
			case "/user-add":
				addUser(req, resp);
				break;
			case "/user-edit":
				updateUser(req, resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/user-table");
				break;
		}
	}
	
	private void showUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Users> listUsers = userServices.getAllUsers();
		req.setAttribute("listUser", listUsers);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
	
	private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Roles> listRoles = roleServices.getAllRoles();
		req.setAttribute("listRoles", listRoles);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id != null) {
			Users user = userServices.getUserById(Integer.parseInt(id));
			List<Roles> listRoles = roleServices.getAllRoles();
			req.setAttribute("user", user);
			req.setAttribute("listRoles", listRoles);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/user-table");
		}
	}
	
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullname = req.getParameter("fullname");
		String avatar = req.getParameter("avatar");
		String roleId = req.getParameter("roleId");
		
		boolean result = userServices.insertUser(email, password, fullname, avatar, Integer.parseInt(roleId));
		
		if (result) {
			resp.sendRedirect(req.getContextPath() + "/user-table");
		} else {
			resp.sendRedirect(req.getContextPath() + "/user-add?error=1");
		}
	}
	
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullname = req.getParameter("fullname");
		String avatar = req.getParameter("avatar");
		String roleId = req.getParameter("roleId");
		
		boolean result = userServices.updateUser(Integer.parseInt(id), email, password, fullname, avatar, Integer.parseInt(roleId));
		
		if (result) {
			resp.sendRedirect(req.getContextPath() + "/user-table");
		} else {
			resp.sendRedirect(req.getContextPath() + "/user-edit?id=" + id + "&error=1");
		}
	}
	
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		if (id != null) {
			userServices.deleteUser(Integer.parseInt(id));
		}
		resp.sendRedirect(req.getContextPath() + "/user-table");
	}
}
