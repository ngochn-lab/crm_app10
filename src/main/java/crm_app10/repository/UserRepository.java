package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app10.config.MySQLConfig;
import crm_app10.entity.Users;

public class UserRepository {
	/**
	 *  Cách đặt tên hàm trong repo để gợi nhớ tới câu truy vấn
	 * 	Vi du: 	SELECT *		findByEmailAndPassword
	 * 			FROM users u
	 * 			WHERE u.email = '' AND u.password = ''
	 * 
	 * SELECT *			findByNameOrId
	 * FROM roles
	 * WHERE name = '' OR id = ''
	 */
	
	public List<Users> findAll() {
		List<Users> listUsers = new ArrayList<Users>();
		
		String query = "SELECT * FROM users u JOIN roles r ON u.role_id = r.id";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Users users = new Users();
				users.setFullname(resultSet.getString("fullname"));
				users.setEmail(resultSet.getString("email"));
				users.setId(resultSet.getInt("id"));
				users.setRoleDescription(resultSet.getString("description"));
				listUsers.add(users);
			}
			
		} catch (Exception e) {
			System.out.println("findAll User " + e.getMessage());
		}
		
		return listUsers;
	}
	
	public int deleteById(int id) {
		int rowCount = 0;
		
		String query = "DELETE FROM users u WHERE u.id = ?";
		
		Connection connection = new MySQLConfig().getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			rowCount = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public Users findById(int id) {
		Users user = null;
		String query = "SELECT u.*, r.description FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				user = new Users();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFullname(resultSet.getString("fullname"));
				user.setAvatar(resultSet.getString("avatar"));
				user.setRoleId(resultSet.getInt("role_id"));
				user.setRoleDescription(resultSet.getString("description"));
			}
		} catch (Exception e) {
			System.out.println("Error findById User: " + e.getMessage());
		}
		
		return user;
	}
	
	public Users findByEmailAndPassword(String email, String password) {
		Users user = null;
		String query = "SELECT u.*, r.description FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = ? AND u.password = ?";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				user = new Users();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFullname(resultSet.getString("fullname"));
				user.setAvatar(resultSet.getString("avatar"));
				user.setRoleId(resultSet.getInt("role_id"));
				user.setRoleDescription(resultSet.getString("description"));
			}
		} catch (Exception e) {
			System.out.println("Error findByEmailAndPassword User: " + e.getMessage());
		}
		
		return user;
	}
	
	public int save(String email, String password, String fullname, String avatar, int roleId) {
		int rowCount = 0;
		String query = "INSERT INTO users (email, password, fullname, avatar, role_id) VALUES (?, ?, ?, ?, ?)";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, fullname);
			statement.setString(4, avatar);
			statement.setInt(5, roleId);
			
			rowCount = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error save User: " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public int update(int id, String email, String password, String fullname, String avatar, int roleId) {
		int rowCount = 0;
		String query = "UPDATE users SET email = ?, password = ?, fullname = ?, avatar = ?, role_id = ? WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, fullname);
			statement.setString(4, avatar);
			statement.setInt(5, roleId);
			statement.setInt(6, id);
			
			rowCount = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error update User: " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public List<Users> findByRoleId(int roleId) {
		List<Users> listUsers = new ArrayList<Users>();
		String query = "SELECT u.*, r.description FROM users u JOIN roles r ON u.role_id = r.id WHERE u.role_id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, roleId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Users user = new Users();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFullname(resultSet.getString("fullname"));
				user.setAvatar(resultSet.getString("avatar"));
				user.setRoleId(resultSet.getInt("role_id"));
				user.setRoleDescription(resultSet.getString("description"));
				listUsers.add(user);
			}
		} catch (Exception e) {
			System.out.println("Error findByRoleId User: " + e.getMessage());
		}
		
		return listUsers;
	}
}
