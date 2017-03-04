package com.newer.kt.Refactor.ui.Avtivity.Xjss;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newer.kt.R;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bajieaichirou on 17/3/3.
 */
public class ActivityNewContest extends Activity {

    @Bind(R.id.contest_club_layout)
    AutoRelativeLayout mClubLayout;

    @Bind(R.id.contest_name_layout)
    AutoRelativeLayout mNameLayout;

    @Bind(R.id.contest_address_layout)
    AutoRelativeLayout mAddressLayout;

    @Bind(R.id.contest_start_time_layout)
    AutoRelativeLayout mStartTimeLayout;

    @Bind(R.id.contest_end_time_layout)
    AutoRelativeLayout mEndTimeLayout;

    @Bind(R.id.contest_introduce_layout)
    AutoRelativeLayout mIntroduceLayout;

    @Bind(R.id.contest_club_txt)
    TextView mClubTxt;

    @Bind(R.id.contest_name_txt)
    TextView mNameTxt;

    @Bind(R.id.contest_address_txt)
    TextView mAddressTxt;

    @Bind(R.id.contest_start_time_txt)
    TextView mStartTimeTxt;

    @Bind(R.id.contest_end_time_txt)
    TextView mEndTimeTxt;

    @Bind(R.id.contest_icon)
    ImageView mIcon;

    private Intent intent;
    private Bitmap iconBitMap;
    private String chooseTimeType;
    private final String ICON_NAME = "ICON.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contest);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.contest_club_layout, R.id.contest_start_time_layout,
            R.id.contest_end_time_layout, R.id.contest_icon})
    public void OnClick(View view){
        intent = new Intent();
        switch (view.getId()){
            case R.id.contest_icon:
                intent.setClass(this, ActivityChooseIcon.class);
                startActivityForResult(intent, Constant.CODE_CHOOSE_ICON);
                break;
            case R.id.contest_club_layout:
                intent.setClass(this, ActivityChooseClub.class);
                startActivityForResult(intent, Constant.CODE_CHOOSE_CLUB);
                break;
            case R.id.contest_start_time_layout:
                chooseTimeType = Constant.KEY_CHOOSE_START_TIME;
                intent.setClass(this, ActivityChooseTime.class);
                intent.putExtra(Constant.KEY_CHOOSE_TIME_TYPE, Constant.KEY_CHOOSE_START_TIME);
                startActivityForResult(intent,Constant.CODE_CHOOSE_TIME);
                break;
            case R.id.contest_end_time_layout:
                chooseTimeType = Constant.KEY_CHOOSE_END_TIME;
                intent.setClass(this, ActivityChooseTime.class);
                intent.putExtra(Constant.KEY_CHOOSE_TIME_TYPE, Constant.KEY_CHOOSE_END_TIME);
                startActivityForResult(intent,Constant.CODE_CHOOSE_TIME);
                break;
        }
    }

    private void chooseCamera(){
        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (AppUtil.hasSdcard()) {
            in.putExtra("return-data", false);
            in.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            in.putExtra("noFaceDetection", true);
            in.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment.getExternalStorageDirectory(), ICON_NAME)));
        }else{
            Toast.makeText(ActivityNewContest.this, "SD卡不存在，请插入SD卡",
                    Toast.LENGTH_LONG).show();
            return;
        }
        startActivityForResult(in, Constant.CODE_CHOOSE_ICON_CAMERA);
    }

    private void choosePicture(){
        Intent in = new Intent(Intent.ACTION_PICK);
        in.setType("image/*");
        startActivityForResult(in, Constant.CODE_CHOOSE_ICON_PICTURE);
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪 crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("noFaceDetection", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, Constant.CODE_CHOOSE_ICON_ZOOM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String temp;
       if (resultCode == Constant.CODE_CHOOSE_CLUB){
            temp = data.getStringExtra(Constant.KEY_CHOOSE_CLUB_NAME);
            if (!temp.isEmpty()){
                mClubTxt.setText(temp);
            }
        }else if (resultCode == Constant.CODE_CHOOSE_TIME){
            temp = data.getStringExtra(Constant.KEY_CHOOSE_TIME);
            if (!temp.isEmpty()){
                if (chooseTimeType.equals(Constant.KEY_CHOOSE_START_TIME)){
                    mStartTimeTxt.setText(temp);
                }else{
                    mEndTimeTxt.setText(temp);
                }
            }
        }else if (resultCode == Constant.CODE_CHOOSE_ICON){
            temp = data.getStringExtra(Constant.KEY_CHOOSE_ICON_TYPE);
            if (!temp.isEmpty()){
                if (temp.equals(Constant.KEY_CHOOSE_TYPE_CAMERA)){//选择照相机
                    chooseCamera();
                }else if (temp.equals(Constant.KEY_CHOOSE_TYPE_PICTURE)){//选择图册
                    choosePicture();
                }
            }
        }else if (requestCode == Constant.CODE_CHOOSE_ICON_CAMERA){//相机返回
            startPhotoZoom(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), ICON_NAME)));
        }else if ((data != null) &&(requestCode == Constant.CODE_CHOOSE_ICON_PICTURE)){//图册返回
            startPhotoZoom(data.getData());
        }else if ((data != null) && (requestCode == Constant.CODE_CHOOSE_ICON_ZOOM)){//裁剪完后
            Bundle extras = data.getExtras();
            if (extras != null) {
                iconBitMap = AppUtil.toRoundBitmap((Bitmap)extras.getParcelable(Constant.KEY_DATA));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                iconBitMap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                mIcon.setImageBitmap(iconBitMap);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iconBitMap != null && (!iconBitMap.isRecycled())){
            iconBitMap.recycle();
        }
    }
}
