package com.dirk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;



@EnableFeignClients
@EnableEurekaClient
@SpringCloudApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class BaseServiceApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(BaseServiceApplication.class);
        //DefaultApplicationContextInitializer initializer = new DefaultApplicationContextInitializer();
        //application.addInitializers(initializer);
        application.run(args);
    }
}
