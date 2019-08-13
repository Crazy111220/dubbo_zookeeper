package com.java.commons.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 商品类型实体对象
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class GoodSort implements Serializable {

    // 主键
    private Integer id;
    // 分类名称
    private String name;

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

    @Override
    public String toString() {
        return "GoodSort{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
