package com.example.hrms.controllers;

import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.models.Experience;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("")
public class IndexController {

    @GetMapping({"","/"})
    ResponseEntity<?> getIndex() {
        String str = """
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
        SuccessDataResult<String> results =  new SuccessDataResult<>
                (str,"Root Page");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/error")
    ResponseEntity<?> getError() {
        String str = """
                An Error occured please go to 
                 https://human-resources-spring-boot.herokuapp.com
                """;
        SuccessDataResult<String> results =  new SuccessDataResult<>
                (str,"Root Page");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
