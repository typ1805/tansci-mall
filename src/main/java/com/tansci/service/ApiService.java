package com.tansci.service;

import com.tansci.domain.Goods;
import com.tansci.domain.dto.ApiDto;

import java.util.List;

/**
 * @path：com.tansci.service.ApiService.java
 * @className：ApiService.java
 * @description：API接口
 * @author：tanyp
 * @dateTime：2022/3/29 20:38
 * @editNote：
 */
public interface ApiService {

    List<Goods> goodsList(String userId);

    Object payment(ApiDto dto);

}
