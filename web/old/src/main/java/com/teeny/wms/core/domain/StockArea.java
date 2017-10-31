package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 *库区资料
 */
public class StockArea {

    /*库区id*/
    private int saId;
    /*仓库id*/
    private int sid;
    /*编码*/
    private String serialNumber;
    /*整零库类型,0/-1未选择1整货库2零货库3零售拆零库  0531*/
    private int wholeFlag;
    /*质量类型,0合格库1不合格库2待退厂库  0531*/
    private int qualityFlag;
    /*名称*/
    private String name;
    /*状态，默认0 ，读取后 1， 读取出错 -1  0531*/
    private int ZT;

    public int getSaId() {
        return saId;
    }

    public void setSaId(int saId) {
        this.saId = saId;
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

    public int getWholeFlag() {
        return wholeFlag;
    }

    public void setWholeFlag(int wholeFlag) {
        this.wholeFlag = wholeFlag;
    }

    public int getQualityFlag() {
        return qualityFlag;
    }

    public void setQualityFlag(int qualityFlag) {
        this.qualityFlag = qualityFlag;
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
