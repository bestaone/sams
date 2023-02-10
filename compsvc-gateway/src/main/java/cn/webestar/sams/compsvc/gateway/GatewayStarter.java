package cn.webestar.sams.compsvc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "cn.webestar.sams")
@SpringBootApplication
public class GatewayStarter {

	public static void main(String[] args) {
		SpringApplication.run(GatewayStarter.class, args);
	}

}
