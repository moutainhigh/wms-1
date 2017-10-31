package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/11.
 */
public class ProductDetailsDTO implements Serializable {

    private int id; //id
    private String goodsName; //商品名
    private String number; //编号111
    private String lotNo; //批号
    private String location; //货位
    private String manufacturer; //厂家
    private String unit; //单位11111
    private int amount; //数量
    private String validateDate; //有效期
    private String standard; //规格
    private String productDate; //生产日期111
    private String status; //状态


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(String validateDate) {
        this.validateDate = validateDate;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
