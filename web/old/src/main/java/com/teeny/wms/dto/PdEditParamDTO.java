package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/15.
 */
public class PdEditParamDTO implements Serializable {
    private String lotNo;
    private float count; //数量
    private String validateDate; //有效期

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(String validateDate) {
        this.validateDate = validateDate;
    }
}
