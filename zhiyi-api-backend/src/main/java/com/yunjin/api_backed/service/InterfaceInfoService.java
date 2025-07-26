package com.yunjin.api_backed.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunjin.api_backed.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.yunjin.api_backed.model.dto.user.UserQueryRequest;
import com.yunjin.api_backed.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest infoQueryRequest);

}
