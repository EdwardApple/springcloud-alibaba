package com.flyme.service;

import com.flyme.dao.ProductDao;
import com.flyme.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    @Transactional
    public void reduceInventory(Integer pid, Integer number) {
        Product product = productDao.findById(pid).get();

        // 模拟异常
        int err = 1 / 0;

        product.setStock(product.getStock() - number);
        productDao.save(product);
    }
}
