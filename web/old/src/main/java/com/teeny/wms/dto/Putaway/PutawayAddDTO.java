package com.teeny.wms.dto.Putaway;

import java.io.Serializable;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see PutawayAddDTO
 * @since 2017/8/17
 */
public class PutawayAddDTO implements Serializable {


    private int id;  //original_id
    private List<Location> locations;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public static class Location{
        private String locationCode;
        private float amount;

        public String getLocationCode() {
            return locationCode;
        }

        public void setLocationCode(String locationCode) {
            this.locationCode = locationCode;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }

}
