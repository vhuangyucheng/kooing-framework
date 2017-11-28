package com.kooing.framework.param.common.response;

import com.github.pagehelper.PageHelper;
import com.kooing.framework.param.common.request.DataReq;
import com.kooing.framework.param.common.request.HeaderReq;

/**
 * @author : kooing
 * @date : 2017/11/9 - 11:48
 * @desription : page工具
 * @update by :
 */
public class PageUtil {
    public static void startPage(DataReq<?> data){
        HeaderReq header = data.getHeader();
        int pageNum = Integer.parseInt(header.getPageNum());
        int pageSize = Integer.parseInt(header.getPageSize());
        PageHelper.startPage(pageNum, pageSize);
    }
}
