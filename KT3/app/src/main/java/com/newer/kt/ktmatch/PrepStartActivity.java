package com.newer.kt.ktmatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.newer.kt.R;
import com.newer.kt.record.TakePicActivity;

public class PrepStartActivity extends TakePicActivity {
    public static void invoke(Context ctx) {
        Intent intent = new Intent(ctx, PrepStartActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kt_prep);
//        findViewById(R.id.choose1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(),MathChooseActivity.class));
//            }
//        });
//        findViewById(R.id.choose2).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        findViewById(R.id.choose3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
