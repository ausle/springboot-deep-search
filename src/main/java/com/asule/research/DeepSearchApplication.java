package com.asule.research;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.asule.research.dao")
public class DeepSearchApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DeepSearchApplication.class);
        springApplication.run(args);
    }

}
