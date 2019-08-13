package com.java.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转的控制器
 */
@Controller
@RequestMapping("/model")
public class ModelController {

    //去到商品详情显示页面
    @RequestMapping("/toShowGoodDetail")
    public String toShowGoodDetail(){
        System.out.println("toShowGoodDetail");
        return "/static/showGoodDetatil.html";
    }
}
