package org.doit.ik.di2;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Setter;

// p66 
// p85: 자바 코드를 이용한 스프링 설정 
// application-context.xml == 자바 클래스 설정 파일

@Configuration // config 파일이 스프링 설정을 나타내는 클래스다 라는 것을 명시한다.
public class Config {
	
	/* <bean id="record" class="org.doit.ik.di.RecordImpl"></bean> */
	@Bean
	public RecordImpl record() { 
		return new RecordImpl();
	}
	
	 /*
	 <bean id="rvi" class="org.doit.ik.di.RecordViewImpl">
	   <property name="record"> 
	     <ref bean="record" />
	   </property>
	 </bean> 
	 */
	@Bean (name = "rvi") // [2] name을 따로 부여할 수도 있고
	public RecordViewImpl getRecordViewImpl() { 	// [1] getRecordViewImpl을 rvi로 바꾼다.
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record());
		return rvi;
	}
	
	
	
} // class
