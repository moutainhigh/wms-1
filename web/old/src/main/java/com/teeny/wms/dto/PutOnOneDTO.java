package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see PutOnOneDTO
 * @since 2017/8/5
 * 上架时修改的DTO
 *
 */
public class PutOnOneDTO implements Serializable {

    private int goodsDetailId;//id
    private List<UpdateEntity> allcations; //货位编码

    public static class UpdateEntity {
        private int allcationId;  //货位id
        private int amount;      //数量

        public int getAllcationId() {
            return allcationId;
        }

        public void setAllcationId(int allcationId) {
            this.allcationId = allcationId;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    public int getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(int goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public List<UpdateEntity> getAllcations() {
        return allcations;
    }

    public void setAllcations(List<UpdateEntity> allcations) {
        this.allcations = allcations;
    }
}

