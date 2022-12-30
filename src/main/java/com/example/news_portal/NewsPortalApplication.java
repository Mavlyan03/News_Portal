package com.example.news_portal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@Slf4j
public class NewsPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsPortalApplication.class, args);
        log.info("Welcome collegues, project name is News Portal");
        System.out.println("Welcome colleagues, project name is News Portal");
    }

}

//@Controller
//@SpringBootApplication
//@Slf4j
//public class PeaksoftlmsB6Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(PeaksoftlmsB6Application.class, args);
//		log.info("Welcome colleagues, project name is Peaksoft-LMS!");
//		System.out.println("Welcome colleagues, project name is Peaksoft-LMS!");
//	}
//
//	@GetMapping("/")
//	public String greetingPage(){
//		return "welcome";
//	}
//}