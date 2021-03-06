package com.java.consumer.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *   设置访问首页index.jsp
 *   @Configuration 实例化此类，读取此类中的配置
 */
@Configuration
public class ToGoodDetailView extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {//
        System.out.println("执行了配置访问登录页面的工具类。。");
        //   /为访问的路径    /WEB-INF/jsp/index.jsp为文件的实际路径
        registry.addViewController( "/" ).setViewName( "/model/toShowGoodDetail" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }
}
