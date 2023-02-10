package cn.webestar.sams.microsvc.org;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoDataSourceProxy
@ComponentScan(basePackages = "cn.webestar.sams")
@SpringBootApplication
public class OrgStarter {

	public static void main(String[] args) {
		SpringApplication.run(OrgStarter.class, args);
	}

}
