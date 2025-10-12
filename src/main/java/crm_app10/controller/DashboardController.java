package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app10.services.TaskServices;
import entity.Tasks;

@WebServlet(name = "dashboardController", urlPatterns = {"/dashboard", "/"})
public class DashboardController extends HttpServlet {
    
    private TaskServices taskServices = new TaskServices();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get all tasks to calculate statistics
        List<Tasks> allTasks = taskServices.getAllTasks();
        
        int notStarted = 0;
        int inProgress = 0;
        int completed = 0;
        
        for (Tasks task : allTasks) {
            if (task.getStatusId() == 1) {
                notStarted++;
            } else if (task.getStatusId() == 2) {
                inProgress++;
            } else if (task.getStatusId() == 3) {
                completed++;
            }
        }
        
        int total = allTasks.size();
        
        // Calculate percentages
        int notStartedPercent = total > 0 ? (notStarted * 100) / total : 0;
        int inProgressPercent = total > 0 ? (inProgress * 100) / total : 0;
        int completedPercent = total > 0 ? (completed * 100) / total : 0;
        
        // Set attributes for JSP
        req.setAttribute("notStarted", notStarted);
        req.setAttribute("inProgress", inProgress);
        req.setAttribute("completed", completed);
        req.setAttribute("notStartedPercent", notStartedPercent);
        req.setAttribute("inProgressPercent", inProgressPercent);
        req.setAttribute("completedPercent", completedPercent);
        
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
