package com.cong.order.service;

import com.cong.order.OrderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 759329342@qq.com
 * @since created  on  2017/11/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(OrderApplication.class)
public class ItemServiceTest {
    @Autowired
    public LoadBalancerClient loadBalancerClient;

    @Test
    public void test(){
        String serviceid = "springcloud-item";
        for (int i = 0; i < 100 ; i++){
            ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceid);
            System.out.println("第"+(i+1)+"次：" + serviceInstance.getHost() + ": " + serviceInstance.getPort());
        }
    }
}
