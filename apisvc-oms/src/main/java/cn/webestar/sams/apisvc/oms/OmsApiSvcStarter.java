package cn.webestar.sams.apisvc.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "cn.webestar.sams")
@EnableFeignClients(basePackages = {"cn.webestar.sams"})
@EnableDiscoveryClient
@SpringBootApplication
public class OmsApiSvcStarter {

	public static void main(String[] args) {
		SpringApplication.run(OmsApiSvcStarter.class, args);
	}

}
