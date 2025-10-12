package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Status;

public class StatusRepository {
    
    public List<Status> findAll() {
        List<Status> listStatus = new ArrayList<>();
        String query = "SELECT * FROM status";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
                listStatus.add(status);
            }
        } catch (Exception e) {
            System.out.println("Error findAll Status: " + e.getMessage());
        }
        
        return listStatus;
    }
    
    public Status findById(int id) {
        Status status = null;
        String query = "SELECT * FROM status WHERE id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error findById Status: " + e.getMessage());
        }
        
        return status;
    }
    
    public int save(String name) {
        int rowCount = 0;
        String query = "INSERT INTO status (name) VALUES (?)";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error save Status: " + e.getMessage());
        }
        
        return rowCount;
    }
    
    public int update(int id, String name) {
        int rowCount = 0;
        String query = "UPDATE status SET name = ? WHERE id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, id);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error update Status: " + e.getMessage());
        }
        
        return rowCount;
    }
    
    public int delete(int id) {
        int rowCount = 0;
        String query = "DELETE FROM status WHERE id = ?";
        
        Connection connection = MySQLConfig.getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error delete Status: " + e.getMessage());
        }
        
        return rowCount;
    }
}
