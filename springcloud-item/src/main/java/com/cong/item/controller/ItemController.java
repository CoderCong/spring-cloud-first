package com.cong.item.controller;

import com.cong.item.config.JdbcConfigBean;
import com.cong.item.pojo.Item;
import com.cong.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 759329342@qq.com
 * @since created  on  2017/11/7.
 */
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private JdbcConfigBean jdbcConfigBean;

    /**
     * 根据item id查询item
     * @param id
     * @return
     */
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id") Long id){
        return this.itemService.queryItemById(id);
    }

    @GetMapping(value = "test")
    public String test(){
        return this.jdbcConfigBean.toString();
    }
}
