package com.teeny.wms.dto.Putaway;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/30.
 */
public class RecheckCompleteDTO implements Serializable {
    private String billNo;
    private int recipientId;
    private String remark;


    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
