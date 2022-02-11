package com.example.hrms.controllers;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController()
@RequestMapping("")
public class IndexController {

    @GetMapping({"","/"})
    ResponseEntity<?> getIndex() {
        String str = "Welcome to HRMS Spring application\n" +
                     "Valid Commands\n" +
                     "/positions\n" +
                     "/candidates\n" +
                     "/cities\n" +
                     "/cv\n" +
                     "/experience\n" +
                     "/job_advertisement\n" +
                     "/task_masters\n";
        SuccessDataResult<String> results =  new SuccessDataResult<>
                (str,"Root Page");
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @GetMapping("/error")
    ResponseEntity<?> getError() {
        String str = "An Error occured please go to\n" +
                     "https://human-resources-spring-boot.herokuapp.com\n";
        SuccessDataResult<String> results =  new SuccessDataResult<>
                (str,"Root Page");
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
