package org.zdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class StartWebApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StartWebApplication.class, args);
	}
}