package com.tomduan.dayulibrary.encryption;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by tomduan on 16-3-8.
 */
public class Encrypt {

    private final Map<String, String> mMap;
    private final String app_secret;

    public Encrypt(Map map, String app_secret) {
        this.app_secret = app_secret;
        this.mMap = map;
    }

    public Map<String, String> addSign(){

        String[] keys = mMap.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuilder query = new StringBuilder();

        query.append(app_secret);

        for (String key : keys) {
            String value = mMap.get(key);
            if (StringUtils.isNotEmpty(value)&&StringUtils.isNotEmpty(key)) {
                query.append(key).append(value);
            }
        }
        byte[] bytes = new byte[0];

        query.append(app_secret);

        try {
            bytes = encryptMD5(query.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        if (!mMap.containsKey("sign")){
            mMap.put("sign", byte2hex(bytes));
        }else {
            mMap.remove("sign");
            mMap.put("sign", byte2hex(bytes));
        }

        return mMap;
    }

    private byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    private byte[] encryptMD5(String data) throws IOException, NoSuchAlgorithmException {
        return MessageDigest.getInstance("MD5").digest(data.getBytes());
    }

    private String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}
