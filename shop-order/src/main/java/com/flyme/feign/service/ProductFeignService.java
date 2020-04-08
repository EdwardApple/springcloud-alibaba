package com.flyme.feign.service;

import com.flyme.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-product") // 被调用的服务id
public interface ProductFeignService {

    @GetMapping("/product/{pid}") // 被调用的服务中方法的 uri
    Product getProduct(@PathVariable("pid") Integer pid);

    @GetMapping("/product/reduceInventory")
    void reduceInventory(@RequestParam("pid") Integer pid,
                         @RequestParam("number") Integer number);

}
