package com.niubaide.im;

import com.niubaide.im.util.IdWorker;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.niubaide.im")
@EnableEncryptableProperties
@MapperScan("com.niubaide.im.mapper")
public class ImApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        // 如果是分布式节点设置为其他值，确保每个节点的id都不一样
        return new IdWorker(0,0);
    }
}
