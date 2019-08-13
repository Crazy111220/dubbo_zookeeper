package com.java.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.java.commons.entity.GoodDetail;
import com.java.commons.service.GoodDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 商品详情的控制器
 */
@Controller
@RequestMapping("/GoodDetail")
public class GoodDetailController {

    //依赖业务层对象
    @Reference(version = "1.0.0")
    private GoodDetailService goodDetailService;

    //根据条件分页查询商品详情
    @RequestMapping("/loadPageGoodDetailByParams")
    public @ResponseBody Map<String,Object> loadPageGoodDetailByParams(Integer page,Integer limit,GoodDetail goodDetail){
        try {
            Map<String,Object> map = goodDetailService.findGoodDetailByParams(page,limit,goodDetail);
            // 响应正确的状态
            map.put("code",0);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 根据id删除商品详情数据
    @RequestMapping("/delGoodDetailById")
    public @ResponseBody String delGoodDetailById(Integer id){
        try {
            return goodDetailService.removeGoodDetailById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // 添加商品详情数据
    @RequestMapping("/saveGoodDetail")
    public @ResponseBody String saveGoodDetail(GoodDetail goodDetail){
        try {
            return goodDetailService.saveGoodDetail(goodDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
