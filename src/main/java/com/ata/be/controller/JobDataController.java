package com.ata.be.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobDataController {
    @GetMapping("/jobData")
    public String searchJobData() {
        return "Success";
    }
}
