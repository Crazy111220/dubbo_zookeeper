package com.java.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.commons.entity.GoodDetail;
import com.java.commons.service.GoodDetailService;
import com.java.provider.mapper.GoodDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service(version = "1.0.0")
public class GoodDetailServiceImpl implements GoodDetailService {

    //依赖引入Mapper代理对象
    @Autowired
    private GoodDetailMapper goodDetailMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    // 查询所有的商品详情信息
    @Override
    public Map<String, Object> findGoodDetailByParams(Integer page, Integer limit, GoodDetail goodDetail) throws Exception {
        System.out.println("GoodDetail-provide2被执行了。。。");
        // 得到redis从缓存中取String数据的对象
        ValueOperations vop = redisTemplate.opsForValue();
        // 得到redis从缓存中取Set数据的对象
        SetOperations sop = redisTemplate.opsForSet();
        Map<String, Object> map = (Map<String, Object>)vop.get("pageGoodDetailMap" + page+ ',' +limit );
        if(map == null){
            map = new HashMap<String,Object>();
            PageHelper.startPage(page,limit);
            PageInfo<GoodDetail> pageInfo = new PageInfo<GoodDetail>(goodDetailMapper.selectGoodDetailByParams(goodDetail));
            map.put("data",pageInfo.getList());
            map.put("count",pageInfo.getTotal());
            vop.set("pageGoodDetailMap," + page+ ',' +limit,map);
            sop.add("pageGoodDetailMapKeys","pageGoodDetailMap," + page+ ',' +limit);
            Set<String> keySet = sop.members("pageGoodDetailMapKeys");
            for (String redisKey:keySet) {
                System.out.println(redisKey);
            }
            System.out.println("此时走的是GoodDetail-provider2数据库查询");
        }else {
            System.out.println("此时走的是GoodDetail-provider2的redis缓存");
        }
        return map;
    }

    // 根据id删除单个数据
    @Override
    public String removeGoodDetailById(Integer id) throws Exception {
        if(goodDetailMapper.deleteGoodDetailById(id) > 0){
            SetOperations sop = redisTemplate.opsForSet();
            Set<String> keySet = sop.members("pageGoodDetailMapKeys");
            redisTemplate.delete(keySet);
            //删除key的set集合
            redisTemplate.delete("pageGoodDetailMapKeys");
            return "success";
        }else {
            return "fail";
        }
    }

    // 添加商品详情数据
    @Override
    public String saveGoodDetail(GoodDetail goodDetail) throws Exception {
        if(goodDetailMapper.insertGoodDetail(goodDetail) > 0){
            SetOperations sop = redisTemplate.opsForSet();
            Set<String> keySet = sop.members("pageGoodDetailMapKeys");
            redisTemplate.delete(keySet);
            //删除key的set集合
            redisTemplate.delete("pageGoodDetailMapKeys");
            return "success";
        }else {
            return "fail";
        }
    }
}
