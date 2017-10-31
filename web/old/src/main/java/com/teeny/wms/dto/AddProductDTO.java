package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AddProductDTO
 * @since 2017/8/12
 */
public class AddProductDTO implements Serializable{
    public int id;
    public int pId;
    public String lotNo;
    public String locationCode;
    public int locationId;
    public float amount;
    public float originalAmount;
    public String validateDate; //有效期
}
