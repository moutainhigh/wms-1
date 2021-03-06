package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/7/29.
 *
 */
public class GoodsDTO implements Serializable {
    private int id;//id 1
    private int originalId;
    private String billNo;
    private String goodsName; //商品名 1
    private String lotNo; //批号 1
    private String specification; //规格 1
    private String validityDate; //有效期 1
    private float retailPrice; //零售价 1
    private float amount; //订单（配送）数量 1
    private float quantity; //收货数量 1
    private String manufacturer; //厂家 1
    private String produceArea; //产地
    private String serialNo; //序号
    private String barcode;   //商品码
    private int status;

    private float rate;
    private String zhUnit;
    private String lhUnit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public float getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(float retailPrice) {
        this.retailPrice = retailPrice;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProduceArea() {
        return produceArea;
    }

    public void setProduceArea(String produceArea) {
        this.produceArea = produceArea;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getZhUnit() {
        return zhUnit;
    }

    public void setZhUnit(String zhUnit) {
        this.zhUnit = zhUnit;
    }

    public String getLhUnit() {
        return lhUnit;
    }

    public void setLhUnit(String lhUnit) {
        this.lhUnit = lhUnit;
    }
}
