//package shengchengerweima;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.uuzuche.lib_zxing.activity.CodeUtils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//public class MainActivity extends AppCompatActivity {
//    public EditText editText = null;
//    public Button button = null;
//    public Button button1 = null;
//    public ImageView imageView = null;
//
//    public Bitmap mBitmap = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(com.hj.asus.shengchengerweima.R.layout.activity_main);
//        initView();
//    }
//
//    /**
//     * 初始化组件
//     */
//    private void initView() {
//
//        editText = (EditText) findViewById(com.hj.asus.shengchengerweima.R.id.edit_content);
//        button = (Button) findViewById(com.hj.asus.shengchengerweima.R.id.button_content);
//        button1 = (Button) findViewById(com.hj.asus.shengchengerweima.R.id.button1_content);
//        imageView = (ImageView) findViewById(com.hj.asus.shengchengerweima.R.id.image_content);
//
//        /**
//         * 生成二维码图片
//         */
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String textContent = editText.getText().toString();
//                if (TextUtils.isEmpty(textContent)) {
//                    Toast.makeText(MainActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                editText.setText("");
//                mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), com.hj.asus.shengchengerweima.R.mipmap.ic_launcher));
//                imageView.setImageBitmap(mBitmap);
//            }
//        });
//
//        /**
//         * 生成不带logo的二维码图片
//         */
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String textContent = editText.getText().toString();
//                if (TextUtils.isEmpty(textContent)) {
//                    Toast.makeText(MainActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                editText.setText("");
//                mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
//                saveBitmaptofile(mBitmap,getPhotoFileName());
//                imageView.setImageBitmap(mBitmap);
//            }
//        });
//    }
//    static boolean saveBitmaptofile(Bitmap bmp,String filename){
//        Bitmap.CompressFormat format= Bitmap.CompressFormat.JPEG;
//        int quality = 100;
//        OutputStream stream = null;
//        try {
//            File cacheDir = new File("/data/data/com.hj.asus.shengchengerweima/picture/");//设置目录参数
//            if(cacheDir.exists()){
//
//            }else{
//                cacheDir.mkdirs();//新建目录
//            }
//
//            stream = new FileOutputStream("/data/data/com.hj.asus.shengchengerweima/picture/"+"13234562333.png");
//        } catch (FileNotFoundException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return bmp.compress(format, quality, stream);
//    }
//    // 使用系统当前日期加以调整作为照片的名称
//    private String getPhotoFileName() {
//        Date date = new Date(System.currentTimeMillis());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//        System.out.println("============" + UUID.randomUUID());
//        return sdf.format(date) + "_" + UUID.randomUUID() + ".png";
//    }
//
//}