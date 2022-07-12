package com.tansci.service;

import com.tansci.domain.vo.ChannelAnalysisVo;
import com.tansci.domain.vo.OrderOverviewVo;
import com.tansci.domain.vo.StatisticsVo;

import java.util.List;

/**
 * @ClassName： HomeService.java
 * @ClassPath： com.tansci.service.HomeService.java
 * @Description： 首页
 * @Author： tanyp
 * @Date： 2022/4/2 8:54
 **/
public interface HomeService {

    StatisticsVo statistics();

    List<OrderOverviewVo> orderOverview();

    List<ChannelAnalysisVo> channelAnalysis();

}
