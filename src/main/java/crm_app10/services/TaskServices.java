package crm_app10.services;

import java.util.List;

import crm_app10.repository.TaskRepository;
import crm_app10.entity.Tasks;

public class TaskServices {
    
    private TaskRepository taskRepository = new TaskRepository();
    
    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Tasks getTaskById(int id) {
        return taskRepository.findById(id);
    }
    
    public boolean insertTask(String name, String startDate, String endDate, 
                              int userId, int projectId, int statusId) {
        return taskRepository.save(name, startDate, endDate, userId, projectId, statusId) > 0;
    }
    
    public boolean updateTask(int id, String name, String startDate, String endDate,
                             int userId, int projectId, int statusId) {
        return taskRepository.update(id, name, startDate, endDate, userId, projectId, statusId) > 0;
    }
    
    public boolean deleteTask(int id) {
        return taskRepository.delete(id) > 0;
    }
    
    public List<Tasks> getTasksByUserId(int userId) {
        return taskRepository.findByUserId(userId);
    }
    
    public List<Tasks> getTasksByProjectId(int projectId) {
        return taskRepository.findByProjectId(projectId);
    }
    
    public List<Tasks> getTasksByLeaderId(int leaderId) {
        return taskRepository.findByLeaderId(leaderId);
    }
    
    public boolean updateTaskStatus(int taskId, int statusId) {
        return taskRepository.updateStatus(taskId, statusId) > 0;
    }
}
