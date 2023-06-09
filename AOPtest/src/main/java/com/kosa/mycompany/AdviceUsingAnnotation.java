package com.kosa.mycompany;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// 자동으로 객체 만들어야 함
// Aspect 설정되어야 함
@Component
@Aspect
public class AdviceUsingAnnotation {
	
	@Pointcut("execution(public * com.kosa.mycompany.*ServiceImpl.*(..))")
	private void publicTarget() {
		
	}
	
	// publicTarget과 연결
	@Around("publicTarget()")
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		// ProceedingJoinPoint - 매개변수 타입
		System.out.println("************* around start **************");
		
		// 함수를 납치해온다. joinPoint 함수에 대한 모든 정보 있음
		// proceed 함수 이용해 원래 함수 호출
		String classname = joinPoint.getTarget().getClass().getSimpleName();
		String methodname = joinPoint.getSignature().getName();
		
		System.out.println("클래스명: " + classname);
		System.out.println("함수명: " + methodname);
		
		long time1 = System.currentTimeMillis(); 	// 현재 시간 가져오기
		Object retVal = joinPoint.proceed();		// 원래 함수 호출
		long time2 = System.currentTimeMillis(); 	// 종료 시간 가져오기
		
		System.out.println("실행시간: " + (time2 - time1)+ "밀리초");
		System.out.println("************* arround end **************");
		
		return retVal; // 함수의 반환값을 보냄
	}
	
	
}
