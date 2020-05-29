package myboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication(scanBasePackages = {"myboot"})
//EnableEurekaServer
public class ServiceApplication {
    public static  void main(String[] args){
        SpringApplication.run(ServiceApplication.class,args);
    }
}
