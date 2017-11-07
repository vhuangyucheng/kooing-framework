package com.kooing.framework.param.common.response;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.RpcContext;

import java.io.Serializable;


/**
 * @author      : kooing
 * @Date        : 2017/11/7 23:28
 * @Desription  : 成功的返回相应
 * @return      :
 */
public class SuccessResp<T> extends CommResp<T> implements Serializable {

    /**
     * @author      : kooing
     * @Date        : 2017/10/12 19:47
     * @Desription  : 这个暂时还看不出什么，可能是返回接口名
     * @return      :
     */
    private String application() {
        URL url = RpcContext.getContext().getUrl();
        if (url != null) {
            return url.getParameter("application");
        } else {
            return "";
        }
    }
    
    /**
     * @author      : kooing
     * @Date        : 2017/11/7 23:29
     * @Desription  : 不配置分页的情况
     * @return      : 
     */
    public SuccessResp(T data){
        code = "0";
        message = "成功";
        pagination = null;
        this.data = data;
        times = System.currentTimeMillis();
        dubboName = application();
    }

    /**
     * @author      : kooing
     * @Date        : 2017/11/7 23:35
     * @Desription  : 配置分页的情况
     * @return      :
     */
    public SuccessResp(Pagination pagination, T data){
        code = "0";
        message = "成功";
        this.pagination = pagination;
        this.data = data;
        times = System.currentTimeMillis();
        dubboName = application();
    }

    /**
     * @author      : kooing
     * @Date        : 2017/11/7 23:37
     * @Desription  : 自定义code和message，带pagination情况
     * @return      :
     */
    public SuccessResp(String code, String message, Pagination pagination, T data){
        this.code = code;
        this.message = message;
        this.pagination = pagination;
        this.data = data;
        times = System.currentTimeMillis();
        dubboName = application();
    }

    /**
     * @author      : kooing
     * @Date        : 2017/11/7 23:39
     * @Desription  : 自定义code和message，不带pagination情况
     * @return      :
     */
    public SuccessResp(String code, String message, T data){
        this.code = code;
        this.message = message;
        this.pagination = null;
        this.data = data;
        times = System.currentTimeMillis();
        dubboName = application();
    }
}
