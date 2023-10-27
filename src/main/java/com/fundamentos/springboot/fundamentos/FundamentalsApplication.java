package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentalsApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentalsApplication.class);
	private final ComponentDependency componentDependency;
	private final MyBean myBean;
	private final MyBeanWithDependency myBeanWithDependency;
	private final MyBeanWithProperties myBeanWithProperties;
	private final UserPojo userPojo;

	public FundamentalsApplication(ComponentDependency componentDependency,
								   MyBean myBean,
								   MyBeanWithDependency myBeanWithDependency,
								   MyBeanWithProperties myBeanWithProperties,
								   UserPojo userPojo){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.fullName("Miguel", "Cazares");
		myBean.print();
		myBeanWithDependency.printWithDependency(51, 5);
		String full_name = myBeanWithProperties.function();
		System.out.println(full_name);
		System.out.println(userPojo.getEmail() + " " + userPojo.getPassword() + " " + userPojo.getAge());
		try{
			int value = 10/0;
			LOGGER.debug("This is a debug message");
		}catch(Exception e){
			LOGGER.error("This is an error message " + e.getMessage());
		}
	}
}
