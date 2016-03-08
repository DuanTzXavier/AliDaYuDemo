package com.tomduan.dayulibrary.encryption;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by tomduan on 16-3-8.
 */
public class Encryption {
    private final static String LOG = "Encryption";

    private final String app_key;
    private final String app_secret;

    private Map<String, String> mMap = new HashMap<>();

    public Encryption(String app_key, String app_secret){
        this.app_key = app_key;
        this.app_secret = app_secret;
        initMap();
    }

    private void initMap() {
        mMap.put("format", "json");
        mMap.put("sign_method", "md5");
        mMap.put("timestamp", getTimeStamp());
        mMap.put("method", "alibaba.aliqin.fc.sms.num.send");
        mMap.put("sms_type", "normal");
        mMap.put("v", "2.0");
        mMap.put("app_key", app_key);
    }

    public Encryption setEec_num(String rec_num){
        if (!mMap.containsKey("rec_num")){
            mMap.put("rec_num", rec_num);
        }else {
            mMap.remove("rec_num");
            mMap.put("rec_num", rec_num);
        }
        return this;
    }

    public Encryption setSms_param(String sms_param){
        if (!mMap.containsKey("sms_param")){
            mMap.put("sms_param", sms_param);
        }else {
            mMap.remove("sms_param");
            mMap.put("sms_param", sms_param);
        }
        return this;
    }

    public Encryption setCodeAndProduct(String code, String product){

        String sms_param = "{\"code\":\""+code+"\",\"product\":\""+product+"\"}";

        if (!mMap.containsKey("sms_param")){
            mMap.put("sms_param", sms_param);
        }else {
            mMap.remove("sms_param");
            mMap.put("sms_param", sms_param);
        }
        return this;
    }

    public Encryption setSms_template_code(String sms_template_code){
        if (!mMap.containsKey("sms_template_code")){
            mMap.put("sms_template_code", sms_template_code);
        }else {
            mMap.remove("sms_template_code");
            mMap.put("sms_template_code", sms_template_code);
        }
        return this;
    }

    public Encryption setSms_free_sign_name(String sms_free_sign_name){
        if (!mMap.containsKey("sms_free_sign_name")){
            mMap.put("sms_free_sign_name", sms_free_sign_name);
        }else {
            mMap.remove("sms_free_sign_name");
            mMap.put("sms_free_sign_name", sms_free_sign_name);
        }
        return this;
    }

    public Encrypt bulid(){
        return new Encrypt(mMap, app_secret);
    }

    private String getTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

}
