package com.xiaoma.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoma.item.mapper.ItemMapper;
import com.xiaoma.item.pojo.Item;
import com.xiaoma.item.service.IItemService;
import com.xiaoma.item.service.IItemStockService;
import com.xiaoma.item.pojo.ItemStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService extends ServiceImpl<ItemMapper, Item> implements IItemService {
    @Autowired
    private IItemStockService stockService;
    @Override
    @Transactional
    public void saveItem(Item item) {
        // 新增商品
        save(item);
        // 新增库存
        ItemStock stock = new ItemStock();
        stock.setId(item.getId());
        stock.setStock(item.getStock());
        stockService.save(stock);
    }
}
