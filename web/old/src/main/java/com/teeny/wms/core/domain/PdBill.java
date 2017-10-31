package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 动态盘点单
 */
public class PdBill {

    /*盘点单id*/
    private int billId;
    /*盘点名称*/
    private String pdName;
    /*仓库id,=pda_storages.s_id*/
    private int sid;
    /*盘单状态 0:未完成 1：初盘 2：复盘 3：已完成*/
    private int billStates;
    /*盘点类型 0：门店盘点 1：仓库盘点*/
    private int pdType;
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

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getBillStates() {
        return billStates;
    }

    public void setBillStates(int billStates) {
        this.billStates = billStates;
    }

    public int getPdType() {
        return pdType;
    }

    public void setPdType(int pdType) {
        this.pdType = pdType;
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
