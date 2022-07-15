package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.LoginLog;
import com.tansci.mapper.LoginLogMapper;
import com.tansci.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName： LoginLogServiceImpl.java
 * @ClassPath： com.tansci.service.impl.LoginLogServiceImpl.java
 * @Description： 登录日志
 * @Author： tanyp
 * @Date： 2022/6/1 15:12
 **/
@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public IPage<LoginLog> page(Page page, LoginLog loginLog) {
        return this.baseMapper.selectPage(page,
                Wrappers.<LoginLog>lambdaQuery()
                        .like(Objects.nonNull(loginLog.getUserName()), LoginLog::getUserName, loginLog.getUserName())
                        .orderByDesc(LoginLog::getLoginTime)
        );
    }

}
