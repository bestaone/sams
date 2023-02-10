package cn.webestar.sams.apisvc.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "cn.webestar.sams")
@EnableFeignClients(basePackages = {"cn.webestar.sams"})
@EnableDiscoveryClient
@SpringBootApplication
public class MallStarter {

	public static void main(String[] args) {
		SpringApplication.run(MallStarter.class, args);
	}

}
