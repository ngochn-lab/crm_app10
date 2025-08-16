package crm_app10.services;

import java.util.List;

import crm_app10.repository.RoleRepository;
import entity.Roles;

public class RoleServices {
	
	private RoleRepository roleRepository = new RoleRepository();
	
	public List<Roles> getAllRoles() {
		return roleRepository.findAll();
	}
}
