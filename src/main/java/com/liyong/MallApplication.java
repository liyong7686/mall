package com.liyong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@ComponentScan
@ImportResource({"classpath*:applicationContext.xml"})
public class MallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}

	/*
    @RequestMapping("/")
    String index() {
    	System.out.println("------ index --------start --------------");
        return "index";
    }*/
}
