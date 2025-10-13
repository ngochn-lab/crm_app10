package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app10.config.MySQLConfig;
import crm_app10.entity.Tasks;

public class TaskRepository {
    
    public List<Tasks> findAll() {
        List<Tasks> listTasks = new ArrayList<>();
        String query = "SELECT t.*, u.fullname as user_fullname, u.email as user_email, " +
                      "p.name as project_name, s.name as status_name " +
                      "FROM tasks t " +
                      "LEFT JOIN users u ON t.user_id = u.id " +
                      "LEFT JOIN projects p ON t.project_id = p.id " +
                      "LEFT JOIN status s ON t.status_id = s.id";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getString("start_date"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setUserId(resultSet.getInt("user_id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setStatusId(resultSet.getInt("status_id"));
                task.setUserFullname(resultSet.getString("user_fullname"));
                task.setUserName(resultSet.getString("user_email"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setStatusName(resultSet.getString("status_name"));
                listTasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error findAll Tasks: " + e.getMessage());
        }
        
        return listTasks;
    }

    public List<Tasks> findByLeaderId(int leaderId) {
        List<Tasks> listTasks = new ArrayList<>();
        String query = "SELECT t.*, u.fullname as user_fullname, u.email as user_email, " +
                      "p.name as project_name, s.name as status_name " +
                      "FROM tasks t " +
                      "LEFT JOIN users u ON t.user_id = u.id " +
                      "LEFT JOIN projects p ON t.project_id = p.id " +
                      "LEFT JOIN status s ON t.status_id = s.id " +
                      "WHERE p.user_id = ?";

        Connection connection = MySQLConfig.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, leaderId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getString("start_date"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setUserId(resultSet.getInt("user_id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setStatusId(resultSet.getInt("status_id"));
                task.setUserFullname(resultSet.getString("user_fullname"));
                task.setUserName(resultSet.getString("user_email"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setStatusName(resultSet.getString("status_name"));
                listTasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error findByLeaderId Tasks: " + e.getMessage());
        }

        return listTasks;
    }
    
    public Tasks findById(int id) {
        Tasks task = null;
        String query = "SELECT t.*, u.fullname as user_fullname, u.email as user_email, " +
                      "p.name as project_name, s.name as status_name " +
                      "FROM tasks t " +
                      "LEFT JOIN users u ON t.user_id = u.id " +
                      "LEFT JOIN projects p ON t.project_id = p.id " +
                      "LEFT JOIN status s ON t.status_id = s.id " +
                      "WHERE t.id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getString("start_date"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setUserId(resultSet.getInt("user_id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setStatusId(resultSet.getInt("status_id"));
                task.setUserFullname(resultSet.getString("user_fullname"));
                task.setUserName(resultSet.getString("user_email"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setStatusName(resultSet.getString("status_name"));
            }
        } catch (Exception e) {
            System.out.println("Error findById Tasks: " + e.getMessage());
        }
        
        return task;
    }
    
    public int save(String name, String startDate, String endDate, int userId, int projectId, int statusId) {
        int rowCount = 0;
        String query = "INSERT INTO tasks (name, start_date, end_date, user_id, project_id, status_id) " +
                      "VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.setInt(4, userId);
            statement.setInt(5, projectId);
            statement.setInt(6, statusId);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error save Tasks: " + e.getMessage());
        }
        
        return rowCount;
    }
    
    public int update(int id, String name, String startDate, String endDate, 
                     int userId, int projectId, int statusId) {
        int rowCount = 0;
        String query = "UPDATE tasks SET name = ?, start_date = ?, end_date = ?, " +
                      "user_id = ?, project_id = ?, status_id = ? WHERE id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.setInt(4, userId);
            statement.setInt(5, projectId);
            statement.setInt(6, statusId);
            statement.setInt(7, id);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error update Tasks: " + e.getMessage());
        }
        
        return rowCount;
    }
    
    public int delete(int id) {
        int rowCount = 0;
        String query = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error delete Tasks: " + e.getMessage());
        }
        
        return rowCount;
    }
    
    public List<Tasks> findByUserId(int userId) {
        List<Tasks> listTasks = new ArrayList<>();
        String query = "SELECT t.*, u.fullname as user_fullname, u.email as user_email, " +
                      "p.name as project_name, s.name as status_name " +
                      "FROM tasks t " +
                      "LEFT JOIN users u ON t.user_id = u.id " +
                      "LEFT JOIN projects p ON t.project_id = p.id " +
                      "LEFT JOIN status s ON t.status_id = s.id " +
                      "WHERE t.user_id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getString("start_date"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setUserId(resultSet.getInt("user_id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setStatusId(resultSet.getInt("status_id"));
                task.setUserFullname(resultSet.getString("user_fullname"));
                task.setUserName(resultSet.getString("user_email"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setStatusName(resultSet.getString("status_name"));
                listTasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error findByUserId Tasks: " + e.getMessage());
        }
        
        return listTasks;
    }
    
    public List<Tasks> findByProjectId(int projectId) {
        List<Tasks> listTasks = new ArrayList<>();
        String query = "SELECT t.*, u.fullname as user_fullname, u.email as user_email, " +
                      "p.name as project_name, s.name as status_name " +
                      "FROM tasks t " +
                      "LEFT JOIN users u ON t.user_id = u.id " +
                      "LEFT JOIN projects p ON t.project_id = p.id " +
                      "LEFT JOIN status s ON t.status_id = s.id " +
                      "WHERE t.project_id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getString("start_date"));
                task.setEndDate(resultSet.getString("end_date"));
                task.setUserId(resultSet.getInt("user_id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setStatusId(resultSet.getInt("status_id"));
                task.setUserFullname(resultSet.getString("user_fullname"));
                task.setUserName(resultSet.getString("user_email"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setStatusName(resultSet.getString("status_name"));
                listTasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error findByProjectId Tasks: " + e.getMessage());
        }
        
        return listTasks;
    }
    
    public int updateStatus(int taskId, int statusId) {
        int rowCount = 0;
        String query = "UPDATE tasks SET status_id = ? WHERE id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, statusId);
            statement.setInt(2, taskId);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updateStatus Tasks: " + e.getMessage());
        }
        
        return rowCount;
    }
}
