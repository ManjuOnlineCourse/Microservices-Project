package com.myproject.microservice.Service;

import com.myproject.microservice.Entity.Department;

public interface DepartmentService {

	Department saveUser(Department department);

	Department getUserById(Long departmentId);

}
