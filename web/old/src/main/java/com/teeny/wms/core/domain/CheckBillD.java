package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 仅用于查看商品明细，如果不参看商品明细，作废此表
 --复核单,pda回写时只能修改复核数量、复核结论、差异原因
 */
public class CheckBillD {

    /*复核单明细表id*/
    private int smbId;
    /*复核单id,=pda_CheckBill.billid*/
    private int billId;
    /*商品id,=pda_Products.p_id*/
    private int pid;
    /*生产日期*/
    private String makeDate;
    /*效期*/
    private String validdate;
    /*批号*/
    private String batchno;
    /*订单数量*/
    private float eligibleQty;
    /*拣货数量*/
    private float pickQty;
    /*复核数量*/
    private float checkQty;
    /*复核结论*/
    private String checkState;
    /*差异原因*/
    private String checkReason;
    /*含税单价*/
    private float taxPrice;
    /*含税金额*/
    private float taxTotal;
    /*成本单价*/
    private float costPrice;
    /*成本金额*/
    private float costTotal;
    /*仓库id,=pda_storages.s_id*/
    private int sid;
    /*货位id,=pda_location.l_id*/
    private int LocationId;
    /*供应商id,=pda_PutOnBill.c_id*/
    private int supplierId;
    /*入库时间*/
    private String instoreTime;
    /*交换状态 0提供 1pda已回写*/
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

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public float getEligibleQty() {
        return eligibleQty;
    }

    public void setEligibleQty(float eligibleQty) {
        this.eligibleQty = eligibleQty;
    }

    public float getPickQty() {
        return pickQty;
    }

    public void setPickQty(float pickQty) {
        this.pickQty = pickQty;
    }

    public float getCheckQty() {
        return checkQty;
    }

    public void setCheckQty(float checkQty) {
        this.checkQty = checkQty;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public float getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(float taxPrice) {
        this.taxPrice = taxPrice;
    }

    public float getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(float taxTotal) {
        this.taxTotal = taxTotal;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public float getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(float costTotal) {
        this.costTotal = costTotal;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int locationId) {
        LocationId = locationId;
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

    public int getPdaStates() {
        return pdaStates;
    }

    public void setPdaStates(int pdaStates) {
        this.pdaStates = pdaStates;
    }
}
