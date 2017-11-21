package com.dubatovka.app.manager;

import org.apache.commons.codec.digest.DigestUtils;

public class JCasinoEncryptor {
    
    private JCasinoEncryptor() {
    }
    
    public static String encryptMD5(String source) {
        if (source == null) {
            return null;
        }
        return DigestUtils.md5Hex(source);
    }
    
}