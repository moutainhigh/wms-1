package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 区域资料
 */
public class Area {
    /*区域id*/
    private int scId;
    /*仓库id*/
    private int sid;
    /*编码*/
    private String serialNumber;
    /*名称*/
    private String name;
    /*状态，默认0 ，读取后 1， 读取出错 -1  0531*/
    private int ZT;

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZT() {
        return ZT;
    }

    public void setZT(int ZT) {
        this.ZT = ZT;
    }
}
