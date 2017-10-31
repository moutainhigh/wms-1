package com.teeny.wms.core.domain;

import java.io.Serializable;

/**
 * Created by lilei on 2017/7/15.
 *
 */

public class Employess implements Serializable{
    private int id;
    private String serialNumber;
    private String password;
    private String pinyin;
    private String username;
    private String alias;
    private String phone;
    private String address;
    private int ZT;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZT() {
        return ZT;
    }

    public void setZT(int ZT) {
        this.ZT = ZT;
    }

    @Override
    public String toString() {
        return "Employess{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", password='" + password + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", username='" + username + '\'' +
                ", alias='" + alias + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", ZT=" + ZT +
                '}';
    }
}
