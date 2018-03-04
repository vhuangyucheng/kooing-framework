package com.kooing.framework.param.common.request;

import lombok.Data;
import net.sf.json.JSONObject;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/9.
 */
@Data
public class BaseReq implements Serializable{

    private static final long serialVersionUID = 6947098193840850628L;

    /**
     * @Author      : kooing
     * @Date        : 2017/10/17 21:29
     * @Desription  : 暂时不知道什么用的
     * @return      :
     */
    protected void writePageClient(Request request, Object commResp) {
        JSONObject json = JSONObject.fromObject(commResp);
        Response.status(Response.Status.OK).entity(json).type("text/plain;charset=UTF-8").build();

    }
}
