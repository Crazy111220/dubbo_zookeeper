package com.java.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消费者的启动类
 */
@SpringBootApplication(scanBasePackages = "com.java.consumer.*")
public class ConsumerStart {

    public static void main(String[] args){
        SpringApplication.run(ConsumerStart.class);
    }
}
