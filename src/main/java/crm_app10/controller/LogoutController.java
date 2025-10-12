package crm_app10.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "logoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xóa session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // Xóa cookies remember me
        Cookie emailCookie = new Cookie("email", "");
        emailCookie.setMaxAge(0);
        emailCookie.setPath(req.getContextPath());
        resp.addCookie(emailCookie);
        
        Cookie passwordCookie = new Cookie("password", "");
        passwordCookie.setMaxAge(0);
        passwordCookie.setPath(req.getContextPath());
        resp.addCookie(passwordCookie);
        
        // Redirect về trang login
        resp.sendRedirect(req.getContextPath() + "/login");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
