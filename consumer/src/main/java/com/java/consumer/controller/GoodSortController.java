package com.java.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.java.commons.entity.GoodSort;
import com.java.commons.service.GoodSortService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品类型控制器
 */
@Controller
@RequestMapping("/goodSort")
public class GoodSortController {

    @Reference(version = "1.0.0")
    private GoodSortService goodSortService;

    // 加载所有的商品类型信息
    @RequestMapping("/loadAllGoodSort")
    public @ResponseBody List<GoodSort> loadAllGoodSort(){
        try {
            return goodSortService.findAllGoodSort();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
