package com.newer.kt;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by win7 on 2017/2/15.
 */

public class MyCountDownTimer extends CountDownTimer {
    public TextView tv;

    public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long l) {
        tv.setText(new SimpleDateFormat("mm;ss").format(l));
    }

    @Override
    public void onFinish() {

    }
}
