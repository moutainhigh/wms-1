package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 上架确认单,pda回写时需要新增数据，不能修改提供的数据，同一批次上多个仓库或货位时，pda复制数据后只能修改仓库id及货位id\上架数量
 */
public class PutOnBillD {

    /*上架确认单明细表id*/
    private int smbId;
    /*上架确认单id,=pda_PutOnBill.billid*/
    private int billId;
    /*商品id,=pda_Products.p_id*/
    private int pid;
    /*生产日期*/
    private String makeDate;
    /*效期*/
    private String ValidDate;
    /*批号*/
    private String batchNo;
    /*上架数量*/
    private float eligibleQty;
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
    private int locationId;
    /*供应商id,=pda_PutOnBill.c_id*/
    private int supplierId;
    /*入库时间*/
    private String instoreTime;
    /*原始行号，复制行时，需要保留该数值不变，用于关联ERP原始明细 0531,复制的行smb_id=0*/
    private int lineSort;
    /*处理状态，0 未处理, 1 PDA已经处理（上架） 0531*/
    private int dealStates;
    /*状态 0提供数据 1pda回写数据 */
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

    public String getValidDate() {
        return ValidDate;
    }

    public void setValidDate(String validDate) {
        ValidDate = validDate;
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

    public int getLineSort() {
        return lineSort;
    }

    public void setLineSort(int lineSort) {
        this.lineSort = lineSort;
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
