package com.flyme.controller;


import com.alibaba.fastjson.JSON;
import com.flyme.domain.Product;
import com.flyme.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{pid}")
    public Product getProduct(@PathVariable("pid") Integer pid) {
        log.info("正在查询{}商品信息的查询", pid);
        Product product = productService.findById(pid);
        log.info("商品信息查询成功：{}", JSON.toJSONString(product));

        return product;
    }

    @GetMapping("/product/reduceInventory")
    public void reduceInventory(Integer pid, Integer number) {
        productService.reduceInventory(pid, number);
    }

}
