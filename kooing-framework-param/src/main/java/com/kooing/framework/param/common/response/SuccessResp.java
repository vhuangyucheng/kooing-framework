package com.kooing.framework.param.common.response;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.RpcContext;
import com.kooing.framework.utils.Utility.PageView;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */
public class SuccessResp<T> extends CommResp<T> implements Serializable {

    /**
     * @Author      : kooing
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
     * @Author      : kooing
     * @Date        : 2017/10/12 19:48
     * @Desription  : 构造参数
     * @return      :
     */
    public SuccessResp() {
        super.code = "0";
        super.msg = "成功";
        super.data = null;
        super.pagination = null;
        super.times = System.currentTimeMillis();
        super.dubboName = application();
    }

    public SuccessResp(String _code, String _msg) {
        code = _code;
        msg = _msg;
        times = System.currentTimeMillis();
        super.dubboName = application();
    }

    /**
     * @Author      : kooing
     * @Date        : 2017/10/12 21:19
     * @Desription  : 有pagination,data传进来的时候
     * @return      :
     */
    public SuccessResp(PageView<?> pageView, T data) {
        super.code = "0";
        super.msg = "成功";
        super.data = data;
        super.times = System.currentTimeMillis();
        super.dubboName = application();
        if(pageView.isDisabled()){
            super.pagination = null;
            return ;
        }
        //分页处理
        long totalRecord = pageView.getTotalRecord();
        int pageIndex = pageView.getPageIndex();
        int pageSize = pageView.getPageSize();
        long totalPage = pageView.getTotalPage();
        String nextPage;
        String prevPage;

        if(totalPage == 0){
            totalPage = 1;
        }
        int prevPageTemp = pageIndex - 1;
        int nextPageTemp = pageIndex + 1;

        //对prevPage,nextPage过大和过小处理
        if(nextPageTemp > totalPage){
            nextPage = String.valueOf(totalPage);
        } else {
            if (nextPageTemp == 0) {
                nextPage = "1";
            }else{
                nextPage = String.valueOf(nextPageTemp);
            }
        }

        if(prevPageTemp > totalPage){
            prevPage = String.valueOf(totalPage);
        } else{
            if(prevPageTemp == 0){
                prevPage = "1";
            }else{
                prevPage = String.valueOf(prevPageTemp);
            }
        }

        Pagination pagination = new Pagination();
        pagination.setNextPage(nextPage);
        pagination.setPageIndex(""+pageIndex);
        pagination.setPageSize(""+pageSize);
        pagination.setPrevPage(prevPage);
        pagination.setTotalRecord(""+totalRecord);
        pagination.setTotalPage(""+totalPage);
        super.pagination = pagination;
    }

    /**
     * @Author      : kooing
     * @Date        : 2017/10/12 22:23
     * @Desription  : 有data的情况
     * @return      : 
     */
    public SuccessResp(String code, String msg, T data){
        super.times = System.currentTimeMillis();
        super.code = code;
        super.msg = msg;
        super.data = data;
        super.dubboName = application();
    }
    
    /**
     * @Author      : kooing
     * @Date        : 2017/10/12 22:23
     * @Desription  : pageIndex,pageSize,total,data的情况
     * @return      : 
     */
    public SuccessResp(int _pageIndex, int _pageSize, int _total, T _data){
        super.code = "0";
        super.msg = "成功";
        super.data = _data;
        super.times = System.currentTimeMillis();
        super.dubboName = application();

        //分页处理
        String totalRecord = String.valueOf(_total);
        String pageIndex = String.valueOf(_pageIndex);
        String pageSize = String.valueOf(_pageSize);
        String totalPage = String.valueOf(Integer.valueOf(_total) / Integer.valueOf(_pageSize) + ((Integer.valueOf(_total) % Integer.valueOf(_pageSize))>0?1:0));
        String nextPage;
        String prevPage;

        if(totalPage.equals("0")){
            totalPage = "1";
        }
        int prevPageTemp = _pageIndex - 1;
        int nextPageTemp = _pageIndex + 1;
        int TotalPageTemp = Integer.valueOf(totalPage);

        //对prevPage,nextPage过大和过小处理
        if(nextPageTemp > TotalPageTemp){
            nextPage = String.valueOf(totalPage);
        } else {
            if (nextPageTemp == 0) {
                nextPage = "1";
            }else{
                nextPage = String.valueOf(nextPageTemp);
            }
        }

        if(prevPageTemp > TotalPageTemp){
            prevPage = String.valueOf(totalPage);
        } else{
            if(prevPageTemp == 0){
                prevPage = "1";
            }else{
                prevPage = String.valueOf(prevPageTemp);
            }
        }

        Pagination pagination = new Pagination();
        pagination.setNextPage(nextPage);
        pagination.setPageIndex(pageIndex);
        pagination.setPageSize(pageSize);
        pagination.setPrevPage(prevPage);
        pagination.setTotalRecord(totalRecord);
        pagination.setTotalPage(totalPage);
        super.pagination = pagination;
    }
}
