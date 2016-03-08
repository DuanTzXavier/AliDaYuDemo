package com.tomduan.bigfishdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.tomduan.bigfishdemo.MD5.ModelToMap;
import com.tomduan.bigfishdemo.MD5.Sign;
import com.tomduan.bigfishdemo.Model.Result;
import com.tomduan.bigfishdemo.Model.Send;
import com.tomduan.bigfishdemo.Model.rest.RestRepo;
import com.tomduan.bigfishdemo.Usecase.SendUsecase;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textv)
    TextView textView;

    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        map.put("method", "taobao.item.seller.get");
        map.put("app_key", "12345678");
        map.put("session", "test");
        map.put("timestamp", "2016-01-01 12:00:00");
        map.put("format", "json");
        map.put("v", "2.0");
        map.put("sign_method", "md5");
        map.put("fields", "num_iid,title,nick,price,num");
        map.put("num_iid", "11223344");

        try {
            textView.setText(Sign.signTopRequest(map, "helloworld", "md5"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

//        textView.setText(map.toString());



    }

    private class SendMSG implements Runnable{

        @Override
        public void run() {
            try {
                sendMSM();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMSM() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23321327", "a26e632f7c87e4b216692788ebce03a7");
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend("12345");
        request.setSmsType("normal");
        request.setSmsFreeSignName("阿里大鱼");
        request.setSmsParamString("");
        request.setRecNum("18817313209");
        request.setSmsTemplateCode("SMS_5215124");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(request);
        Log.i("aaaaaaa",rsp.getBody());
    }

    @OnClick(R.id.click)
    public void send(){

        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        String sms_param = "{\"code\":\"1234\",\"product\":\"alidayu\"}";

        Send send = new Send(
                "12345",
                "normal", "注册验证", "18817313209",
                "SMS_5215121", "alibaba.aliqin.fc.sms.num.send", "23321327", str, "2.0", "md5", "json", sms_param);
        String sign = "";


        Map<String,String> map = new HashMap<>();
        try {
            map = new ModelToMap().toMap(send);
            sign = Sign.signTopRequest(map, "55c49bf4000c91d2f50e1aa8203d8fca", "md5");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        send.setSign(sign);
        map.put("sign", sign);

//
        new SendUsecase(new RestRepo(), map).execute().subscribe(
                response -> manage(response),
                throwable -> manageError(throwable)
        );
    }

    private void manageError(Throwable throwable) {
        textView.setText(throwable.getMessage());
        Log.i("loggg", throwable.getMessage());
        throwable.printStackTrace();
    }

    private void manage(Response<Result> response) {
        if (response.isSuccess()){
            textView.setText(response.body().toString());
        }
    }
}
