package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Authentication Filter - Kiểm tra người dùng đã đăng nhập chưa
 * Áp dụng cho tất cả URL trừ login và resources
 */
@WebFilter(filterName = "authenFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String path = req.getServletPath();
		
		// Cho phép truy cập không cần đăng nhập với các resource và trang login
		if (path.equals("/login") || path.startsWith("/plugins/") || path.startsWith("/bootstrap/") 
				|| path.startsWith("/css/") || path.startsWith("/js/") || path.endsWith(".css") 
				|| path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")) {
			chain.doFilter(request, response);
			return;
		}
		
		// Kiểm tra session
		HttpSession session = req.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		Integer roleId = (Integer) session.getAttribute("roleId");
		
		if (userId != null && roleId != null) {
			// Đã đăng nhập - cho đi tiếp
			chain.doFilter(request, response);
		} else {
			// Chưa đăng nhập - redirect về login
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

}
