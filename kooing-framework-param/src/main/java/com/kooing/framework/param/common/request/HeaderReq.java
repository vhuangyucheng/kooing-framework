package com.kooing.framework.param.common.request;

import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */
@Data
public class HeaderReq extends BaseReq {

    @Valid
    private String method;//API接口名称
    private String appKey;//被调用的目标appKey,仅当被调用的API为第三方ISV提供时有效。
    private String session;//用户登录授权成功后，TOP颁发给应用的授权信息，详细介绍请点击这里。当此API的标签上注明：“需要授权”，则此参数必传；“不需要授权”，则此参数不需要传；“可选授权”，则此参数为可选。
    private String timestamp;//时间戳，格式为yyyy-mm-dd
                                //hh:mm:ss,时区为GMT+8,例如：
                                //2017-10-10  21:52:06.服务端允许客户端请求最大时间误差为10分钟
    private String format;//响应格式。默认xml格式，可选值：xml，json
    private String v;//API协议版本，可选值2.0
    private String isByAppId;//指定当前数据查询是否依赖app Id, 1 是,0不是
    /***********后期加校检：一定要传的TODO**********/
    /*@NotNull
	@Digits(fraction = 0, integer = 32, message = "平台标识必须为数字") // 限制12位int数字,小数部分为0位.
	private String appId;// 是 平台标识
    */
    private String appId;//平台标识。
    private String appName;//平台标识。
    private String simplify;//是否采用精简JSON返回格式，仅当format=json时有效，默认值为：false
    private String signMethod;//签名的摘要算法，可选值：hmac，md5
    private String sign;//API输入参数签名结果

    private String userFlag;//考虑到不同平台可能公用一个方法，那么需要区分是系统后台（1），商家后台（2），其他用户（0）
    private String storeId;//商店id

    private String reqOrg;//请求来源("mobile","pcShop","pcSys")
    /****当分页1：只查询总记录数，2不查询总记录数，只返回列表数据，其他值既查询总纪录又返回列表数据*/
    private String pageSumCount;
    /****当分页：只查询数据，不做分页***/
    private String pageDisable;
}
