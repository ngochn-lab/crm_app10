package crm_app10.services;

import java.util.List;

import crm_app10.repository.ProjectRepository;
import crm_app10.entity.Projects;

public class ProjectServices {
    
    private ProjectRepository projectRepository = new ProjectRepository();
    
    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }
    
    public Projects getProjectById(int id) {
        return projectRepository.findById(id);
    }
    
    public boolean insertProject(String name, String startDate, String endDate, int userId) {
        return projectRepository.save(name, startDate, endDate, userId) > 0;
    }
    
    public boolean updateProject(int id, String name, String startDate, String endDate, int userId) {
        return projectRepository.update(id, name, startDate, endDate, userId) > 0;
    }
    
    public boolean deleteProject(int id) {
        return projectRepository.delete(id) > 0;
    }
    
    public List<Projects> getProjectsByUserId(int userId) {
        return projectRepository.findByUserId(userId);
    }
}
