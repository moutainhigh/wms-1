package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lilei on 2017/8/14.
 */
public class StorePdDTO implements Serializable {

    private int billCount; //已盘点数
    private int billTotalCount; //总盘点数
    private int goodsCount; //已盘点商品数
    private int goodsTotal; //盘点商品总数
    private List<StroePdListDTO> goods; //商品

    public int getBillCount() {
        return billCount;
    }

    public void setBillCount(int billCount) {
        this.billCount = billCount;
    }

    public int getBillTotalCount() {
        return billTotalCount;
    }

    public void setBillTotalCount(int billTotalCount) {
        this.billTotalCount = billTotalCount;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(int goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public List<StroePdListDTO> getGoods() {
        return goods;
    }

    public void setGoods(List<StroePdListDTO> goods) {
        this.goods = goods;
    }
}
