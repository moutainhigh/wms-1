package com.teeny.wms.app.model;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ResponseEntity
 * @since 2017/10/16
 */
public class ResponseEntity<T> {

    private static final String MSG_SUCCESS = "请求成功.";

    private int code;
    private String message;
    private T data;

    public ResponseEntity() {
        this.code = 0;
        this.message = MSG_SUCCESS;
    }

    public ResponseEntity(T data) {
        this();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResponseEntity create(int code, String message) {
        ResponseEntity result = new ResponseEntity();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
