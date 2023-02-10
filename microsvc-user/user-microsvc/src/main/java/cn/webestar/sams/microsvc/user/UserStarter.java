package cn.webestar.sams.microsvc.user;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoDataSourceProxy
@ComponentScan(basePackages = "cn.webestar.sams")
@SpringBootApplication
public class UserStarter {

	public static void main(String[] args) {
		SpringApplication.run(UserStarter.class, args);
	}

}
