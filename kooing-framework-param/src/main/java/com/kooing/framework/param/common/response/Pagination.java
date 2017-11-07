package com.kooing.framework.param.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author      : kooing
 * @Date        : 2017/11/7 23:23
 * @Desription  : 分页
 */
@Data
public class Pagination implements Serializable {

    private static final long serialVersionUID = -5311077762660334862L;
    /** 当前页*/
    protected String pageNum;
    /** 一页的纪录数*/
    protected String pageSize;
    /** 总记录数*/
    protected String total;
    /** 总页数*/
    protected String pages;
}
