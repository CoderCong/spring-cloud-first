package com.cong.order.feign;

import com.cong.order.pojo.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 759329342@qq.com
 * @since created  on  2017/11/20.
 */

@FeignClient(value = "springcloud-item")
public interface ItemFeignClient {

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public Item queryItemById(@PathVariable("id") Long id);

}
