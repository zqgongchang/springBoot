package com.atisz.springBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atisz.springBoot.mapper") //指定mybatis的mapper接口所在的包，或者直接在Mapper类上面添加注解@Mapper
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
