package com.myproject.microservice.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.microservice.Entity.Department;
import com.myproject.microservice.Exception.ResourceNotFoundException;
import com.myproject.microservice.Repository.DepartmentRepo;
import com.myproject.microservice.Service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepo departmentRepo;

	@Override
	public Department saveUser(Department department) {
		return departmentRepo.save(department);
	}

	@Override
	public Department getUserById(Long departmentId) {
		return this.departmentRepo.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("User does not aling to any department"));
	}
	
}
