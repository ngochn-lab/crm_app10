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

@WebServlet(name = "roleController", urlPatterns = {"/role"})
public class RoleController extends HttpServlet{
	
	RoleServices roleServices = new RoleServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Roles> listRoles = roleServices.getAllRoles();
		
		req.setAttribute("listRole", listRoles);
		
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}
}
