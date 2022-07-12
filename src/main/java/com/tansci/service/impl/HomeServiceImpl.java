package com.tansci.service.impl;

import com.tansci.domain.vo.ChannelAnalysisVo;
import com.tansci.domain.vo.OrderOverviewVo;
import com.tansci.domain.vo.StatisticsVo;
import com.tansci.mapper.HomeMapper;
import com.tansci.service.HomeService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName： HomeServiceImpl.java
 * @ClassPath： com.tansci.service.impl.HomeServiceImpl.java
 * @Description： 首页
 * @Author： tanyp
 * @Date： 2022/4/2 8:55
 **/
@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public StatisticsVo statistics() {
        String userId = null;
        if (!Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            userId = SecurityUserUtils.getUser().getId();
        }
        return homeMapper.statistics(userId);
    }

    @Override
    public List<OrderOverviewVo> orderOverview() {
        String userId = null;
        if (!Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            userId = SecurityUserUtils.getUser().getId();
        }
        return homeMapper.orderOverview(userId);
    }

    @Override
    public List<ChannelAnalysisVo> channelAnalysis() {
        String userId = null;
        if (!Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            userId = SecurityUserUtils.getUser().getId();
        }
        return homeMapper.channelAnalysis(userId);
    }

}
