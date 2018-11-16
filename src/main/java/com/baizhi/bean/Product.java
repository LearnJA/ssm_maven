package com.baizhi.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class Product {
    private String id;
    private String name;
    private Double price;
    private String desctory;
    private MultipartFile file;
    private Integer status;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;
    private String addr;

    public Product() {
    }

    public Product(String id, String name, Double price, String desctory, MultipartFile file, Integer status, String date, String addr) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desctory = desctory;
        this.file = file;
        this.status = status;
        this.date = date;
        this.addr = addr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesctory() {
        return desctory;
    }

    public void setDesctory(String desctory) {
        this.desctory = desctory;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", desctory='" + desctory + '\'' +
                ", file=" + file +
                ", status=" + status +
                ", date='" + date + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
