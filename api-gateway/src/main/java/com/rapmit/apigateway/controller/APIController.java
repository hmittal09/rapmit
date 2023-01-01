package com.rapmit.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping("/admin")
    public String admin()
    {
        return "welcome to login";
    }

    @GetMapping("/user")
    public String user()
    {
        return "welcome to user";
    }
    @GetMapping("/dummy")
    public String dummy()
    {
        return "welcome to dummy";
    }
}
