package com.tansci.mapper;

import com.tansci.domain.vo.ChannelAnalysisVo;
import com.tansci.domain.vo.OrderOverviewVo;
import com.tansci.domain.vo.StatisticsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName： HomeMapper.java
 * @ClassPath： com.tansci.mapper.HomeMapper.java
 * @Description： 首页
 * @Author： tanyp
 * @Date： 2022/4/2 8:54
 **/
@Mapper
public interface HomeMapper {

    StatisticsVo statistics(String userId);

    List<OrderOverviewVo> orderOverview(String userId);

    List<ChannelAnalysisVo> channelAnalysis(String userId);

}
