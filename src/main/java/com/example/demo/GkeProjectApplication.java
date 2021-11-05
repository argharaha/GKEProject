package com.example.demo;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@Configuration
@EnableConfigurationProperties
public class GkeProjectApplication {

	
	private String ipAddress;

    public static void main(String[] args) {
        SpringApplication.run(GkeProjectApplication.class, args);
    }


    public GkeProjectApplication() {
        super();
        try {
            ipAddress = "Host[" + InetAddress.getLocalHost().getHostAddress() + "]";
        } catch (Exception e) {
            throw new RuntimeException("Could not get local address");
        }
    }

    @GetMapping("/echo")
    public String echo(@RequestParam String message, HttpServletRequest request) {
        return ipAddress + " : " + message;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
