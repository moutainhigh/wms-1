package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by bao on 2017/7/27.
 * 复核完成/暂存DTO
 */
public class ReviewUpdateDTO implements Serializable {

    private String billNo; //订单号
    private int reviewerId; //复核人ID
    private String remark; //差异备注

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
