package cn.webestar.sams.apisvc.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OmsStarter {

	public static void main(String[] args) {
		SpringApplication.run(OmsStarter.class, args);
	}

}
