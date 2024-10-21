package com.example.demo2.controller;

import java.util.List;
import java.util.Map;

import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo2.dao.employeeDao;
import com.example.demo2.model.employeeModel;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class employeeController {
	

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

	@Autowired 
	employeeDao fDao;

	@GetMapping(value = "/getData")
	public @ResponseBody List<employeeModel> getEmployees() {
		System.out.println("onclick");
		
		System.out.println("hello" + fDao.findAll());
		return fDao.findAll();
	
	}
	
	@PostMapping(value = "/getDataById")
	public @ResponseBody List<employeeModel> getDataById(String id) {
		System.out.println("onclickbyid");
		
		return fDao.getDataById(id);
	
	}
	
	@PostMapping(value = "/saveData")
	public @ResponseBody String saveEmployees(@RequestParam String name, @RequestParam String mobile_no, @RequestParam String email_id) {
		System.out.println("saveData");
		
//		String name = ""; //request.getParameter("name").toString();
//		String mobile_no = ""; //request.getParameter("mobile_no").toString();
//		String email_id = "";//request.getParameter("email_id").toString();
		System.out.println("hello00" + name);
		String msg = fDao.saveData(name,mobile_no,email_id);
		System.out.println("msg" + msg);
		
		return msg;
	
	}
	

	@PostMapping(value = "/saveDataReact")
	public @ResponseBody String saveEmployees(@RequestBody Map<String, String> body
			//@RequestParam String name, @RequestParam String mobile_no, @RequestParam String email_id
			) {
		System.out.println("saveData");
		
		String name = body.get("name");  //request.getParameter("name").toString();
		String mobile_no =  body.get("mobileNo");; //request.getParameter("mobile_no").toString();
		String email_id = body.get("emailId");//request.getParameter("email_id").toString();
		System.out.println("hello00" + name);
		String msg = fDao.saveData(name,mobile_no,email_id);
		System.out.println("msg" + msg);
		
		return msg;
	
	}
	
	@PostMapping(value = "/editData")
	public @ResponseBody String editData(@RequestParam String name, @RequestParam String mobile_no, @RequestParam String email_id, @RequestParam String id) {
		System.out.println("editData");
		
		System.out.println("hello00" + name);
		String msg = fDao.editData(name,mobile_no,email_id,Integer.parseInt(id));
		System.out.println("msg" + msg);
		
		return msg;
	
	}
	
	@PostMapping(value = "/editDataReact")
	public @ResponseBody String editData(@RequestBody Map<String, Object> body) {
		System.out.println("editData"+body);
		
		String name = (String) body.get("name");  
		String mobile_no =  (String) body.get("mobileNo");
		String email_id = (String) body.get("emailId");
		Integer id =    (Integer) body.get("editId");
		 
		System.out.println("hello00" + id);
		String msg = fDao.editData(name,mobile_no,email_id,id);
		System.out.println("msg" + msg);
		
		return msg;
	
	}
	
	@PostMapping(value = "/deleteData")
	public @ResponseBody String deleteData(@RequestParam String id) {
		System.out.println("deleteData");
		
		String msg = fDao.deleteData(id);
		System.out.println("msg" + msg);
		
		return msg;
	}
	
	@PostMapping(value = "/deleteDataReact")
	public @ResponseBody String deleteData(@RequestBody Map<String, Object> body) {
		System.out.println("deleteData");
		Integer id = (Integer) body.get("id");
		String msg = fDao.deleteData(id.toString());
		System.out.println("msg" + msg);
		
		return msg;
	}
	
	

}

