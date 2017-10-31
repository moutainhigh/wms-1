package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 上架确认单
 */
public class PutOnBill {
    /*上架确认单id*/
    private int billId;
    /*销售订单单号*/
    private String billNumber;
    /*经手人id,=pda_employees.e_id*/
    private int eid;
    /*上架确认单状态 10未处理 13已审核*/
    private int billStates;
    /*交换状态 0提供 1pda已读取 2pda已回写*/
    private int pdaStates;
    /*提供时间*/
    private String pdaInTime;
    /*读取时间*/
    private String pdaReTime;
    /*回写时间*/
    private String pdaWrTime;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getBillStates() {
        return billStates;
    }

    public void setBillStates(int billStates) {
        this.billStates = billStates;
    }

    public int getPdaStates() {
        return pdaStates;
    }

    public void setPdaStates(int pdaStates) {
        this.pdaStates = pdaStates;
    }

    public String getPdaInTime() {
        return pdaInTime;
    }

    public void setPdaInTime(String pdaInTime) {
        this.pdaInTime = pdaInTime;
    }

    public String getPdaReTime() {
        return pdaReTime;
    }

    public void setPdaReTime(String pdaReTime) {
        this.pdaReTime = pdaReTime;
    }

    public String getPdaWrTime() {
        return pdaWrTime;
    }

    public void setPdaWrTime(String pdaWrTime) {
        this.pdaWrTime = pdaWrTime;
    }
}
