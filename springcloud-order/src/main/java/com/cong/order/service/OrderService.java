package com.cong.order.service;

import com.cong.order.pojo.Item;
import com.cong.order.pojo.Order;
import com.cong.order.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 759329342@qq.com
 * @since created  on  2017/11/7.
 */
@Service
public class OrderService {

    private static final Map<String, Order> MAP = new HashMap<>();

    static {
        /**
         * 测试数据
         */
        Order order = new Order();
        order.setOrderId("8986461");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        // 此处并没有商品的数据，需要调用商品微服务获取
        Item item = new Item();
        item.setId(1L);
        orderDetailList.add(new OrderDetail(order.getOrderId(), item));
        // 构造第二个商品数据
        item = new Item();
        item.setId(2L);
        orderDetailList.add(new OrderDetail(order.getOrderId(), item));

        order.setOrderDetails(orderDetailList);

        MAP.put(order.getOrderId(), order);

    }

    @Autowired
    private ItemService itemService;


    public Order queryOrderById(String orderId){
        Order order = MAP.get(orderId);
        if(null == order){
            return null;
        }
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail: orderDetails) {
            Item item = this.itemService.queryItemByIdFromFeign(orderDetail.getItem().getId());
            if(null == item){
                continue;
            }
            orderDetail.setItem(item);
        }
        return order;
    }
}
