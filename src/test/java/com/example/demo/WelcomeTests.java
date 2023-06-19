package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WelcomeTests {
    @Test
    void welcomeTests(){
        // Create expected response to test welcome endpoint
        Map<String, String> expected_response = new HashMap<>();
        expected_response.put("message", "Welcome to spring boot!");

        // Get actual response from welcome endpoint
        Welcome welcome_obj = new Welcome();
        Map<String, String> actual_response = new HashMap<>();
        actual_response = (Map<String, String>) welcome_obj.welcome();

        // Compare actual and expected responses
        assertEquals(expected_response,actual_response);
    }
}
