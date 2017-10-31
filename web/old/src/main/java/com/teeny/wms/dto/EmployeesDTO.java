package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/7/17.
 *
 */
public class EmployeesDTO implements Serializable{
    private int id;
    private String username;
    private String password;
    private String pinyin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
