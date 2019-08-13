package com.java.commons.service;

import com.java.commons.entity.GoodSort;

import java.util.List;

/**
 * 商品类型公共接口
 */
public interface GoodSortService {

    // 查询所有商品类型
    List<GoodSort> findAllGoodSort() throws Exception;
}
