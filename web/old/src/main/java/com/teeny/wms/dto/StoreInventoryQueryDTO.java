package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/7.
 */
public class StoreInventoryQueryDTO implements Serializable {
    private String type; //盘点类型
    private String goods; //商品
    private String storageArea; //库区
    private String area; //区域
    private String location; //货位

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(String storageArea) {
        this.storageArea = storageArea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
