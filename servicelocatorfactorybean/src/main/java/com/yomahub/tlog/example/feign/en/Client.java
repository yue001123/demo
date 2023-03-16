package com.yomahub.tlog.example.feign.en;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.util.List;

@Service
public class Client {
//    private Parser csvParser, jsonParser;
//
//    @Autowired
//    public Client(Parser csvParser, Parser jsonParser) {
//        this.csvParser = csvParser;
//        this.jsonParser = jsonParser;
//    }

    @Autowired
    private ParserFactory parserFactory;

    public List getAll(ContentType contentType) {

        Reader reader = null;
//        switch (contentType) {
//            case CSV:
//                return csvParser.parse(reader);
//            case JSON:
//                return jsonParser.parse(reader);
//        }
        List parse = parserFactory.getParser(contentType).parse(reader);

        return parse;

    }
}


//        Parser parser = parserFactory.getParser(contentType);
//        List parse = parser.parse(reader);


//    private ParserFactory parserFactory;
//
//    @Autowired
//    public Client(ParserFactory parserFactory) {
//        this.parserFactory = parserFactory;
//    }