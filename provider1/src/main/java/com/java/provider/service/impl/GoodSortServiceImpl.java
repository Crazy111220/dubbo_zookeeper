package com.java.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.java.commons.entity.GoodSort;
import com.java.commons.service.GoodSortService;
import com.java.provider.mapper.GoodSortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

/**
 * 商品类型业务成实现类
 */
@Service(version = "1.0.0")
public class GoodSortServiceImpl implements GoodSortService {

    @Autowired
    private GoodSortMapper goodSortMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    // 查询所有商品类型信息
    @Override
    public List<GoodSort> findAllGoodSort() throws Exception {
        System.out.println("GoodSort-provide1被执行了。。。");
        ValueOperations vop = redisTemplate.opsForValue();
        List<GoodSort> googSorts = (List<GoodSort>) vop.get("allGoodSort");
        if(googSorts == null){
            googSorts = goodSortMapper.selectAllGoodSort();
            vop.set("allGoodSort",googSorts);
            System.out.println("执行了数据库AllGoodSort-provide1");
        }else {
            System.out.println("执行了数据库AllGoodSort-provide1缓存");
        }
        return googSorts;
    }
}
