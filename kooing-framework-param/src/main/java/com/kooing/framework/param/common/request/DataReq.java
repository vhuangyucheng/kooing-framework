package com.kooing.framework.param.common.request;

import lombok.Data;
import lombok.Value;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */
@Data
public class DataReq<T> implements Serializable{

    private static final long serialVersionUID = -2604263643477983300L;

    @Valid
    private HeaderReq header;//公共参数

    @Valid
    private T body;//应用参数
}
