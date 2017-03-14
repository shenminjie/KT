package com.newer.kt.Refactor.ui.Fragment.Main.PhotoTake;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.newer.kt.R;

public class MainActivity extends AppCompatActivity {

    private ImageView button;
    private ImageView imageView;
    private ImageView image_vs_item_back;
    String schoolAvatar;
    int valueAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        initView();
        initDate();
        initOnclick();


    }

    private void initOnclick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUtils.showImagePickDialog(MainActivity.this);
            }
        });
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initDate() {
        Intent intent=getIntent();
        if(intent!=null){
            schoolAvatar=intent.getStringExtra("getAvatar");
            valueAvatar=Integer.valueOf(schoolAvatar);
            imageView.setBackgroundResource(valueAvatar);
        }
        System.out.println(valueAvatar+"GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

    }

    private void initView() {
        button = (ImageView) findViewById(R.id.iv_xiugai);
        imageView = (ImageView) findViewById(R.id.imageView);
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_FROM_ALBUM: {

                if (resultCode == RESULT_CANCELED) {   //取消操作
                    return;
                }

                Uri imageUri = data.getData();
                ImageUtils.copyImageUri(this, imageUri);
                ImageUtils.cropImageUri(this, ImageUtils.getCurrentUri(), 200, 200);
                break;
            }
            case ImageUtils.REQUEST_CODE_FROM_CAMERA: {

                if (resultCode == RESULT_CANCELED) {     //取消操作
                    ImageUtils.deleteImageUri(this, ImageUtils.getCurrentUri());   //删除Uri
                }

                ImageUtils.cropImageUri(this, ImageUtils.getCurrentUri(), 200, 200);
                break;
            }
            case ImageUtils.REQUEST_CODE_CROP: {

                if (resultCode == RESULT_CANCELED) {     //取消操作
                    return;
                }

                Uri imageUri = ImageUtils.getCurrentUri();
                if (imageUri != null) {
                    imageView.setImageURI(imageUri);
                }
                break;
            }
            default:
                break;
        }
    }


}
