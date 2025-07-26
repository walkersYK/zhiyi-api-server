package com.yunjin.api_backed.common;

import lombok.Data;

import java.util.List;

/**
 * 功能：
 *
 * @anther 韵锦
 * @from
 */
@Data
public class Page<T> {
    private Integer total;
    private List<T> list;
}
