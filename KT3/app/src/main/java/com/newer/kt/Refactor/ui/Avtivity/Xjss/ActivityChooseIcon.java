package com.newer.kt.Refactor.ui.Avtivity.Xjss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


import com.newer.kt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bajieaichirou on 17/3/4.
 */
public class ActivityChooseIcon extends Activity {

    @Bind(R.id.choose_camera)
    TextView mCameraTxt;

    @Bind(R.id.choose_picture)
    TextView mPictureTxt;

    @Bind(R.id.choose_cancel)
    TextView mCancelTxt;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_choose_icon);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.choose_camera, R.id.choose_picture, R.id.choose_cancel})
    public void OnClick(View view){
        intent = new Intent();
        switch (view.getId()){
            case R.id.choose_camera:
                intent.putExtra(Constant.KEY_CHOOSE_ICON_TYPE, Constant.KEY_CHOOSE_TYPE_CAMERA);
                setResult(Constant.CODE_CHOOSE_ICON, intent);
                this.finish();
                break;
            case R.id.choose_picture:
                intent.putExtra(Constant.KEY_CHOOSE_ICON_TYPE, Constant.KEY_CHOOSE_TYPE_PICTURE);
                setResult(Constant.CODE_CHOOSE_ICON, intent);
                this.finish();
                break;
            case R.id.choose_cancel:
                intent.putExtra(Constant.KEY_CHOOSE_ICON_TYPE, "");
                setResult(Constant.CODE_CHOOSE_ICON, intent);
                this.finish();
                break;
        }
    }
}
