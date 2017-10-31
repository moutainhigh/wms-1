package com.teeny.wms.core.domain;

/**
 * Created by lilei on 2017/7/15.
 * 货位资料
 */
public class Location {

    /*货位id*/
    private int lid;
    /*仓库id*/
    private int sid;
    /*编码*/
    private String locCode;
    /*库区id,=pda_stockArea.sa_id*/
    private int saId;
    /*区域id,=pda_Area.sc_id*/
    private int scId;
    /*货架*/
    private String shelfName;
    /*状态，默认0 ，读取后 1， 读取出错 -1  0531*/
    private int ZT;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public int getSaId() {
        return saId;
    }

    public void setSaId(int saId) {
        this.saId = saId;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public int getZT() {
        return ZT;
    }

    public void setZT(int ZT) {
        this.ZT = ZT;
    }
}
