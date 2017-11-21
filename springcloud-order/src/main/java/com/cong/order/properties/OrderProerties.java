package com.cong.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 759329342@qq.com
 * @since created  on  2017/11/7.
 */
@Component
@ConfigurationProperties(prefix = "com.cong")
public class OrderProerties {

    private ItemProperties item = new ItemProperties();

    public ItemProperties getItem() {
        return item;
    }

    public void setItem(ItemProperties item) {
        this.item = item;
    }
}
