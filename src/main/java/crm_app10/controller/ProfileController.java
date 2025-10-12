package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm_app10.services.RoleServices;
import crm_app10.services.TaskServices;
import crm_app10.services.UserServices;
import entity.Roles;
import entity.Tasks;
import entity.Users;

@WebServlet(name = "profileController", urlPatterns = {"/profile", "/profile-edit", "/user-details"})
public class ProfileController extends HttpServlet {
    
    private UserServices userServices = new UserServices();
    private TaskServices taskServices = new TaskServices();
    private RoleServices roleServices = new RoleServices();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        
        switch (path) {
            case "/profile":
                showProfile(req, resp);
                break;
            case "/profile-edit":
                showEditProfile(req, resp);
                break;
            case "/user-details":
                showUserDetails(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/dashboard");
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        
        if (path.equals("/profile-edit")) {
            updateProfile(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }
    
    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get current user ID from cookie
        int userId = getCurrentUserId(req);
        
        if (userId > 0) {
            Users user = userServices.getUserById(userId);
            List<Tasks> userTasks = taskServices.getTasksByUserId(userId);
            
            // Calculate task statistics
            int notStarted = 0;
            int inProgress = 0;
            int completed = 0;
            
            for (Tasks task : userTasks) {
                if (task.getStatusId() == 1) {
                    notStarted++;
                } else if (task.getStatusId() == 2) {
                    inProgress++;
                } else if (task.getStatusId() == 3) {
                    completed++;
                }
            }
            
            req.setAttribute("user", user);
            req.setAttribute("userTasks", userTasks);
            req.setAttribute("notStarted", notStarted);
            req.setAttribute("inProgress", inProgress);
            req.setAttribute("completed", completed);
            
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
    
    private void showEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = getCurrentUserId(req);
        
        if (userId > 0) {
            Users user = userServices.getUserById(userId);
            List<Roles> listRoles = roleServices.getAllRoles();
            
            req.setAttribute("user", user);
            req.setAttribute("listRoles", listRoles);
            req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
    
    private void showUserDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        
        if (id != null) {
            Users user = userServices.getUserById(Integer.parseInt(id));
            List<Tasks> userTasks = taskServices.getTasksByUserId(Integer.parseInt(id));
            
            // Calculate task statistics
            int notStarted = 0;
            int inProgress = 0;
            int completed = 0;
            
            for (Tasks task : userTasks) {
                if (task.getStatusId() == 1) {
                    notStarted++;
                } else if (task.getStatusId() == 2) {
                    inProgress++;
                } else if (task.getStatusId() == 3) {
                    completed++;
                }
            }
            
            req.setAttribute("user", user);
            req.setAttribute("userTasks", userTasks);
            req.setAttribute("notStarted", notStarted);
            req.setAttribute("inProgress", inProgress);
            req.setAttribute("completed", completed);
            
            req.getRequestDispatcher("user-details.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/user-table");
        }
    }
    
    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId = getCurrentUserId(req);
        
        if (userId > 0) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String fullname = req.getParameter("fullname");
            String avatar = req.getParameter("avatar");
            String roleId = req.getParameter("roleId");
            
            boolean result = userServices.updateUser(userId, email, password, fullname, avatar, Integer.parseInt(roleId));
            
            if (result) {
                resp.sendRedirect(req.getContextPath() + "/profile");
            } else {
                resp.sendRedirect(req.getContextPath() + "/profile-edit?error=1");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
    
    private int getCurrentUserId(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        return userId != null ? userId : 0;
    }
}
