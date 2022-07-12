package com.tansci.controller;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.vo.ChannelAnalysisVo;
import com.tansci.domain.vo.OrderOverviewVo;
import com.tansci.domain.vo.StatisticsVo;
import com.tansci.service.HomeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName： HomeController.java
 * @ClassPath： com.tansci.controller.HomeController.java
 * @Description： 首页
 * @Author： tanyp
 * @Date： 2022/4/2 8:55
 **/
@Slf4j
@RestController
@RequestMapping("/home")
@Api(value = "home", tags = "首页")
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * @MonthName： statistics
     * @Description： 平台数据统计
     * @Author： tanyp
     * @Date： 2022/4/2 8:58
     * @Param： []
     * @return： com.tansci.common.Wrapper<java.lang.Object>
     **/
    @GetMapping("statistics")
    public Wrapper<StatisticsVo> statistics() {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, homeService.statistics());
    }

    /**
     * @MonthName： orderOverview
     * @Description： 交易数据总览
     * @Author： tanyp
     * @Date： 2022/4/2 8:58
     * @Param： []
     * @return： com.tansci.common.Wrapper<java.lang.Object>
     **/
    @GetMapping("orderOverview")
    public Wrapper<List<OrderOverviewVo>> orderOverview() {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, homeService.orderOverview());
    }

    /**
     * @MonthName： channelAnalysis
     * @Description： 支付渠道总览
     * @Author： tanyp
     * @Date： 2022/4/2 8:58
     * @Param： []
     * @return： com.tansci.common.Wrapper<java.lang.Object>
     **/
    @GetMapping("channelAnalysis")
    public Wrapper<List<ChannelAnalysisVo>> channelAnalysis() {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, homeService.channelAnalysis());
    }

}
