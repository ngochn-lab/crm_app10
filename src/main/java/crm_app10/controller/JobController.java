package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app10.services.JobServices;
import crm_app10.entity.Jobs;

@WebServlet(name = "jobController", urlPatterns = {"/job"})
public class JobController extends HttpServlet {
	
	private JobServices jobServices = new JobServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Jobs> listJobs = jobServices.getAllJobs();
		req.setAttribute("listJob", listJobs);
		req.getRequestDispatcher("job-table.jsp").forward(req, resp);
	}
}
