package com.kooing.framework.exception;


import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import net.sf.json.JSONObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : kooing
 * @date : 2017/10/25 - 18:10
 * @desription :
 * @update by :
 */
@Provider
public class CustomExceptionMapper implements ExceptionMapper<CommonException> {
    @Override
    public Response toResponse(CommonException commonException) {
        Map<String, Object> errorInfoMap = new HashMap<>();
        String code = "-1";
        String message = "网络异常";
        if(!(commonException.getCode() == null || "".equals(commonException.getCode()))){
            code = commonException.getCode();
        }
        if(!(commonException.getMessage() == null || "".equals(commonException.getCode()))){
            message = commonException.getMessage();
        }
        errorInfoMap.put("code", code);
        errorInfoMap.put("message", message);
        errorInfoMap.put("times", commonException.getTimes());
        errorInfoMap.put("dubboName", commonException.getApplication());

        JSONObject json = JSONObject.fromObject(errorInfoMap);
        //装设者设计模式
        return Response.status(Response.Status.OK).entity(json).type(ContentType.TEXT_PLAIN_UTF_8).build();
    }
}
