package com.example.hrms.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("")
public class IndexController {

    @GetMapping({"","/"})
    String getIndex() {
        return """
                Welcome to HRMS Spring application
                Valid Commands
                /positions
                /candidates
                /cities
                /cv
                /experience
                /job_advertisement
                /task_masters
                 
                """;
    }

    @GetMapping("/error")
    String getError() {
        return """
                An Error occured please go to 
                 https://human-resources-spring-boot.herokuapp.com
                """;
    }
}
