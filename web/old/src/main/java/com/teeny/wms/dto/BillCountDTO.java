package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by bao on 2017/7/25.
 */
public class BillCountDTO implements Serializable {

    //验收单数
    private int acceptBillCount;
    //上架单数
    private int putawayBillCount;
    //审核单数
    private int reviewBillCount;
    //调拨单数
    private int tranferBillCount;

    public int getAcceptBillCount() {
        return acceptBillCount;
    }

    public void setAcceptBillCount(int acceptBillCount) {
        this.acceptBillCount = acceptBillCount;
    }

    public int getPutawayBillCount() {
        return putawayBillCount;
    }

    public void setPutawayBillCount(int putawayBillCount) {
        this.putawayBillCount = putawayBillCount;
    }

    public int getReviewBillCount() {
        return reviewBillCount;
    }

    public void setReviewBillCount(int reviewBillCount) {
        this.reviewBillCount = reviewBillCount;
    }

    public int getTranferBillCount() {
        return tranferBillCount;
    }

    public void setTranferBillCount(int tranferBillCount) {
        this.tranferBillCount = tranferBillCount;
    }
}
