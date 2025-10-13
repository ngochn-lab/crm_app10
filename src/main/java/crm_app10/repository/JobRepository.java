package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app10.config.MySQLConfig;
import crm_app10.entity.Jobs;

public class JobRepository {

	public List<Jobs> findAll() {
		List<Jobs> listJobs = new ArrayList<Jobs>();
		var query = "SELECT * FROM jobs j";

		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Jobs jobs = new Jobs();
				jobs.setName(resultSet.getString("name"));
				jobs.setId(resultSet.getInt("id"));
				jobs.setStartDate(resultSet.getString("start_date"));
				jobs.setEndDate(resultSet.getString("end_date"));
				listJobs.add(jobs);
			}
		} catch (Exception e) {
			System.out.println("findAll Jobs " + e.getMessage());
		}
		return listJobs;
	}
}
