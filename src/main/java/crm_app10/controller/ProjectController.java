package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm_app10.services.ProjectServices;
import crm_app10.services.TaskServices;
import crm_app10.services.UserServices;
import crm_app10.entity.Projects;
import crm_app10.entity.Tasks;
import crm_app10.entity.Users;

@WebServlet(name = "projectController", urlPatterns = {"/groupwork", "/groupwork-add", "/groupwork-details", "/groupwork-delete", "/groupwork-edit"})
public class ProjectController extends HttpServlet {
    
    private ProjectServices projectServices = new ProjectServices();
    private UserServices userServices = new UserServices();
    private TaskServices taskServices = new TaskServices();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        
        switch (path) {
            case "/groupwork":
                showProjectList(req, resp);
                break;
            case "/groupwork-add":
                showAddForm(req, resp);
                break;
            case "/groupwork-details":
                showProjectDetails(req, resp);
                break;
            case "/groupwork-delete":
                deleteProject(req, resp);
                break;
            case "/groupwork-edit":
                showEditForm(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/groupwork");
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        
        switch (path) {
            case "/groupwork-add":
                addProject(req, resp);
                break;
            case "/groupwork-edit":
                updateProject(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/groupwork");
                break;
        }
    }
    
    private void showProjectList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession();
    	Integer userId = (Integer) session.getAttribute("userId");
        Integer roleId = (Integer) session.getAttribute("roleId");
        
        List<Projects> listProjects;
        
        if (roleId == 1) {
        	listProjects = projectServices.getAllProjects();
        } else {
        	listProjects = projectServices.getProjectsByUserId(userId);
        }
        
        req.setAttribute("listProjects", listProjects);
        req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
    }
    
    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> listUsers = userServices.getAllUsers();
        req.setAttribute("listUsers", listUsers);
        req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
    }
    
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Projects project = projectServices.getProjectById(Integer.parseInt(id));
            List<Users> listUsers = userServices.getAllUsers();
            req.setAttribute("project", project);
            req.setAttribute("listUsers", listUsers);
            req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/groupwork");
        }
    }
    
    private void showProjectDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Projects project = projectServices.getProjectById(Integer.parseInt(id));
            List<Tasks> projectTasks = taskServices.getTasksByProjectId(Integer.parseInt(id));
            
            // Calculate statistics
            int notStarted = 0;
            int inProgress = 0;
            int completed = 0;
            
            for (Tasks task : projectTasks) {
                if (task.getStatusId() == 1) {
                    notStarted++;
                } else if (task.getStatusId() == 2) {
                    inProgress++;
                } else if (task.getStatusId() == 3) {
                    completed++;
                }
            }
            
            req.setAttribute("project", project);
            req.setAttribute("projectTasks", projectTasks);
            req.setAttribute("notStarted", notStarted);
            req.setAttribute("inProgress", inProgress);
            req.setAttribute("completed", completed);
            
            req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/groupwork");
        }
    }
    
    private void addProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String userId = req.getParameter("userId");
        
        boolean result = projectServices.insertProject(name, startDate, endDate, Integer.parseInt(userId));
        
        if (result) {
            resp.sendRedirect(req.getContextPath() + "/groupwork");
        } else {
            resp.sendRedirect(req.getContextPath() + "/groupwork-add?error=1");
        }
    }
    
    private void updateProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String userId = req.getParameter("userId");
        
        boolean result = projectServices.updateProject(Integer.parseInt(id), name, startDate, endDate, Integer.parseInt(userId));
        
        if (result) {
            resp.sendRedirect(req.getContextPath() + "/groupwork");
        } else {
            resp.sendRedirect(req.getContextPath() + "/groupwork-edit?id=" + id + "&error=1");
        }
    }
    
    private void deleteProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            projectServices.deleteProject(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/groupwork");
    }
}
