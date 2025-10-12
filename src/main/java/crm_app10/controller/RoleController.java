package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app10.services.RoleServices;
import entity.Roles;
import entity.Users;

@WebServlet(name = "roleController", urlPatterns = {"/role", "/role-table", "/role-add", "/role-edit", "/role-delete"})
public class RoleController extends HttpServlet{
	
	private RoleServices roleServices = new RoleServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/role":
			case "/role-table":
				showRoleList(req, resp);
				break;
			case "/role-add":
				showAddForm(req, resp);
				break;
			case "/role-edit":
				showEditForm(req, resp);
				break;
			case "/role-delete":
				deleteRole(req, resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/role-table");
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		
		switch (path) {
			case "/role-add":
				addRole(req, resp);
				break;
			case "/role-edit":
				updateRole(req, resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/role-table");
				break;
		}
	}
	
	private void showRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Roles> listRoles = roleServices.getAllRoles();
		req.setAttribute("listRole", listRoles);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}
	
	private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
	
	private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id != null) {
			Roles role = roleServices.getRoleById(Integer.parseInt(id));
			req.setAttribute("role", role);
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/role-table");
		}
	}
	
	private void addRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		
		boolean result = roleServices.insertRole(name, description);
		
		if (result) {
			resp.sendRedirect(req.getContextPath() + "/role-table");
		} else {
			resp.sendRedirect(req.getContextPath() + "/role-add?error=1");
		}
	}
	
	private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		
		boolean result = roleServices.updateRole(Integer.parseInt(id), name, description);
		
		if (result) {
			resp.sendRedirect(req.getContextPath() + "/role-table");
		} else {
			resp.sendRedirect(req.getContextPath() + "/role-edit?id=" + id + "&error=1");
		}
	}
	
	private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		if (id != null) {
			roleServices.deleteRole(Integer.parseInt(id));
		}
		resp.sendRedirect(req.getContextPath() + "/role-table");
	}
}
