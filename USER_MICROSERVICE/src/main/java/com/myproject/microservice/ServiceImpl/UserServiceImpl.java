package com.myproject.microservice.ServiceImpl;



import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myproject.microservice.Entity.User;
import com.myproject.microservice.Exception.ResourceNotFoundException;
import com.myproject.microservice.Repository.UserRepo;
import com.myproject.microservice.Service.EmailService;
import com.myproject.microservice.Service.UserService;
import com.myproject.microservice.VO.Department;
import com.myproject.microservice.VO.ResponseTemplateVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import util.EmailDetails;

@Service
public class UserServiceImpl implements UserService{

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired private EmailService emailService;
	

	@Override
	public User saveUser(User user) throws ResourceNotFoundException{
		if(!validate(user.getEmail()))
			throw new ResourceNotFoundException("Email Error.");
		else
		{
		User save = userRepo.save(user);
		String body = "Hi,\n\n"+"User details\n\n"+
						"First Name : " + save.getFirstname()+ 
						"\nLast Name : " + save.getLastname()+ 
						"\nEmail : "+ save.getEmail() +
						"\n\n\nThnaks,\nManju & Team";
		String sendSimpleMail = emailService.sendSimpleMail(new EmailDetails(save.getEmail(), body, "User is Created with id : " +save.getId()));
		System.out.println(sendSimpleMail);
		return save;
		}
	}

	
	@Override
	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		ResponseTemplateVO responseTemplateVO=new ResponseTemplateVO();
		
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","ID",userId));
		
		
		Department department = restTemplate.getForObject("http://localhost:9001/api/department/"+user.getDepartmentId(), Department.class);
		//Department department = restTemplate.getForObject("http://DEPARTMENT_MICROSERVICE/api/department/"+user.getDepartmentId(), Department.class);
		
		//System.out.println(department.getDname()+" : "+department.getDepartId());
		
		responseTemplateVO.setUser(user);
		responseTemplateVO.setDepartment(department);
		
		//System.out.println(responseTemplateVO.getDepartment());
		return responseTemplateVO;
	}
	
	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
	
	
}
