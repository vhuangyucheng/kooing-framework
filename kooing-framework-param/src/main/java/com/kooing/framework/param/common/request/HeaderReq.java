package com.kooing.framework.param.common.request;

import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */
@Data
public class HeaderReq extends BaseReq {
    /** token*/
    private String token;
    /** 请求来源("mobile","pcShop","pcSys")*/
    private String reqOrg;
    /** 当前页 */
    private String pageNum;
    /** 页行数*/
    private String pageSize;
}
