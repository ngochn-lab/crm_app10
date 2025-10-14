package crm_app10.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm_app10.services.UserServices;
import crm_app10.entity.Users;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	
	private UserServices userServices = new UserServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Kiểm tra nếu đã đăng nhập thì redirect về dashboard
		HttpSession session = req.getSession();
		if (session.getAttribute("userId") != null) {
			resp.sendRedirect(req.getContextPath() + "/dashboard");
			return;
		}
		
		// Kiểm tra cookie remember me
		String email = "";
		String password = "";
		
		if (req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				} else if (cookie.getName().equals("password")) {
					password = cookie.getValue();
				}
			}
		}
		
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		// Sử dụng service để kiểm tra đăng nhập
		Users user = userServices.login(email, password);
		
		if (user != null) {
			// Đăng nhập thành công
			HttpSession session = req.getSession();
			
			// Lưu thông tin user vào session
			session.setAttribute("userId", user.getId());
			session.setAttribute("userEmail", user.getEmail());
			session.setAttribute("userFullname", user.getFullname());
			session.setAttribute("userAvatar", user.getAvatar());
			session.setAttribute("roleId", user.getRoleId());
			session.setAttribute("roleDescription", user.getRoleDescription());
			
			// Xử lý Remember Me
			if ("on".equals(remember)) {
				Cookie emailCookie = new Cookie("email", email);
				emailCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
				resp.addCookie(emailCookie);
				
				Cookie passwordCookie = new Cookie("password", password);
				passwordCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
				resp.addCookie(passwordCookie);
			} else {
				// Xóa cookie nếu không chọn remember
				Cookie emailCookie = new Cookie("email", "");
				emailCookie.setMaxAge(0);
				resp.addCookie(emailCookie);
				
				Cookie passwordCookie = new Cookie("password", "");
				passwordCookie.setMaxAge(0);
				resp.addCookie(passwordCookie);
			}
			
			// Redirect dựa trên role
			int roleId = user.getRoleId();
			if (roleId == 1) { // ADMIN
				resp.sendRedirect(req.getContextPath() + "/dashboard");
			} else if (roleId == 2) { // LEADER
				resp.sendRedirect(req.getContextPath() + "/groupwork");
			} else { // USER
				resp.sendRedirect(req.getContextPath() + "/task");
			}
		} else {
			// Đăng nhập thất bại
			req.setAttribute("errorMessage", "Email hoặc mật khẩu không đúng!");
			req.setAttribute("email", email);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}

