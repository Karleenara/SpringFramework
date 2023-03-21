package com.kosa.mycompany;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// �ڵ����� ��ü ������ ��
// Aspect �����Ǿ�� ��
@Component
@Aspect
public class AdviceUsingAnnotation {
	
	@Pointcut("execution(public * com.kosa.mycompany.*ServiceImpl.*(..))")
	private void publicTarget() {
		
	}
	
	// publicTarget�� ����
	@Around("publicTarget()")
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		// ProceedingJoinPoint - �Ű����� Ÿ��
		System.out.println("************* around start **************");
		
		// �Լ��� ��ġ�ؿ´�. joinPoint �Լ��� ���� ��� ���� ����
		// proceed �Լ� �̿��� ���� �Լ� ȣ��
		String classname = joinPoint.getTarget().getClass().getSimpleName();
		String methodname = joinPoint.getSignature().getName();
		
		System.out.println("Ŭ������: " + classname);
		System.out.println("�Լ���: " + methodname);
		
		long time1 = System.currentTimeMillis(); 	// ���� �ð� ��������
		Object retVal = joinPoint.proceed();		// ���� �Լ� ȣ��
		long time2 = System.currentTimeMillis(); 	// ���� �ð� ��������
		
		System.out.println("����ð�: " + (time2 - time1)+ "�и���");
		System.out.println("************* arround end **************");
		
		return retVal; // �Լ��� ��ȯ���� ����
	}
	
	
}
