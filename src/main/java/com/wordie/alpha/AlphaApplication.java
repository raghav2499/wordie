package com.wordie.alpha;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableConfigurationProperties
//@EnableJpaRepositories({"com.wordie.alpha.*"})
//@ComponentScan({"com.wordie.alpha.*"})
//@EntityScan({"com.wordie.alpha.*"})
//@EnableAutoConfiguration()
public class AlphaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AlphaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
