package com.example.demo2.dao;

import java.util.List;

import com.example.demo2.model.employeeModel;


public interface employeeDao{

//	void insertEmployee(firstModel emp);
	
	List<employeeModel> findAll();
	String saveData(String name,String mobile_no,String email_id);
	List<employeeModel> getDataById(String id);
	String editData(String name,String mobile_no,String email_id,int id);
	String deleteData(String id);
	
}