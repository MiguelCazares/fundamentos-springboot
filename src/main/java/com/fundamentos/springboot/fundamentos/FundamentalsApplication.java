package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentalsApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentalsApplication.class);
	private final ComponentDependency componentDependency;
	private final MyBean myBean;
	private final MyBeanWithDependency myBeanWithDependency;
	private final MyBeanWithProperties myBeanWithProperties;
	private final UserPojo userPojo;

	private final UserRepository userRepository;
	private final UserService userService;

	public FundamentalsApplication(ComponentDependency componentDependency,
								   MyBean myBean,
								   MyBeanWithDependency myBeanWithDependency,
								   MyBeanWithProperties myBeanWithProperties,
								   UserPojo userPojo,
								   UserRepository userRepository,
								   UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//dependencyInjection();
		//saveUserInDataBase();
		//getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		User user1 = new User("Miguel", "cazares@gmail.com", LocalDate.of(1999, 8, 2));
		User user2 = new User("Angel", "cazares2@gmail.com", LocalDate.of(1998, 1, 29));
		List<User> users = Arrays.asList(user1, user2);
		try {
			userService.saveTransactional(users);
		} catch (Exception e){
			LOGGER.error("This is an exception inside the transactional method " + e.getMessage());
		}
		userService.getAllUsers()
				.forEach(user -> LOGGER.info("This is the user inside the transactional method: " + user));
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("User with email: " + userRepository.findByEmail("Cazares@gmail.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("Luis", Sort.by("id").descending())
				.forEach(user -> LOGGER.info("User with method sort: " + user));

	 	LOGGER.info("findByEmailAndName: " + userRepository.findByEmailAndName("Cazares@gmail.com", "Miguel")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario con el metodo findByEmailAndName")));
	}

	private void saveUserInDataBase(){
		User user1 = new User("Miguel", "Cazares@gmail.com", LocalDate.of(1999, 8, 2));
		User user2 = new User("Angel", "Soledad@gmail.com", LocalDate.of(1998, 1, 29));
		User user3 = new User("Luis", "luis@gmai.com", LocalDate.of(1999, 8, 2));
		User user4 = new User("Luis2", "luis2@gmail.com", LocalDate.of(1999, 8, 2));
		User user5 = new User("Miguel", "otheremail", LocalDate.of(1999, 8, 2));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5);

		list.forEach(userRepository::save);
	}

	private void dependencyInjection(){
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
