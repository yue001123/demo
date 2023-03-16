package com.woniu.httpdemo.configration;

import lombok.Data;

import java.net.Proxy;

public class HttpConfig {


    /**
     * 读取超时时间，秒
     */
    private long readTimeout = 30;
    /**
     * 链接超时时间
     */
    private long connectTimeout = 30;

    private boolean followRedirects = false;

    /**
     * 最大的连接数
     */
    private int maxIdleConnections = 5;

    /**
     * 最大的kepAlive 时间 秒
     */
    private long keepAliveDuration = 5;

    private String userAgent = "OKHTTP";
    /**
     * 是否支持cookie
     */
    private boolean cookie = false;
    private ProxyConfig proxy;


    @Data
    public static class ProxyConfig {

        private Proxy.Type type = Proxy.Type.HTTP;
        private String host;
        private Integer port = 80;
        private String userName;
        private String password;
    }
}
