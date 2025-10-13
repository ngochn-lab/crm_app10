package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app10.config.MySQLConfig;
import crm_app10.entity.Projects;

public class ProjectRepository {
    
    public List<Projects> findAll() {
        List<Projects> listProjects = new ArrayList<>();
        String query = "SELECT p.*, u.fullname as user_fullname, u.email as user_email " +
                      "FROM projects p " +
                      "LEFT JOIN users u ON p.user_id = u.id";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Projects project = new Projects();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getString("start_date"));
                project.setEndDate(resultSet.getString("end_date"));
                project.setUserId(resultSet.getInt("user_id"));
                project.setUserFullname(resultSet.getString("user_fullname"));
                project.setUserName(resultSet.getString("user_email"));
                listProjects.add(project);
            }
        } catch (Exception e) {
            System.out.println("Error findAll Projects: " + e.getMessage());
        }
        
        return listProjects;
    }
    
    public Projects findById(int id) {
        Projects project = null;
        String query = "SELECT p.*, u.fullname as user_fullname, u.email as user_email " +
                      "FROM projects p " +
                      "LEFT JOIN users u ON p.user_id = u.id " +
                      "WHERE p.id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                project = new Projects();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getString("start_date"));
                project.setEndDate(resultSet.getString("end_date"));
                project.setUserId(resultSet.getInt("user_id"));
                project.setUserFullname(resultSet.getString("user_fullname"));
                project.setUserName(resultSet.getString("user_email"));
            }
        } catch (Exception e) {
            System.out.println("Error findById Projects: " + e.getMessage());
        }
        
        return project;
    }
    
    public int save(String name, String startDate, String endDate, int userId) {
        int rowCount = 0;
        String query = "INSERT INTO projects (name, start_date, end_date, user_id) VALUES (?, ?, ?, ?)";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.setInt(4, userId);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error save Projects: " + e.getMessage());
        }
        
        return rowCount;
    }
    
    public int update(int id, String name, String startDate, String endDate, int userId) {
        int rowCount = 0;
        String query = "UPDATE projects SET name = ?, start_date = ?, end_date = ?, user_id = ? WHERE id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.setInt(4, userId);
            statement.setInt(5, id);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error update Projects: " + e.getMessage());
        }
        
        return rowCount;
    }
    
    public int delete(int id) {
        int rowCount = 0;
        String query = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error delete Projects: " + e.getMessage());
        }
        
        return rowCount;
    }
    
    public List<Projects> findByUserId(int userId) {
        List<Projects> listProjects = new ArrayList<>();
        String query = "SELECT p.*, u.fullname as user_fullname, u.email as user_email " +
                      "FROM projects p " +
                      "LEFT JOIN users u ON p.user_id = u.id " +
                      "WHERE p.user_id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Projects project = new Projects();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getString("start_date"));
                project.setEndDate(resultSet.getString("end_date"));
                project.setUserId(resultSet.getInt("user_id"));
                project.setUserFullname(resultSet.getString("user_fullname"));
                project.setUserName(resultSet.getString("user_email"));
                listProjects.add(project);
            }
        } catch (Exception e) {
            System.out.println("Error findByUserId Projects: " + e.getMessage());
        }
        
        return listProjects;
    }
}
