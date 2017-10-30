package com.teeny.wms.model;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see GoodsAllocationEntity
 * @since 2017/9/22
 */

public class GoodsAllocationEntity {

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
