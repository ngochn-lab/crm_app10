package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Users;

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
}
