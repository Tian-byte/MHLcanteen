package com.tianedu.mhl.domain;

/**
 * @author tian
 *
 * 该类(javaben) 和 menu 表对应
 *
 */
public class Menu {
    private Integer id;
    private String name;
    private String type;
    private Double price;

    public Menu() {  // 无参构造器
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
