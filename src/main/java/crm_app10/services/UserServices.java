package crm_app10.services;

import java.util.List;

import crm_app10.repository.UserRepository;
import crm_app10.entity.Users;

public class UserServices {
	
	private UserRepository userRepository = new UserRepository();
	
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}
	
	public boolean deleteUser(int idUser) {
		return userRepository.deleteById(idUser) > 0;
	}
	
	public Users getUserById(int id) {
		return userRepository.findById(id);
	}
	
	public Users login(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public boolean insertUser(String email, String password, String fullname, String avatar, int roleId) {
		return userRepository.save(email, password, fullname, avatar, roleId) > 0;
	}
	
	public boolean updateUser(int id, String email, String password, String fullname, String avatar, int roleId) {
		return userRepository.update(id, email, password, fullname, avatar, roleId) > 0;
	}
	
	public List<Users> getUsersByRole(int roleId) {
		return userRepository.findByRoleId(roleId);
	}
}
