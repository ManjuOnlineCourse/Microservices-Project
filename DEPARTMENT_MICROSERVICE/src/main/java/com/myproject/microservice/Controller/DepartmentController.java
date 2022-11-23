package com.myproject.microservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.microservice.Entity.Department;
import com.myproject.microservice.Service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/department")
@Slf4j
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/")
	public Department saveUser(@RequestBody Department department) {
		log.info("saveUser");
		System.out.println("saveUser ");
		return departmentService.saveUser(department);
		
	}

	@GetMapping("/{departmentId}")
	public ResponseEntity<Department> getUserById(@PathVariable Long departmentId) {
		return new ResponseEntity<Department>( this.departmentService.getUserById(departmentId), HttpStatus.OK);
		
	}
}
