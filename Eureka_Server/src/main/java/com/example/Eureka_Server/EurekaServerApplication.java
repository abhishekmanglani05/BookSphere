package com.example.Eureka_Server;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class EurekaServerApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(EurekaServerApplication.class, args);
//	}
//
//}



//package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
