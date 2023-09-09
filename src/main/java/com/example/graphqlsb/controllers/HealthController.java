package com.example.graphqlsb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/status")
    public ResponseEntity<String> checkStatus() {
        return ResponseEntity.ok().body("ok");
    }
}
