package com.example.newkt.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.newkt.R;
import com.example.newkt.fragment.BangFragment;
import com.example.newkt.fragment.CommunityFragment;
import com.example.newkt.fragment.CompetitionFragment;
import com.example.newkt.fragment.PeiXunFragment;
import com.example.newkt.fragment.SheZhiFragment;

public class MainActivity extends AppCompatActivity {
    Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void change(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mian_fragment,fragment)
                .commit();
    }


    public void mianClick(View view){
        switch (view.getId()){
            case R.id.mian_button1:
                fragment=new PeiXunFragment();
                break;
            case R.id.mian_button2:
                fragment=new CompetitionFragment();
                break;
            case R.id.mian_button3:
                fragment=new BangFragment();
                break;
            case R.id.mian_button4:
                fragment=new CommunityFragment();
                break;
            case R.id.mian_button5:
                fragment=new SheZhiFragment();
                break;
        }
        change(fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragment=null;
    }
}
