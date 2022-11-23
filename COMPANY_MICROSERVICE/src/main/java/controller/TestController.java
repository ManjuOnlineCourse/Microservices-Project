package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class TestController {

	
	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping("/test/{id}")
	@CircuitBreaker(name = "serviceA")
	public ResponseEntity<Department> getDepartment(@PathVariable Long id){
		
		return new ResponseEntity<Department>(restTemplate.getForObject("http://localhost:9001/api/department/"+id, Department.class),HttpStatus.OK);
		
	}
	public String fallback(Exception exception) {
		return "Please try again later";
	}
	
	@GetMapping()
	public String test()
	{
		return "hello";
	}
}
