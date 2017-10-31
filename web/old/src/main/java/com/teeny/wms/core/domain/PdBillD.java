package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 动态盘点明细表
 */
public class PdBillD {

    /*盘点单明细表id0    0606*/
    private int smbId;
    /*盘点单id,=pda_pdBill.billid*/
    private int billId;
    /*商品id,=pda_Products.p_id*/
    private int pid;
    /*生产日期*/
    private String makeDate;
    /*效期*/
    private String validdate;
    /*批号*/
    private String batchNo;
    /*库存数量*/
    private float eligibleQty;
    /*实盘数量*/
    private float pdQty;
    /*仓库id,=pda_storages.s_id*/
    private int sid;
    /*货位id,=pda_location.l_id*/
    private int locationId;
    /*供应商id,=pda_PutOnBill.c_id*/
    private int supplierId;
    /*入库时间*/
    private String instoreTime;
    /*盘单状态 1：初盘 2：复盘*/
    private int billStates;
    /*处理状态，0 未处理, 1 PDA已盘点 0531*/
    private int dealStates;
    /*状态 0提供数据 1pda回写数据*/
    private int pdaStates;


    public int getSmbId() {
        return smbId;
    }

    public void setSmbId(int smbId) {
        this.smbId = smbId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public String getValiddate() {
        return validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public float getEligibleQty() {
        return eligibleQty;
    }

    public void setEligibleQty(float eligibleQty) {
        this.eligibleQty = eligibleQty;
    }

    public float getPdQty() {
        return pdQty;
    }

    public void setPdQty(float pdQty) {
        this.pdQty = pdQty;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getInstoreTime() {
        return instoreTime;
    }

    public void setInstoreTime(String instoreTime) {
        this.instoreTime = instoreTime;
    }

    public int getBillStates() {
        return billStates;
    }

    public void setBillStates(int billStates) {
        this.billStates = billStates;
    }

    public int getDealStates() {
        return dealStates;
    }

    public void setDealStates(int dealStates) {
        this.dealStates = dealStates;
    }

    public int getPdaStates() {
        return pdaStates;
    }

    public void setPdaStates(int pdaStates) {
        this.pdaStates = pdaStates;
    }
}
