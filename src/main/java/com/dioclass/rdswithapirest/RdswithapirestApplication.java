package com.dioclass.rdswithapirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;

@SpringBootApplication
public class RdswithapirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdswithapirestApplication.class, args);
	}


}
