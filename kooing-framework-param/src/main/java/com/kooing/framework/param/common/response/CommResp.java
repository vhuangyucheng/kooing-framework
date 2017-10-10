package com.kooing.framework.param.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */
@Data
public class CommResp<T> extends BaseResp implements Serializable{

    private static final long serialVersionUID = 1111936137924553556L;

    protected String code;//是否成功

    protected String msg;//消息

    protected Pagination pagination;//分页
}
