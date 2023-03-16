package org.example.redislimater.controller;


import org.example.redislimater.ApiCall;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/cricle")
@Validated
public class TestController {

    @ApiCall(time = 1,limitCount = 100,timeUnit = TimeUnit.SECONDS)
    @GetMapping("/queryAllCricles")
    public String queryAllCricles(String req)
    {
        return null;
    }
}
