package com.revature.clp.ecommerce.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	// @Around is the advice
	// execution(...) is the point cut expression

	@Around("execution(* com.revature.clp.ecommerce.project..*(..)))")
	public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		// Get intercepted method details
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		final StopWatch stopWatch = new StopWatch();

		// Measure method execution time
		stopWatch.start(); // all this is before the actual method is called
		Object result = proceedingJoinPoint.proceed(); // in this line the actual methods get called
		stopWatch.stop(); // all this is after the actual method is called

		// Log method execution time
		LOGGER.info("Execution time of " + className + "." + methodName + " " + ":: " + stopWatch.getTotalTimeMillis()
				+ " ms");

		return result;
	}

	@Around("execution(* com.revature.clp.ecommerce.project..*(..)))")
	public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		// Get intercepted method details
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		LOGGER.info("Entered " + methodName + " of " + className);
		Object result = proceedingJoinPoint.proceed(); // in this line the actual methods get called
		LOGGER.info("Exited " + methodName + " of " + className);

		return result;
	}
	

}
