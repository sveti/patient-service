package com.example.patientservice;

import controller.PatientController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import service.AppointmentService;
import service.PatientService;


@ComponentScan(basePackageClasses = PatientController.class)
@ComponentScan(basePackageClasses = PatientService.class)
@ComponentScan(basePackageClasses = AppointmentService.class)
@EnableEurekaClient
@SpringBootApplication
public class PatientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    WebClient.Builder getWebclientBuilder() {
        return WebClient.builder();
    }

}
