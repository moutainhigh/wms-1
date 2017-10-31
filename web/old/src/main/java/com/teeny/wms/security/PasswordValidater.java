package com.teeny.wms.security;

import com.teeny.wms.utils.EncryptDES;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * Created by lilei on 2017/7/10.
 */
public class PasswordValidater implements PasswordEncoder{

    public PasswordValidater() {
        super();
    }

    public String encodePassword(String rawPass, Object salt) {
        EncryptDES des = null;
        try {
            des = new EncryptDES("wms-security");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawPass;
    }

    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        String pass1 = ""+encPass;
        String pass2 = encodePassword(rawPass, salt);
        //return pass1.equals(pass2);
        boolean flag = encPass.equals(rawPass);
        return flag;
    }
}
