package com.flyme.service;

import com.alibaba.fastjson.JSON;
import com.flyme.dao.OrderDao;
import com.flyme.domain.Order;

import com.flyme.domain.Product;
import com.flyme.feign.service.ProductFeignService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductFeignService productFeignService;

    @Override
    @GlobalTransactional // 开启全局事务控制
    public Order createOrder(Integer pid) {
        Product product = productFeignService.getProduct(pid);
        log.info("查询到商品pid{}的信息{}", pid, JSON.toJSONString(product));

        // 创建订单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setNumber(1);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        orderDao.save(order);

        // 扣减库存
        productFeignService.reduceInventory(pid, order.getNumber());

        return order;
    }
}
