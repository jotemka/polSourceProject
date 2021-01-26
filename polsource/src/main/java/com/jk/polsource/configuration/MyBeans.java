package com.jk.polsource.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeans {

    @Bean
    ObjectMapper createMapper(){
        return new ObjectMapper();
    }
}
