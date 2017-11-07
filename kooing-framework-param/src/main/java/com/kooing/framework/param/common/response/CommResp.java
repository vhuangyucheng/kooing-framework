package com.kooing.framework.param.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */
@Data
public class CommResp<T> extends BaseResp {

    protected String code;//是否成功

    protected String message;//消息

    protected Pagination pagination;//分页

    protected T data;

    protected Long times;

    protected String dubboName;
}
