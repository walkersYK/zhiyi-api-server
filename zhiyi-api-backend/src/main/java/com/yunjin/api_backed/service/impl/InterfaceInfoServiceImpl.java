package com.yunjin.api_backed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjin.api_backed.common.ErrorCode;
import com.yunjin.api_backed.exception.BusinessException;
import com.yunjin.api_backed.mapper.InterfaceInfoMapper;
import com.yunjin.api_backed.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.yunjin.api_backed.model.entity.InterfaceInfo;
import com.yunjin.api_backed.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

    @Override
    public QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest infoQueryRequest) {
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();

        if (infoQueryRequest == null) {
            return queryWrapper;
        }
        // 获取查询参数
        String name = infoQueryRequest.getName();
        Integer status = infoQueryRequest.getStatus();
        // 模糊查询：接口名称
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }

        // 精确匹配：状态
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        // 按创建时间降序排列
        queryWrapper.orderByDesc("createTime");

        return queryWrapper;
    }



}



