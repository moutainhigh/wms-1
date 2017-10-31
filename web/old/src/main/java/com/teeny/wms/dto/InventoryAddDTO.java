package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see InventoryAddDTO
 * @since 2017/9/7
 */
public class InventoryAddDTO implements Serializable {
    public int inventoryId;                 //盘点单id
    public int goodsId;                     //商品id
    public String locationCode;             //货位码
    public String lotNo;                    //批号
    public int amount;                      //数量
    public String validateDate;             //有效期


    public int locationId;                  //货位id
    public int billState;                   //初盘1  复盘2
}
