package com.myproject.microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.microservice.Entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long>{

}
