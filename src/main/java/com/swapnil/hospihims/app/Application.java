package com.swapnil.hospihims.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
@EnableTransactionManagement
@ImportResource({"classpath:applicationContext.xml"})
@ComponentScan("com.swapnil.hospihims")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
    }
}
