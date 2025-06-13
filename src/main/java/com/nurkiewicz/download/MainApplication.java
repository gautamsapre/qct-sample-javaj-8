package com.nurkiewicz.download;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class MainApplication {

        public static void main(String[] args) {
                System.out.println("hello world");
                Integer temp = new Integer("1234");
                SpringApplication.run(MainApplication.class, args);
        }
}
