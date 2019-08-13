package com.java.commons.service;

import com.java.commons.entity.GoodDetail;

import java.util.Map;

/**
 * 商品详情的公共业务层接口
 */
public interface GoodDetailService {
    // 根据条件分页查询
    Map<String,Object> findGoodDetailByParams(Integer page, Integer limit, GoodDetail goodDetail) throws Exception;

    // 根据id删除单个数据
    String removeGoodDetailById(Integer id) throws Exception;

    // 添加商品详情数据
    String saveGoodDetail(GoodDetail goodDetail) throws Exception;
}
