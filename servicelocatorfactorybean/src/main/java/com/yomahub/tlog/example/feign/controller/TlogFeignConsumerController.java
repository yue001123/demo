package com.yomahub.tlog.example.feign.controller;

import com.yomahub.tlog.example.feign.en.Client;
import com.yomahub.tlog.example.feign.en.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TlogFeignConsumerController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Client client;

    @RequestMapping("/hi")
    public String sayHello(@RequestParam String name){
        log.info("invoke consumer controller method sayHello");
        List all = client.getAll(ContentType.CSV);
        return "ok";
    }


}
