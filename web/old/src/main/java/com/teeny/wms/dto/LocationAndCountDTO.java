package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see LocationAndCountDTO
 * @since 2017/8/25
 */
public class LocationAndCountDTO implements Serializable {

    private float amount;
    private String locationCode;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
}
