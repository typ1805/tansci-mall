package com.tansci.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysUserAddress;
import com.tansci.mapper.SysUserAddressMapper;
import com.tansci.service.SysUserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @path：com.tansci.service.impl.SysUserAddressServiceImpl.java
 * @className：SysUserAddressServiceImpl.java
 * @description： 商户地址
 * @author：tanyp
 * @dateTime：2022/7/21 13:25 
 * @editNote：
 */
@Slf4j
@Service
public class SysUserAddressServiceImpl extends ServiceImpl<SysUserAddressMapper, SysUserAddress> implements SysUserAddressService {
}
