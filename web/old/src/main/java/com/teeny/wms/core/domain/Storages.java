package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 *
 */
public class Storages {
    /*id*/
    private int sid;
    /*编码*/
    private String serialNumber;
    /*拼音*/
    private String pinyin;
    /*名称*/
    private String name;
    /*简名*/
    private String alias;
    /*0,--整零库类型,0/-1未选择1整货库2零货库3零售拆零库*/
    private int wholeFlag;
    /*0,--温度条件,0常温1阴冷2冷链*/
    private int storeCondition;
    /*0,--质量类型,0合格库1不合格库2待退厂库*/
    private int qualityFlag;
    /*--状态，默认0 ，读取后 1， 读取出错 -1  0531*/
    private int ZT;

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

    public int getWholeFlag() {
        return wholeFlag;
    }

    public void setWholeFlag(int wholeFlag) {
        this.wholeFlag = wholeFlag;
    }

    public int getStoreCondition() {
        return storeCondition;
    }

    public void setStoreCondition(int storeCondition) {
        this.storeCondition = storeCondition;
    }

    public int getQualityFlag() {
        return qualityFlag;
    }

    public void setQualityFlag(int qualityFlag) {
        this.qualityFlag = qualityFlag;
    }

    public int getZT() {
        return ZT;
    }

    public void setZT(int ZT) {
        this.ZT = ZT;
    }
}
