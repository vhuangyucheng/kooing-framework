package com.kooing.framework.param.common.response;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author      : kooing
 * @Date        : 2017/11/7 23:23
 * @Desription  : 分页
 */
@Data
public class Pagination implements Serializable {

    private static final long serialVersionUID = -5311077762660334862L;
    /** 当前页*/
    protected Integer pageNum;
    /** 一页的纪录数*/
    protected Integer pageSize;
    /** 总记录数*/
    protected Integer total;
    /** 总页数*/
    protected Integer pages;

    /**
     * @author      : kooing
     * @Date        : 2017/11/9 12:06
     * @Desription  : 构造函数使用pagehelper获得total和pages
     * @return      :
     */
    public Pagination(List<?> list){
        PageInfo page = new PageInfo(list);
        Long total = page.getTotal();
        int pages = page.getPages();
        setTotal(total.intValue());
        setPages(pages);
    }

    /**
     * @author      : kooing
     * @Date        : 2017/11/9 12:06
     * @Desription  : 无参
     * @return      :
     */
    public Pagination(){

    }
}
