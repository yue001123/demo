package com.wm.file.config;


import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @Author: woniu
 * @Date: 2023-2-19 11:14
 * @Description:
 * Spring Boot 实现日志链路追踪，无需引入组件，让日志定位更方便！
 */
public final class ThreadMdcUtil {
    private static final String TRACE_ID = "TRACE_ID";

    // 获取唯一性标识
    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    public static void setTraceIdIfAbsent() {
        if (MDC.get(TRACE_ID) == null) {
            MDC.put(TRACE_ID, generateTraceId());
        }
    }

    /**
     * 用于父线程向线程池中提交任务时，将自身MDC中的数据复制给子线程
     *
     * @param callable
     * @param context
     * @param <T>
     * @return
     */
    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    /**
     * 用于父线程向线程池中提交任务时，将自身MDC中的数据复制给子线程
     *
     * @param runnable
     * @param context
     * @return
     */
    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}