package com.newer.kt.Refactor.ui.Fragment.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import shengchengerweima.CamScanActivity;

/**
 * Created by litli on 2017/3/9.
 */

public class ScanAddStu extends CamScanActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invokeCap();
    }

    @Override
    public void recvCode(String result) {
        super.recvCode(result);
        startActivity(new Intent(getBaseContext(),Student_Info.class).putExtra("code",result));
    }
}
