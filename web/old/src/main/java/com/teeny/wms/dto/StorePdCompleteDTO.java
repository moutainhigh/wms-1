package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see StorePdCompleteDTO
 * @since 2017/8/14
 */
public class StorePdCompleteDTO implements Serializable{

    private String span;
    private int wAreaid;
    private int areaId;
    private int allocationId;
    private int goodsId;

    public String getSpan() {
        return span;
    }

    public void setSpan(String span) {
        this.span = span;
    }

    public int getwAreaid() {
        return wAreaid;
    }

    public void setwAreaid(int wAreaid) {
        this.wAreaid = wAreaid;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
}
