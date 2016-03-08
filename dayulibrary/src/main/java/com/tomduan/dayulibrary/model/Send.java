package com.tomduan.dayulibrary.model;

/**
 * Created by tomduan on 16-3-7.
 */
public class Send {
    private String extend;
    private String smstype;
    private String sms_free_sign_name;
    private String rec_num;
    private String sms_template_code;
    private String method;
    private String app_key;
    private String timestamp;
    private String v;
    private String sign_method;
    private String sign;
    private String format;
    private String sms_param;

    public Send(
            String extend, String smstype, String sms_free_sign_name,
            String rec_num, String sms_template_code, String method,
            String app_key, String timestamp, String v, String sign_method,
            String format, String sms_param) {
        this.extend = extend;
        this.smstype = smstype;
        this.sms_free_sign_name = sms_free_sign_name;
        this.rec_num = rec_num;
        this.sms_template_code = sms_template_code;
        this.method = method;
        this.app_key = app_key;
        this.timestamp = timestamp;
        this.v = v;
        this.sign_method = sign_method;
        this.format = format;
        this.sms_param = sms_param;
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

    public String getExtend() {
        return extend;
    }

    public String getSmstype() {
        return smstype;
    }

    public String getSms_free_sign_name() {
        return sms_free_sign_name;
    }

    public String getRec_num() {
        return rec_num;
    }

    public String getSms_template_code() {
        return sms_template_code;
    }

    public String getExtendName() {
        return "extend";
    }

    public String getSmstypeName() {
        return "sms_type";
    }

    public String getSms_free_sign_nameName() {
        return "sms_free_sign_name";
    }

    public String getRec_numName() {
        return "rec_num";
    }

    public String getSms_template_codeName() {
        return "sms_template_code";
    }

    public String getFormat() {
        return format;
    }

    public String getFormatName(){
        return "format";
    }

    public String getSms_paramName() {
        return "sms_param";
    }

    public String getSms_param() {
        return sms_param;
    }
}
