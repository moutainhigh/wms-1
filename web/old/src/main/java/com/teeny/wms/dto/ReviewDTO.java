package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by bao on 2017/7/27.
 * 出库复核DTO
 */
public class ReviewDTO implements Serializable {

    private String billNo; //销售单号
    private String deliveryLine; //配送路线
    private String customer; //客户
    private String priority; //优先级
    private String tempArea; //暂存区
    private String status;//状态
    private int documentStatus; //单据状态
    private String billRemark; //单据备注

    private float zhQuantity; //整货数量
    private float pxQuantity; //拼箱件数
    private float dbQuantity; //打包件数

    private float zhQuantityTotal; //整货数量总数
    private float pxQuantityTotal; //拼箱件数总数
    private float dbQuantityTotal; //打包件数总数

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getDeliveryLine() {
        return deliveryLine;
    }

    public void setDeliveryLine(String deliveryLine) {
        this.deliveryLine = deliveryLine;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTempArea() {
        return tempArea;
    }

    public void setTempArea(String tempArea) {
        this.tempArea = tempArea;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(int documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getBillRemark() {
        return billRemark;
    }

    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    public float getZhQuantity() {
        return zhQuantity;
    }

    public void setZhQuantity(float zhQuantity) {
        this.zhQuantity = zhQuantity;
    }

    public float getPxQuantity() {
        return pxQuantity;
    }

    public void setPxQuantity(float pxQuantity) {
        this.pxQuantity = pxQuantity;
    }

    public float getDbQuantity() {
        return dbQuantity;
    }

    public void setDbQuantity(float dbQuantity) {
        this.dbQuantity = dbQuantity;
    }

    public float getZhQuantityTotal() {
        return zhQuantityTotal;
    }

    public void setZhQuantityTotal(float zhQuantityTotal) {
        this.zhQuantityTotal = zhQuantityTotal;
    }

    public float getPxQuantityTotal() {
        return pxQuantityTotal;
    }

    public void setPxQuantityTotal(float pxQuantityTotal) {
        this.pxQuantityTotal = pxQuantityTotal;
    }

    public float getDbQuantityTotal() {
        return dbQuantityTotal;
    }

    public void setDbQuantityTotal(float dbQuantityTotal) {
        this.dbQuantityTotal = dbQuantityTotal;
    }
}
