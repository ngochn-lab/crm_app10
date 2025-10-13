package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app10.config.MySQLConfig;
import crm_app10.entity.Roles;

public class RoleRepository {
	
	public List<Roles> findAll() {
		List<Roles> listRoles = new ArrayList<Roles>();
		var query = "SELECT * FROM roles r";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Roles roles = new Roles();
				roles.setName(resultSet.getString("name"));
				roles.setId(resultSet.getInt("id"));
				roles.setDescription(resultSet.getString("description"));
				listRoles.add(roles);
			}
		} catch (Exception e) {
			System.out.println("findAll Roles " + e.getMessage());
		}
		return listRoles;
	}
	
	public int save(String name, String desc) {
		int rowCount = 0;
		
		String query = "INSERT INTO roles (name, description) VALUES (?, ?)";
		
		Connection connection = new MySQLConfig().getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, desc);
			
			rowCount = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		return rowCount;
	}
	
	public int update(int id, String name, String desc) {
		int rowCount = 0;
		String query = "UPDATE roles SET name = ?, description = ? WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, desc);
			statement.setInt(3, id);
			
			rowCount = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error update Role: " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public int delete(int id) {
		int rowCount = 0;
		String query = "DELETE FROM roles WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			rowCount = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error delete Role: " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public Roles findById(int id) {
		Roles role = null;
		String query = "SELECT * FROM roles WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				role = new Roles();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
			}
		} catch (Exception e) {
			System.out.println("Error findById Role: " + e.getMessage());
		}
		
		return role;
	}
}
