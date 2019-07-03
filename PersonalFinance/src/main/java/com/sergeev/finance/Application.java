package com.sergeev.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    @Autowired
    public DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



}
