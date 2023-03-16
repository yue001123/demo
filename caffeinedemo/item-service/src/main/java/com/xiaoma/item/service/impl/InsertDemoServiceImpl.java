package com.xiaoma.item.service.impl;

import com.xiaoma.item.mapper.ItemMapper;
import com.xiaoma.item.pojo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class InsertDemoServiceImpl {

    @Autowired
    ItemMapper itemMapper;


    @Autowired
    TransactionManager transactionManager;

    /**
     * 2万条数据，怎么快速插入到 MySQL
     * <p>
     * insert into tb_item（）values （），（）…….（）;
     * 这样插入，数据多的时候，数据库会报错
     * Packet for query is too large (6071393 > 4194304).
     *
     * @param list
     */
    @Transactional(rollbackFor = Exception.class)
    public void addItemsNew(List<Item> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        itemMapper.insertUserList(list);
    }

    /**
     * 2万条数据
     * <p>
     * 这样操作，可以避免上面的错误，
     * 但是分多次插入，无形中就增加了操作实践，很容易超时。
     *
     * @param list
     */
    @Transactional(rollbackFor = Exception.class)
    public void addItemsNew2(List<Item> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        //10000  100  100
        int c = 100;
        int b = list.size() / c;
        int d = list.size() % c;
        for (int e = c; e <= c * b; e = e + c) {
            itemMapper.insertUserList(list.subList(e - c, e));
        }
        if (d != 0) {
            itemMapper.insertUserList(list.subList(c * b, list.size()));
        }
    }

    @Autowired
    private NoAutoTransactionalUtil transactionalUtil;

    /**
     * 2万条数据
     * 多线程分批导入
     * @param list
     */
//    @Transactional(rollbackFor = Exception.class)
    public void addItemsNew3(List<Item> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int nThreads = 20;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(nThreads);
        AtomicReference<Boolean> rollback = new AtomicReference<>(false);
        int size = list.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        //20000 20 1000
        for (int i = 0; i < nThreads; i++) {
            final List<Item> itemImputList = list.subList(size / nThreads * i, size / nThreads * (i + 1));
            executorService.execute(() -> {
                TransactionStatus tranction = transactionalUtil.begin();
                try {
                    //insert 操作 小于1 就抛异常
                    if (itemMapper.insertUserList(itemImputList) < 1) {
                       log.info("手动异常");
                       throw new RuntimeException("插入数据失败");
                    }
                } catch (Exception e) {
                    //如果当前线程异常 则设置回滚标志
                    rollback.set(true);
                    log.error("插入数据失败");
                }
                //等待所有线程的事务结果
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                if (rollback.get()) {
                    transactionalUtil.rollback(tranction);
                    log.info("rollback");
                    return;
                }
                transactionalUtil.commit(tranction);
            });
        }
        executorService.shutdown();
    }


}
