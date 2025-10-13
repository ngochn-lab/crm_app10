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
import crm_app10.services.StatusServices;
import crm_app10.services.TaskServices;
import crm_app10.services.UserServices;
import crm_app10.entity.Projects;
import crm_app10.entity.Status;
import crm_app10.entity.Tasks;
import crm_app10.entity.Users;

@WebServlet(name = "taskController", urlPatterns = {"/task", "/task-add", "/task-edit", "/task-delete", "/task-update-status"})
public class TaskController extends HttpServlet {
    
    private TaskServices taskServices = new TaskServices();
    private UserServices userServices = new UserServices();
    private ProjectServices projectServices = new ProjectServices();
    private StatusServices statusServices = new StatusServices();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        
        switch (path) {
            case "/task":
                showTaskList(req, resp);
                break;
            case "/task-add":
                showAddForm(req, resp);
                break;
            case "/task-edit":
                showEditForm(req, resp);
                break;
            case "/task-delete":
                deleteTask(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/task");
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        
        switch (path) {
            case "/task-add":
                addTask(req, resp);
                break;
            case "/task-edit":
                updateTask(req, resp);
                break;
            case "/task-update-status":
                updateTaskStatus(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/task");
                break;
        }
    }
    
    private void showTaskList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Integer roleId = (Integer) session.getAttribute("roleId");
        
        List<Tasks> listTasks;
        
        if (roleId == 1) { // ADMIN - xem tất cả task
            listTasks = taskServices.getAllTasks();
        } else if (roleId == 2) { // LEADER - xem task của dự án mình quản lý
            listTasks = taskServices.getTasksByLeaderId(userId);
        } else { // USER - chỉ xem task của mình
            listTasks = taskServices.getTasksByUserId(userId);
        }
        
        req.setAttribute("listTasks", listTasks);
        req.getRequestDispatcher("task.jsp").forward(req, resp);
    }
    
    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> listUsers = userServices.getAllUsers();
        List<Projects> listProjects = projectServices.getAllProjects();
        List<Status> listStatus = statusServices.getAllStatus();
        
        req.setAttribute("listUsers", listUsers);
        req.setAttribute("listProjects", listProjects);
        req.setAttribute("listStatus", listStatus);
        req.getRequestDispatcher("task-add.jsp").forward(req, resp);
    }
    
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Tasks task = taskServices.getTaskById(Integer.parseInt(id));
            List<Users> listUsers = userServices.getAllUsers();
            List<Projects> listProjects = projectServices.getAllProjects();
            List<Status> listStatus = statusServices.getAllStatus();
            
            req.setAttribute("task", task);
            req.setAttribute("listUsers", listUsers);
            req.setAttribute("listProjects", listProjects);
            req.setAttribute("listStatus", listStatus);
            req.getRequestDispatcher("task-add.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/task");
        }
    }
    
    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String userId = req.getParameter("userId");
        String projectId = req.getParameter("projectId");
        String statusId = req.getParameter("statusId");
        
        boolean result = taskServices.insertTask(name, startDate, endDate, 
                                                 Integer.parseInt(userId), 
                                                 Integer.parseInt(projectId), 
                                                 Integer.parseInt(statusId));
        
        if (result) {
            resp.sendRedirect(req.getContextPath() + "/task");
        } else {
            resp.sendRedirect(req.getContextPath() + "/task-add?error=1");
        }
    }
    
    private void updateTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String userId = req.getParameter("userId");
        String projectId = req.getParameter("projectId");
        String statusId = req.getParameter("statusId");
        
        boolean result = taskServices.updateTask(Integer.parseInt(id), name, startDate, endDate,
                                                 Integer.parseInt(userId),
                                                 Integer.parseInt(projectId),
                                                 Integer.parseInt(statusId));
        
        if (result) {
            resp.sendRedirect(req.getContextPath() + "/task");
        } else {
            resp.sendRedirect(req.getContextPath() + "/task-edit?id=" + id + "&error=1");
        }
    }
    
    private void updateTaskStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String taskId = req.getParameter("taskId");
        String statusId = req.getParameter("statusId");
        
        if (taskId != null && statusId != null) {
            taskServices.updateTaskStatus(Integer.parseInt(taskId), Integer.parseInt(statusId));
        }
        
        // Check if request came from project details page
        String projectId = req.getParameter("projectId");
        if (projectId != null) {
            resp.sendRedirect(req.getContextPath() + "/groupwork-details?id=" + projectId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/task");
        }
    }
    
    private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            taskServices.deleteTask(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/task");
    }
}
