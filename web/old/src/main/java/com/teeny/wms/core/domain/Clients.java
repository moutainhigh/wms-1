package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 往来单位资料
 */
public class Clients {

    /*往来单位id*/
    private int cid;
    /*编码*/
    private String serialNumber;
    /**/
    private String pinyin;
    /*名称*/
    private String name;
    /*简名*/
    private String alias;
    /*单位类型 0客户1供应商2两者皆是*/
    private String csFlag;
    /*联系人*/
    private String contactPersonal;
    /*联系电话*/
    private String phoneNumber;
    /*联系地址*/
    private String address;
    /*路线*/
    private String roadName;
    /*状态，默认0 ，读取后 1， 读取出错 -1  0531*/
    private int ZT;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCsFlag() {
        return csFlag;
    }

    public void setCsFlag(String csFlag) {
        this.csFlag = csFlag;
    }

    public String getContactPersonal() {
        return contactPersonal;
    }

    public void setContactPersonal(String contactPersonal) {
        this.contactPersonal = contactPersonal;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public int getZT() {
        return ZT;
    }

    public void setZT(int ZT) {
        this.ZT = ZT;
    }
}
