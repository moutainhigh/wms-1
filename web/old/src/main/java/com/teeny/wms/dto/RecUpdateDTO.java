package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lilei on 2017/8/2.
 */
public class RecUpdateDTO implements Serializable {

    private int id;//original_id
    private int smbId; //单据明细id
    private List<AcceptAddDTO> param;

    public int getSmbId() {
        return smbId;
    }

    public void setSmbId(int smbId) {
        this.smbId = smbId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AcceptAddDTO> getParam() {
        return param;
    }

    public void setParam(List<AcceptAddDTO> param) {
        this.param = param;
    }
}
