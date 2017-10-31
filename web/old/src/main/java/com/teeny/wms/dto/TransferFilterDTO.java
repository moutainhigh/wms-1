package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see TransferFilterDTO
 * @since 2017/8/24
 */
public class TransferFilterDTO implements Serializable {

    private List<CommonDTO> warehouseList;
    private List<CommonDTO> saList;
    private List<CommonDTO> locationList;

    public List<CommonDTO> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<CommonDTO> warehouseList) {
        this.warehouseList = warehouseList;
    }

    public List<CommonDTO> getSaList() {
        return saList;
    }

    public void setSaList(List<CommonDTO> saList) {
        this.saList = saList;
    }

    public List<CommonDTO> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<CommonDTO> locationList) {
        this.locationList = locationList;
    }
}
