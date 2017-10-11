package com.kooing.framework.param.common.response;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.RpcContext;
import com.kooing.framework.utils.Utility.PageView;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */
public class SuccessResp<T> extends CommResp<T> implements Serializable {
    private String application() {
        URL url = RpcContext.getContext().getUrl();
        if (url != null) {
            return url.getParameter("application");
        } else {
            return "";
        }
    }

    public SuccessResp() {
        super.code = "0";
        super.msg = "成功";
        super.data = null;
        super.pagination = null;
        super.times = System.currentTimeMillis();
        super.dubboName = application();
    }

    public SuccessResp(String _code, String _msg) {
        /**
         * @Author      : kooing
         * @Date        : 2017/10/11 21:17
         * @Desription  :
         */
        
        code = _code;
        msg = _msg;
        times = System.currentTimeMillis();
        super.dubboName = application();
    }

    public SuccessResp(PageView<?> page, T data) {
        /**
         * @Author      : kooing
         * @Date        : 2017/10/11 21:09
         * @Desription  :
         */
        
        super.code = "0";
        super.msg = "成功";
        super.data = data;
        super.times = System.currentTimeMillis();
        super.dubboName = application();
        if(page.)
    }
}
