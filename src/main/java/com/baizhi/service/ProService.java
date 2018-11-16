package com.baizhi.service;

import com.baizhi.bean.Product;

import java.util.List;

public interface ProService {
    /*添加产品*/
    void addPro(Product product);
    /*检索商品*/
    List<Product> findPros(String keyStr);
}
