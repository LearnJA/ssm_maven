package com.baizhi.controller;

import com.baizhi.bean.Product;
import com.baizhi.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pro")
public class ProductController {

    @Autowired
    private ProService proService;

    @RequestMapping("/add")
    public String addPro(Product product){
        System.out.println(product);
        return "index";
    }

    @RequestMapping("/find")
    public @ResponseBody List<Product> findLucene(String key){
        List<Product> pros=proService.findPros(key);
        return pros;
    }
}
