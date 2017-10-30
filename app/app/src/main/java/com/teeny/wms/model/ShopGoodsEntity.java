package com.teeny.wms.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ShopGoodsEntity
 * @since 2017/8/20
 */

public class ShopGoodsEntity implements Parcelable {
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
    private String repositoryName;    //库区名字
    private String areaName;          //区域名字

    public ShopGoodsEntity() {
    }

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

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    protected ShopGoodsEntity(Parcel in) {
        id = in.readInt();
        originalId = in.readInt();
        billId = in.readInt();
        goodsId = in.readInt();
        goodsName = in.readString();
        location = in.readString();
        inventoryCount = in.readFloat();
        countInBill = in.readFloat();
        unit = in.readString();
        specification = in.readString();
        manufacturer = in.readString();
        status = in.readInt();
        locationCode = in.readString();
        goodsCode = in.readString();
        repositoryName = in.readString();
        areaName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(originalId);
        dest.writeInt(billId);
        dest.writeInt(goodsId);
        dest.writeString(goodsName);
        dest.writeString(location);
        dest.writeFloat(inventoryCount);
        dest.writeFloat(countInBill);
        dest.writeString(unit);
        dest.writeString(specification);
        dest.writeString(manufacturer);
        dest.writeInt(status);
        dest.writeString(locationCode);
        dest.writeString(goodsCode);
        dest.writeString(repositoryName);
        dest.writeString(areaName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShopGoodsEntity> CREATOR = new Creator<ShopGoodsEntity>() {
        @Override
        public ShopGoodsEntity createFromParcel(Parcel in) {
            return new ShopGoodsEntity(in);
        }

        @Override
        public ShopGoodsEntity[] newArray(int size) {
            return new ShopGoodsEntity[size];
        }
    };
}
