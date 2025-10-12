package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 * Authorization Filter - Kiểm tra quyền truy cập theo role
 * ADMIN (roleId = 1): Full quyền
 * LEADER (roleId = 2): Quản lý dự án và task
 * USER (roleId = 3): Chỉ xem và cập nhật task của mình
 */
@WebFilter(filterName = "authorizationFilter", urlPatterns = {"/*"})
public class AuthorizationFilter implements Filter {
    
    private static final int ROLE_ADMIN = 1;
    private static final int ROLE_LEADER = 2;
    private static final int ROLE_USER = 3;
    
    // Map URL patterns to required roles
    private static final Map<String, Set<Integer>> URL_PERMISSIONS = new HashMap<>();
    
    static {
        // Admin only URLs - Quản lý user và role
        Set<Integer> adminOnly = new HashSet<>();
        adminOnly.add(ROLE_ADMIN);
        URL_PERMISSIONS.put("/user-table", adminOnly);
        URL_PERMISSIONS.put("/user-add", adminOnly);
        URL_PERMISSIONS.put("/user-edit", adminOnly);
        URL_PERMISSIONS.put("/user-delete", adminOnly);
        URL_PERMISSIONS.put("/role-table", adminOnly);
        URL_PERMISSIONS.put("/role-add", adminOnly);
        URL_PERMISSIONS.put("/role-edit", adminOnly);
        URL_PERMISSIONS.put("/role-delete", adminOnly);
        
        // Admin and Leader URLs - Quản lý dự án
        Set<Integer> adminAndLeader = new HashSet<>();
        adminAndLeader.add(ROLE_ADMIN);
        adminAndLeader.add(ROLE_LEADER);
        URL_PERMISSIONS.put("/groupwork", adminAndLeader);
        URL_PERMISSIONS.put("/groupwork-add", adminAndLeader);
        URL_PERMISSIONS.put("/groupwork-edit", adminAndLeader);
        URL_PERMISSIONS.put("/groupwork-delete", adminAndLeader);
        URL_PERMISSIONS.put("/groupwork-details", adminAndLeader);
        URL_PERMISSIONS.put("/task-add", adminAndLeader);
        URL_PERMISSIONS.put("/task-edit", adminAndLeader);
        URL_PERMISSIONS.put("/task-delete", adminAndLeader);
        
        // All authenticated users - Xem task và profile
        Set<Integer> allRoles = new HashSet<>();
        allRoles.add(ROLE_ADMIN);
        allRoles.add(ROLE_LEADER);
        allRoles.add(ROLE_USER);
        URL_PERMISSIONS.put("/dashboard", allRoles);
        URL_PERMISSIONS.put("/", allRoles);
        URL_PERMISSIONS.put("/task", allRoles);
        URL_PERMISSIONS.put("/task-update-status", allRoles);
        URL_PERMISSIONS.put("/profile", allRoles);
        URL_PERMISSIONS.put("/profile-edit", allRoles);
        URL_PERMISSIONS.put("/user-details", allRoles);
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String path = req.getServletPath();
        
        // Skip authorization for login and resources
        if (path.equals("/login") || path.equals("/logout") || path.startsWith("/plugins/") 
                || path.startsWith("/bootstrap/") || path.startsWith("/css/") 
                || path.startsWith("/js/") || path.endsWith(".css") 
                || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }
        
        HttpSession session = req.getSession();
        Integer roleId = (Integer) session.getAttribute("roleId");
        
        // If no role in session, authentication should have caught this
        if (roleId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        // Check if URL requires specific permissions
        Set<Integer> allowedRoles = URL_PERMISSIONS.get(path);
        
        if (allowedRoles == null) {
            // No specific permission required, allow all authenticated users
            chain.doFilter(request, response);
            return;
        }
        
        if (allowedRoles.contains(roleId)) {
            // User has permission
            chain.doFilter(request, response);
        } else {
            // User doesn't have permission - redirect to error or dashboard
            session.setAttribute("errorMessage", "Bạn không có quyền truy cập chức năng này!");
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        }
    }
}
