package com.java.provider.mapper;

import com.java.commons.entity.GoodDetail;

import java.util.List;

/**
 * 商品详情Mapper代理对象
 */
public interface GoodDetailMapper {

    // 根据条件查询商品信息
    List<GoodDetail> selectGoodDetailByParams(GoodDetail goodDetail) throws Exception;

    // 根据id删除单个数据
    Integer deleteGoodDetailById(Integer id) throws Exception;

    // 添加商品详情数据
    Integer insertGoodDetail(GoodDetail goodDetail) throws Exception;
}
