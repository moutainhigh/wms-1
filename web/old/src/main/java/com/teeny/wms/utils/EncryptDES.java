package com.teeny.wms.utils;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.sun.crypto.provider.SunJCE;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

public class EncryptDES {
    private static String strDefaultKey = "national";
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);

        for(int i = 0; i < iLen; ++i) {
            int intTmp;
            for(intTmp = arrB[i]; intTmp < 0; intTmp += 256) {
                ;
            }

            if(intTmp < 16) {
                sb.append("0");
            }

            sb.append(Integer.toString(intTmp, 16));
        }

        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];

        for(int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }

        return arrOut;
    }

    public EncryptDES() throws Exception {
        this(strDefaultKey);
    }

    public EncryptDES(String strKey) throws Exception {
        this.encryptCipher = null;
        this.decryptCipher = null;
        Security.addProvider(new SunJCE());

        try {
            Key key = this.getKey(strKey.getBytes());
            this.encryptCipher = Cipher.getInstance("DES");
            this.encryptCipher.init(1, key);
            this.decryptCipher = Cipher.getInstance("DES");
            this.decryptCipher.init(2, key);
        } catch (Exception var3) {
            var3.printStackTrace();
            throw new Exception("加密错误！");
        }
    }

    public byte[] encrypt(byte[] arrB) throws Exception {
        return this.encryptCipher.doFinal(arrB);
    }

    public String encrytor(String strIn) {
        try {
            return byteArr2HexStr(this.encrypt(strIn.getBytes()));
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public byte[] decrypt(byte[] arrB) throws Exception {
        return this.decryptCipher.doFinal(arrB);
    }

    public String decryptor(String strIn) {
        try {
            return new String(this.decrypt(hexStr2ByteArr(strIn)));
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    private Key getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8];

        for(int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
            arrB[i] = arrBTmp[i];
        }

        Key key = new SecretKeySpec(arrB, "DES");
        return key;
    }

    public static void main(String[] args) {
    }
}
