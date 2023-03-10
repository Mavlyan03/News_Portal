package com.example.news_portal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
@Slf4j
public class NewsPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsPortalApplication.class, args);
        log.info("Welcome colleagues, project name is News Portal");
        System.out.println("Welcome colleagues, project name is News Portal");
    }

    @GetMapping("/")
    public String greetingPage() {
        return "welcome";
    }
}