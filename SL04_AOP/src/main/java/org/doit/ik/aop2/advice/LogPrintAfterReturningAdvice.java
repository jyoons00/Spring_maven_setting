package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

// 실행 후 호출되는 공통기능 + 예외 X
@Component
public class LogPrintAfterReturningAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(
			Object returnValue,     	// 결과값 
			Method method, 			// add()
			Object[] args, 				// 3, 5
			Object target 				// 핵심기능 실제 객체
			) throws Throwable {
		
		String methodNmae = method.getName();
		Log log = LogFactory.getLog(this.getClass());
		// 인증 처리 부분 코딩.
		log.info("<<" + methodNmae+"() : AfterReturningAdvice 호출됨...");
		
		
	}
	
	

}
