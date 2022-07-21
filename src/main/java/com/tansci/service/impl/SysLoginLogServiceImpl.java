package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysLoginLog;
import com.tansci.mapper.SysLoginLogMapper;
import com.tansci.service.SysLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName： SysLoginLogServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysLoginLogServiceImpl.java
 * @Description： 登录日志
 * @Author： tanyp
 * @Date： 2022/6/1 15:12
 **/
@Slf4j
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Override
    public IPage<SysLoginLog> page(Page page, SysLoginLog loginLog) {
        return this.baseMapper.selectPage(page,
                Wrappers.<SysLoginLog>lambdaQuery()
                        .like(Objects.nonNull(loginLog.getUserName()), SysLoginLog::getUserName, loginLog.getUserName())
                        .orderByDesc(SysLoginLog::getLoginTime)
        );
    }

}
