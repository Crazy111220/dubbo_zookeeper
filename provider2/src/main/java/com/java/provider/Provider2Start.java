package com.java.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 提供者2的启动类
 */
@SpringBootApplication(scanBasePackages = "com.java.provider.*")
@MapperScan("com.java.provider.mapper")
@EnableCaching  // 开启缓存
public class Provider2Start {

    public static void main(String[] args){
        SpringApplication.run(Provider2Start.class);
    }

}
