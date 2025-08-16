package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Roles;
import entity.Users;

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
}
