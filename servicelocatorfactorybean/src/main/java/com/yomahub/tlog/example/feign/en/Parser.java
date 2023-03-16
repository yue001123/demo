package com.yomahub.tlog.example.feign.en;

import java.io.Reader;
import java.util.List;

/**
 * Spring项目中用了服务定位模式，
 * 消除代码中的if else，经理对我刮目相看！
 *
 * 比如根据文件的类型使用 CSV解析器或者JSON解析器，
 * 在调用的客户端一般都是用if else去做判断，
 * 比如类型等于JSON，我就用JSON解析器，
 * 那如果新加一个类型的解析器，是不是调用的客户端还要修改呢？
 */
public interface Parser {
    List parse(Reader r);
}
