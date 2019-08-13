package com.java.provider.test;

import com.java.commons.entity.GoodDetail;
import com.java.commons.service.GoodDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商品详情业务层测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoodDetailServiceTest {

    @Resource
    private GoodDetailService goodDetailService;

    @Test
    public void test01(){
        GoodDetail paramsGoodDetail = new GoodDetail();
        try {
            Map<String,Object> map = goodDetailService.findGoodDetailByParams(1, 5, paramsGoodDetail);
            System.out.println("总的数据条数：" + map.get("count"));
            List<GoodDetail> goodDetails = (List<GoodDetail>) map.get("data");
            for(GoodDetail goodDetail : goodDetails){
                System.out.println(goodDetail.getName()+"--"+goodDetail.getPrice());
                System.out.println(goodDetail.getGoodSort());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
