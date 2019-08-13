package com.java.commons.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品详情实体对象
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class GoodDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //分类编号
    private Integer sortId;
    //商品名称
    private String name;
    //产地
    private String address;
    //价格
    private Double price;
    // 生产日期
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    //生产数量
    private Integer remaining;
    //商品类型实体对象
    private GoodSort goodSort;

    public GoodSort getGoodSort() {
        return goodSort;
    }

    public void setGoodSort(GoodSort goodSort) {
        this.goodSort = goodSort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "GoodDetail{" +
                "id=" + id +
                ", sortId=" + sortId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", remaining=" + remaining +
                '}';
    }
}
