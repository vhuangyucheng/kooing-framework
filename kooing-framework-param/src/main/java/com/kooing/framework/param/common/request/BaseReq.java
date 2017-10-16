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

    protected String operType;//操作类型add表示增加，edit表示修改
    protected String operUserId;//操作用户id
    protected String operUserName;//操作用户的昵称
    protected String page;
    protected String rows;

    protected String startTime;
    protected String endTime;

    //暂时不知道什么用的
    protected void WritePageClient(Request request, Object commResp) {
        JSONObject json = JSONObject.fromObject(commResp);
        Response.status(Response.Status.OK).entity(json).type("text/plain;charset=UTF-8").build();

    }
}
