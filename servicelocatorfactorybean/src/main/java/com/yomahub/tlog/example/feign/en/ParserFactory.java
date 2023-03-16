package com.yomahub.tlog.example.feign.en;

public interface ParserFactory {
    Parser getParser(ContentType  contentType);
}