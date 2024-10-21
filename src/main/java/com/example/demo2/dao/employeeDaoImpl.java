package com.example.demo2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.example.demo2.model.employeeModel;

import javax.sql.DataSource;



@Repository
public class employeeDaoImpl implements employeeDao{


	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	 private final DataSource dataSource; // Assume you have a DataSource for connection management

	    public employeeDaoImpl(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }
	    
	   @Override
	    public List<employeeModel> findAll() {
	    	
	    	 List<employeeModel> entities = new ArrayList();
	         String query = "SELECT * FROM employee_data ORDER BY id ASC";

	         try (Connection connection = dataSource.getConnection();
	              PreparedStatement preparedStatement = connection.prepareStatement(query);
	        		 
	              ResultSet resultSet = preparedStatement.executeQuery()) {

	             while (resultSet.next()) {
	            	 employeeModel entity = new employeeModel();
	                 entity.setId(resultSet.getInt("id"));
	                 entity.setName(resultSet.getString("Name"));
	                 entity.setMobileNo(resultSet.getString("mobileNo"));
	                 entity.setEmailId(resultSet.getString("emailId"));
	                 // Set other fields as necessary
	                 entities.add(entity);
	             }

	         } catch (SQLException e) {
	            e.printStackTrace(); // Handle exceptions appropriately in a real application
	        }

	        return entities;
	    }
	   
	@Override
	public String saveData(String Name, String MobileNo, String EmailId) {
		System.out.println("name"+Name);
		  String sql = "INSERT INTO employee_data (\"Name\", \"MobileNo\", \"EmailId\") VALUES (?, ?, ?)";
		  jdbcTemplate.update(sql, Name, MobileNo, EmailId);
	
	        return "User saved successfully!";
		
	}
	
	@Override
    public List<employeeModel> getDataById(String id) {
    	
    	 List<employeeModel> entities = new ArrayList();
         String query = "SELECT * FROM employee_data where id = "+id;

         try (Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query);
        		 
              ResultSet resultSet = preparedStatement.executeQuery()) {

             while (resultSet.next()) {
            	 employeeModel entity = new employeeModel();
                 entity.setId(resultSet.getInt("id"));
                 entity.setName(resultSet.getString("Name"));
                 entity.setMobileNo(resultSet.getString("mobileNo"));
                 entity.setEmailId(resultSet.getString("emailId"));
                 // Set other fields as necessary
                 entities.add(entity);
             }

         } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
        }

        return entities;
    }

    
    @Override
	public String editData(String Name, String MobileNo, String EmailId,int id) {
		System.out.println("name"+id);
		String sql = "UPDATE employee_data SET \"Name\" = ?, \"MobileNo\" = ?, \"EmailId\" = ? WHERE \"id\" = ?";
		jdbcTemplate.update(sql, Name, MobileNo, EmailId, id);
	
	        return "User Updated successfully!";
		
	}
    
    @Override
  	public String deleteData(String id) {
  		System.out.println("id"+id);
  		String sql = "DELETE FROM employee_data WHERE \"id\" = ?";
  		jdbcTemplate.update(sql, Integer.parseInt(id));
  	
  	        return "User Deleted successfully!";
  		
  	}
    
}
