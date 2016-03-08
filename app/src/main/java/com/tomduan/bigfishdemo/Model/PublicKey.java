package com.tomduan.bigfishdemo.Model;

/**
 * Created by tomduan on 16-3-7.
 */
public class PublicKey {
    private String method;
    private String app_key;
    private String timestamp;
    private String v;
    private String sign_method;
    private String sign;

    public PublicKey(String method, String app_key, String timestamp, String v, String sign_method) {
        this.method = method;
        this.app_key = app_key;
        this.timestamp = timestamp;
        this.v = v;
        this.sign_method = sign_method;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMethodName() {
        return "method";
    }

    public String getApp_keyName() {
        return "app_key";
    }

    public String getTimestampName() {
        return "timestamp";
    }

    public String getVName() {
        return "v";
    }

    public String getSign_methodName() {
        return "sign_method";
    }

    public String getMethod() {
        return method;
    }

    public String getApp_key() {
        return app_key;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getV() {
        return v;
    }

    public String getSign_method() {
        return sign_method;
    }

    public String getSign() {
        return sign;
    }
}
