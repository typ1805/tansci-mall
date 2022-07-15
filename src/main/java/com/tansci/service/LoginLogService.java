package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.LoginLog;

/**
 * @ClassName： LoginLogService.java
 * @ClassPath： com.tansci.service.LoginLogService.java
 * @Description： 登录日志
 * @Author： tanyp
 * @Date： 2022/6/1 15:12
 **/
public interface LoginLogService extends IService<LoginLog> {

    IPage<LoginLog> page(Page page, LoginLog loginLog);

}
