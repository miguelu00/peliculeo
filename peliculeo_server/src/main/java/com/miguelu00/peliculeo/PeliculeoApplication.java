package com.miguelu00.peliculeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages= {"controladores", "repositorios", "autenticacion"})
@EntityScan(basePackages= {"modelos"})
@EnableJpaRepositories(basePackages= {"repositorios"})

@SpringBootApplication
@EnableScheduling
public class PeliculeoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PeliculeoApplication.class, args);
    }
}