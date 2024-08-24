package com.cca.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyController {

    @GetMapping("/trace")
    public String traceEndpoint() {
        // This request will be automatically traced
        log.info("Traced!");
        return "Traced!";
    }
}