package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Welcome {
    @CrossOrigin(origins = "*", allowedHeaders="*")
    @GetMapping("/welcome")
    public Object welcome()
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to spring boot!");
        return response;
    }
}
