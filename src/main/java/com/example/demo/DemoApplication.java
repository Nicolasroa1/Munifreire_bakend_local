package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.config.AppConfig;

import io.vertx.core.Vertx;

@Import(AppConfig.class)
@EnableJpaRepositories(basePackages = "com.example.demo.*")
@ComponentScan(basePackages = { "com.example.demo.*" })
@EntityScan(basePackages = "com.example.demo.model")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		SpringApplication.run(DemoApplication.class, args);
	}

}
