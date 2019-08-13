package com.java.provider.mapper;

import com.java.commons.entity.GoodSort;

import java.util.List;

/**
 * 商品类型Mapper代理对象
 */
public interface GoodSortMapper {

    //查询所有商品类型
    List<GoodSort> selectAllGoodSort() throws Exception;

    //根据id查询单个商品类型
    GoodSort selectGoodSortById(Integer id) throws Exception;
}
