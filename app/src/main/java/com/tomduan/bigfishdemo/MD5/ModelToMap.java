package com.tomduan.bigfishdemo.MD5;

import com.tomduan.bigfishdemo.Model.Send;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomduan on 16-3-7.
 */
public class ModelToMap {
    private Map<String, String> map = new HashMap<>();

    public Map<String, String> toMap(Send send){
        map.put(send.getExtendName(), send.getExtend());
        map.put(send.getRec_numName(), send.getRec_num());
        map.put(send.getSms_free_sign_nameName(), send.getSms_free_sign_name());
        map.put(send.getSms_template_codeName(), send.getSms_template_code());
        map.put(send.getSmstypeName(), send.getSmstype());
        map.put(send.getApp_keyName(), send.getApp_key());
        map.put(send.getMethodName(), send.getMethod());
        map.put(send.getSign_methodName(), send.getSign_method());
        map.put(send.getTimestampName(), send.getTimestamp());
        map.put(send.getVName(), send.getV());
        map.put(send.getFormatName(), send.getFormat());
        map.put(send.getSms_paramName(), send.getSms_param());
        return map;
    }
}
