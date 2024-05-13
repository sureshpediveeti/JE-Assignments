package com.javaexpress.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.javaexpress.accounts.dto.AccountsContactDetailsDto;

// Need to load configuration props form git along with @RefreshScope
@EnableConfigurationProperties(AccountsContactDetailsDto.class)
@EnableDiscoveryClient
@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
