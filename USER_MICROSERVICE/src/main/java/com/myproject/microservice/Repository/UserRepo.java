package com.myproject.microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.microservice.Entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
