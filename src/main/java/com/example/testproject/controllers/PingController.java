package com.example.testproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

  @GetMapping("/api/person-management/ping")
  public String getPong(){
    return "person-management:pong";
  }

}
