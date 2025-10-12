package crm_app10.services;

import java.util.List;

import crm_app10.repository.StatusRepository;
import entity.Status;

public class StatusServices {
    
    private StatusRepository statusRepository = new StatusRepository();
    
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }
    
    public Status getStatusById(int id) {
        return statusRepository.findById(id);
    }
    
    public boolean insertStatus(String name) {
        return statusRepository.save(name) > 0;
    }
    
    public boolean updateStatus(int id, String name) {
        return statusRepository.update(id, name) > 0;
    }
    
    public boolean deleteStatus(int id) {
        return statusRepository.delete(id) > 0;
    }
}
