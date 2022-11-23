package AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class GeneralAOP {
	
	@Pointcut("execution(*com.myproject.microservice.Controller.*.*(..))")
	public void loggingPointCut() {}
	
	@Before("loggingPointCut()")
	public void before(JoinPoint joinPoint) {
		log.info("Before Controller.."+joinPoint.getSignature());
		System.out.println("--------------------");
	}
	@After("loggingPointCut()")
	public void after() {
		log.info("Before Controller..");
		System.out.println("--------------------");
	}

}
