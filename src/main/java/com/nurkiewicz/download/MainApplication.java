package com.nurkiewicz.download;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class MainApplication {

        public static void main(String[] args) {
                Integer temp = new Integer("1234");
                System.out.println("Hello World");
                SpringApplication.run(MainApplication.class, args);
        }
}
