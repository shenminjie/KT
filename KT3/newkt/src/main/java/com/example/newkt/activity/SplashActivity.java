package com.example.newkt.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.example.newkt.R;

public class SplashActivity extends AppCompatActivity {
    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);
    /*    //判断是否联网
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info==null){
            Toast.makeText(getBaseContext(), "无网络连接，请连接网络", Toast.LENGTH_SHORT).show();
        }*/
        //给闪屏页设置渐入的动画效果
        AlphaAnimation animation=new AlphaAnimation(0.1f,1.0f);
        //设置动画时常1秒
        animation.setDuration(1000);
        //开启动画
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //跳转到引导页
                SharedPreferences preferences = getSharedPreferences("args", Context.MODE_PRIVATE);
                boolean isGuid = preferences.getBoolean("isGuid", false);
                intent=new Intent(SplashActivity.this,GuideActivity.class);
                if(isGuid){
                    intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    intent=new Intent(SplashActivity.this,GuideActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        intent=null;
    }
}
