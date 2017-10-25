package com.kooing.framework.exception;

import com.alibaba.dubbo.rpc.protocol.rest.RestConstraintViolation;
import com.alibaba.dubbo.rpc.protocol.rest.RpcExceptionMapper;
import com.alibaba.dubbo.rpc.protocol.rest.ViolationReport;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import net.sf.json.JSONObject;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : kooing
 * @date : 2017/10/25 - 19:03
 * @desription :
 * @update by :
 */
public class MyValidationWxceptionMapper extends RpcExceptionMapper {
    protected Response handleConstraintViolationException(ConstraintViolationException cve) {
        // 采用json输出代替xml输出
        Map<String, Object> errInfoMap = new HashMap<>();
        Map<String, String> result = new HashMap<>();
        String code = "-1";
        String message = "系统异常";

        ViolationReport report = new ViolationReport();
        for (ConstraintViolation cv : cve.getConstraintViolations()) {
            report.addConstraintViolation(
                    new RestConstraintViolation(cv.getPropertyPath().toString(),
                            cv.getMessage(),
                            cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString()));
            message = cv.getMessage();
        }

        errInfoMap.put("code", code);
        errInfoMap.put("message", message);
        errInfoMap.put("times", System.currentTimeMillis());
        errInfoMap.put("dubboName", ApplicationNameMap.get());
        JSONObject json = JSONObject.fromObject(errInfoMap);

        return Response.status(Response.Status.OK).entity(json).type(ContentType.TEXT_PLAIN_UTF_8).build();

        // return
        // Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(report).type(ContentType.APPLICATION_JSON_UTF_8).build();
    }

}
