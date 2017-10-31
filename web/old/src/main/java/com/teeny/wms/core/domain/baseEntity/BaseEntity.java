package com.teeny.wms.core.domain.baseEntity;

/**
 * Created by lilei on 2017/7/23.
 */
public class BaseEntity<T> {


    private int result = 0;//状态码
    private String msg = "请求成功！";//消息
    private T data;

    public BaseEntity(T data) {
        this.data = data;
    }

    public BaseEntity() {

    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
