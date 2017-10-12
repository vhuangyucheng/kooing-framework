package com.kooing.framework.param.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */
@Data
public class Pagination implements Serializable {

    private static final long serialVersionUID = -5311077762660334862L;
    protected String totalRecord;//总记录
    protected String totalPage;//总页数
    protected String pageIndex;//当前页
    protected String pageSize;//每页纪录数
    protected String prevPage;//前一页
    protected String nextPage;//下一页
}
