package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/7.
 */
public class StoreInventoryGoodsDTO implements Serializable {
    private int id; //id
    private int originalId;
    private int billId;       //盘点单id
    private int goodsId;       //商品id
    private String goodsName; //商品名
    private String location; // 货位
    private float inventoryCount; //盘点数量
    private float countInBill; //账面数量
    private String unit; //单位盒
    private String specification; //规格
    private String manufacturer; //厂家
    private int status; //状态
    private String locationCode;   //货位码
    private String goodsCode;      //商品码


    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(float inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public float getCountInBill() {
        return countInBill;
    }

    public void setCountInBill(float countInBill) {
        this.countInBill = countInBill;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
}
