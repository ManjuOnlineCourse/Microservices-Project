package com.myproject.microservice.Service;

import com.myproject.microservice.Entity.User;
import com.myproject.microservice.Exception.ResourceNotFoundException;
import com.myproject.microservice.VO.ResponseTemplateVO;

public interface UserService {

	User saveUser(User user) throws ResourceNotFoundException;

	ResponseTemplateVO getUserWithDepartment(Long userId);

}
