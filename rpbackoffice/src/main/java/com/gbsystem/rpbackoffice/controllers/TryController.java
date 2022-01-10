package com.gbsystem.rpbackoffice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/try")
public class TryController{

    @GetMapping("/hello")
    public ResponseEntity<String> testAPI(){
        return new ResponseEntity<>("Hallo",HttpStatus.OK);
    }
    
}