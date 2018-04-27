package com.zugogo.app.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.common.collect.Lists;
import com.sunfusheng.marqueeview.MarqueeView;
import com.zugogo.app.R;

import java.util.List;

public class MarqueeSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee_sample);

        MarqueeView marqueeView = findViewById(R.id.marqueeView);
//        marqueeView.setTextDirection();
        List<String> info = Lists.newArrayList();
        info.add("1.大家好，我是孫福生。");
        info.add("2.歡迎大家關注我哦！");
        info.add("3. GitHub帳號：sfsheng0322");
        info.add("4.新浪微博：孫福生微博");
        info.add("5。個人博客：sunfusheng.com");
        info.add("6.微信公眾號：孫福生");
        marqueeView.startWithList(info);

        // 在代碼裡設置自己的動畫
        marqueeView.startWithList(info, R.anim.anim_right_in, R.anim.anim_left_out);

    }

}
