package com.cong.order.service;

import com.cong.order.feign.ItemFeignClient;
import com.cong.order.pojo.Item;
import com.cong.order.properties.OrderProerties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 759329342@qq.com
 * @since created  on  2017/11/7.
 */
@Service
public class ItemService {
    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private OrderProerties orderProerties;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ItemFeignClient itemFeignClient;


    public Item queryItemById(Long id) {
        return this.restTemplate.getForObject(this.orderProerties.getItem().getUrl() + id, Item.class);
    }

    public Item queryItemByIdFromEureka(Long id){
        String serviceid = "springcloud-item";
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceid);
        if(instances == null){
            return null;
        }
        ServiceInstance instance = instances.get(0);
        String url = instance.getHost() + ":" + instance.getPort();
        System.out.println(url);
        return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
    }

    // 进行容错处理
    @HystrixCommand(fallbackMethod = "queryItemByIdFallBackMethod")
    public Item queryItemByIdFromEurekaWithLoadBalance(Long id){
        String serviceid = "springcloud-item";
        return this.restTemplate.getForObject("http://" + serviceid + "/item/" + id, Item.class);
    }

    // 进行容错处理
    @HystrixCommand(fallbackMethod = "queryItemByIdFallBackMethod")
    public Item queryItemByIdFromFeign(Long id){
        return this.itemFeignClient.queryItemById(id);
    }

    // 请求失败执行的方法
    public Item queryItemByIdFallBackMethod(Long id){
        return new Item(id, "查询商品信息出错!", null, null, null);
    }


}
