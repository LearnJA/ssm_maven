package com.baizhi.service;

import com.baizhi.bean.Product;
import com.baizhi.dao.LuceneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class proServiceImpl implements ProService {

    @Autowired
    private LuceneDAO luceneDAO;

    @Override
    public void addPro(Product product) {
        luceneDAO.addLucene(product);
    }

    @Override
    public List<Product> findPros(String keyStr) {
        return luceneDAO.searchLucene(keyStr);
    }
}
