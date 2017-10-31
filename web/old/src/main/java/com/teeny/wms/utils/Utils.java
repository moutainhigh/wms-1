package com.teeny.wms.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by lilei on 2017/7/10.
 */
public class Utils {
    public static String transCharCode(String str){
        String name="";
        try {
            name=new String(str.getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return name;
    }
}
