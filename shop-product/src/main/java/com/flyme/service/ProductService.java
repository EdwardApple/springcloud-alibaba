package com.flyme.service;

import com.flyme.domain.Product;

public interface ProductService {

    Product findById(Integer pid);

    void reduceInventory(Integer pid, Integer number);

}
