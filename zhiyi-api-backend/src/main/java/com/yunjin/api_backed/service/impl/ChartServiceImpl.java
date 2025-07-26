package com.yunjin.api_backed.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjin.api_backed.model.entity.Chart;
import com.yunjin.api_backed.service.ChartService;
import com.yunjin.api_backed.mapper.ChartMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

}




