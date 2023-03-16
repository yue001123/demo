package com.yomahub.tlog.example.feign.en.impl;

import com.yomahub.tlog.example.feign.en.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component("JSON")
@Slf4j
public class JSONParser implements Parser {
    @Override
    public List parse(Reader r) {
        log.info("用JSONParser解析。。。");
        return null;
    }
}