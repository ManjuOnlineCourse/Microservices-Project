package com.myproject.cloud.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class CircuitBreackerController {
	
	@GetMapping("/userCallBackMethod")
	public ResponseEntity<String> userCallBackMethod(){
		
		return new ResponseEntity<String>("Something went wrong please try again later.",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
