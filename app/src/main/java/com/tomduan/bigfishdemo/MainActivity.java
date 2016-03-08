package com.tomduan.bigfishdemo;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.tomduan.dayulibrary.domain.SendUsecase;
import com.tomduan.dayulibrary.encryption.Encryption;
import com.tomduan.dayulibrary.model.Result;
import com.tomduan.dayulibrary.model.rest.RestRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textv)
    TextView textView;


    static TextView textView1;
    private String code;

    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView1 = (TextView) findViewById(R.id.textv1);
    }

    @OnClick(R.id.click)
    public void send(){
        getMSG();
        textView.setText("随机验证码为： " + code);
    }



    private void getMSG() {
        Encryption encryption = new Encryption(ALiBigFish.APP_KEY, ALiBigFish.APP_SECRET);
        Map<String, String> map = encryption
                .setEec_num("18817313209")
                .setSms_free_sign_name("注册验证")
                .setCodeAndProduct(getCode(), "鲑鱼科技")
                .setSms_template_code("SMS_5215121")
                .bulid().addSign();
        new SendUsecase(new RestRepo(), map).execute().subscribe(
                response -> manage(response),
                throwable -> manageError(throwable)
        );
    }

    private String getCode() {
        code = String.valueOf((int)((Math.random()*9+1)*100000));
        return code;
    }

    private void manageError(Throwable throwable) {
//        textView.setText(throwable.getMessage());
        Log.i("loggg", throwable.getMessage());
        throwable.printStackTrace();
    }

    private void manage(Response<Result> response) {
//        if (response.isSuccess()){
//            textView.setText(response.body().toString());
//        }
    }

    /**
     * 从短信字符窜提取验证码
     * @param body 短信内容
     * @param YZMLENGTH  验证码的长度 一般6位或者4位
     * @return 接取出来的验证码
     */
    public static void getyzm(String body, int YZMLENGTH) {
        // 首先([a-zA-Z0-9]{YZMLENGTH})是得到一个连续的六位数字字母组合
        // (?<![a-zA-Z0-9])负向断言([0-9]{YZMLENGTH})前面不能有数字
        // (?![a-zA-Z0-9])断言([0-9]{YZMLENGTH})后面不能有数字出现
        Pattern p = Pattern
                .compile("(?<![a-zA-Z0-9])([a-zA-Z0-9]{" + YZMLENGTH + "})(?![a-zA-Z0-9])");
        Matcher m = p.matcher(body);
        if (m.find()) {
            System.out.println(m.group());
        }
        setCode(m.group(0));
    }

    private static void setCode(String code){
        textView1.setText("短信中的验证码是： "+ code);
    }
}
