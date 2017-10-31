package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lilei on 2017/8/15.
 */
public class PdEditDTO implements Serializable {

    private int id;  //original_id

    private List<PdEditParamDTO> param;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PdEditParamDTO> getParam() {
        return param;
    }

    public void setParam(List<PdEditParamDTO> param) {
        this.param = param;
    }

}
