package com.guitar;

import com.guitar.mongo.App;
import com.guitar.postgres.PostgresApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class GuitarBackendApplication {

	static App app;
	static PostgresApp postgresApp;
/*
	@Autowired
	public GuitarBackendApplication(App app) {
		this.app = app;
	}
*/

	@Autowired
	public GuitarBackendApplication(PostgresApp postgresApp,App app) {
		this.postgresApp = postgresApp;
		this.app=app;
	}


	public static void main(String[] args) {

		SpringApplication.run(GuitarBackendApplication.class, args);

		postgresApp.go();
		System.out.println("doszlo main");
		app.go();
	}
}
