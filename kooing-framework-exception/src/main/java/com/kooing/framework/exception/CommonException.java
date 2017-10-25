package com.kooing.framework.exception;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author : kooing
 * @Date : 2017/10/25 - 16:28
 * @Desription :
 * @update by :
 */
@Setter
@Getter
public class CommonException extends Exception implements ExceptionMapper {
    private static final long serialVersionUID = 1L;
    private String code = "";
    private String message = "";
    private Long times = null;
    private String application = "";
    @Override
    public Response toResponse(Throwable exception) {
        return null;
    }

    /**
     * @Author      : kooing
     * @Date        : 2017/10/25 16:37
     * @Desription  : 无参构造函数
     * @return      :
     */
    public CommonException(){
        applicationName();
        this.times = System.currentTimeMillis();
    }
    /**
     * @Author      : kooing
     * @Date        : 2017/10/25 16:36
     * @Desription  : 创建参数对象
     * @return      :
     */
    public CommonException(String message){
        this.message = message;
        applicationName();
        this.times = System.currentTimeMillis();
    }

    /**
     * @Author      : kooing
     * @Date        : 2017/10/25 18:06
     * @Desription  : 创建带code和message的对象
     * @return      :
     */
    public CommonException(String code, String message){
        this.code = code;
        this.message = message;
        this.times = System.currentTimeMillis();
        applicationName();
    }
    private void applicationName() {
        this.application = ApplicationNameMap.get();
    }
}
