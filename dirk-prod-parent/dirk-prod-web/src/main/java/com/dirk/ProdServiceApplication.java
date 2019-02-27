package com.dirk;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringCloudApplication
//@SpringBootApplication(scanBasePackages = {"com.dirk","com.cloud.frame.core.base.exception"}) //加com.cloud.frame.core.base.exception是为了扫描到统一异常处理类
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ProdServiceApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(ProdServiceApplication.class);
        //DefaultApplicationContextInitializer initializer = new DefaultApplicationContextInitializer();
        //application.addInitializers(initializer);
        application.run(args);
    }
}
