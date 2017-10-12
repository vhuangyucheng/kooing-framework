package com.kooing.framework.utils.Utility;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author      : kooing
 * @Date        : 2017/10/11 21:22
 * @Desription  : 分页工具
 */

@Data
public class PageView<T> implements Serializable {
    private static final long serialVersionUID = 6762913837247589878L;

    private List<?> resultMapList;
    /**总页数**/
    private long totalPage = 1;
    /**每页显示记录数**/
    private int pageSize = 12;
    /**当前页**/
    private int pageIndex = 1;
    /**总记录数**/
    private long totalRecord;
    /**当分页是：1只查询总记录数，2不查询总记录数，只返回列表数据，3其他值既查询总数据又返回列表数据**/
    private int pageSumCount;
    /**当分页时：1只查询数据不做分页**/
    private int pageDisable;

    private String sortName;

    private String sortOrder;

    private Object otherData;

    /**
     * @Author      : kooing
     * @Date        : 2017/10/11 21:26
     * @Desription  : 分页是否无效： 1 或者 true时候不可用
     */
    public boolean isDisabled(){
        return this.pageDisable == 1;
    }

    /**
     * @Author      : kooing
     * @Date        : 2017/10/11 21:32
     * @Desription  : 是否进行总数统计
     * @return      : pageSumCount==2,不统计记录数，不查询resultMapList
     */
    public boolean isCount(){
        return this.pageSumCount != 2;
    }
    /**
     * @Author      : kooing
     * @Date        : 2017/10/11 21:39
     * @Desription  : 是否*仅仅*进行总数统计
     * @return      : pageSumCount==1,只查询总纪录
     */
    public boolean isOnlySumCount(){
        return this.pageSumCount==1;
    }

    public PageView(int pageSize, int pageIndex){
        if(pageIndex <= 0){
            pageIndex = 1;
        }
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public PageView(int pageSize, int pageIndex, String sortName, String sortOrder){
        if(pageIndex <= 0){
            pageIndex = 1;
        }
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.sortName = sortName;
        this.sortOrder = sortOrder;
    }

    public PageView(int pageSize, int pageIndex, long totalRecord, List<T> resultMapList){
        if(pageIndex <= 0){
            pageIndex = 1;
        }
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        setTotalRecordWrap(totalRecord);
        this.resultMapList = resultMapList;
    }
    /**
     * @Author      : kooing
     * @Date        : 2017/10/11 22:15
     * @Desription  : 总记录0的时候页数是0，页面数优化，对简单的set方法封装一下
     * @return      : void
     */
    public void setTotalRecordWrap(long totalRecord){
        this.totalRecord = totalRecord;
        if(totalRecord  == 0) {
            setTotalPage(0);
            return;
        }
        //this.totalRecord % this.pageSize == 0 ? this.totalrecord/this.pageSize : this.totalrecord/this.pageSize+1);
        setTotalPage(this.totalRecord % this.pageSize == 0 ? this.totalRecord/this.pageSize : this.totalRecord/this.pageSize+1);
    }
}