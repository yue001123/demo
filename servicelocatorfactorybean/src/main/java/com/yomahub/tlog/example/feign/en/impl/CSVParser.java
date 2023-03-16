package com.yomahub.tlog.example.feign.en.impl;


import com.yomahub.tlog.example.feign.en.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component("CSV")
@Slf4j
public class CSVParser implements Parser {
    @Override
    public List parse(Reader r) {
        log.info("用CSVParser解析。。。");
        return null;
    }
}