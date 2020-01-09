package com.woc.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(tags = "A. Application Monitoring")
public class HealthCheckApi {

    @GetMapping("/health/check")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<String>("fine", HttpStatus.OK);
    }

}
