package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SysLoginLog;

/**
 * @ClassName： SysLoginLogService.java
 * @ClassPath： com.tansci.service.SysLoginLogService.java
 * @Description： 登录日志
 * @Author： tanyp
 * @Date： 2022/6/1 15:12
 **/
public interface SysLoginLogService extends IService<SysLoginLog> {

    IPage<SysLoginLog> page(Page page, SysLoginLog loginLog);

}
