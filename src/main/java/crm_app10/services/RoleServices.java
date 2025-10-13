package crm_app10.services;

import java.util.List;

import crm_app10.repository.RoleRepository;
import crm_app10.entity.Roles;

public class RoleServices {
	
	private RoleRepository roleRepository = new RoleRepository();
	
	public List<Roles> getAllRoles() {
		return roleRepository.findAll();
	}
	
	public boolean insertRole(String name, String desc) {
		return roleRepository.save(name, desc) > 0;
	}
	
	public boolean updateRole(int id, String name, String desc) {
		return roleRepository.update(id, name, desc) > 0;
	}
	
	public boolean deleteRole(int id) {
		return roleRepository.delete(id) > 0;
	}
	
	public Roles getRoleById(int id) {
		return roleRepository.findById(id);
	}
}
