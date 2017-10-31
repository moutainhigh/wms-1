package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
public class ProductsInventoryDTO implements Serializable {

    private int total;
    private List<PdListDTO> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PdListDTO> getList() {
        return list;
    }

    public void setList(List<PdListDTO> list) {
        this.list = list;
    }
}