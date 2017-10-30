package com.teeny.wms.model.request;

import com.teeny.wms.model.AcceptanceLotEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AcceptanceRequestEntity
 * @since 2017/8/24
 */

public class AcceptanceRequestEntity {

    private int id;
    private int smbId;
    private List<AcceptanceLotEntity> param;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSmbId() {
        return smbId;
    }

    public void setSmbId(int smbId) {
        this.smbId = smbId;
    }

    public List<AcceptanceLotEntity> getParam() {
        return param;
    }

    public void setParam(List<AcceptanceLotEntity> param) {
        this.param = param;
    }
}
