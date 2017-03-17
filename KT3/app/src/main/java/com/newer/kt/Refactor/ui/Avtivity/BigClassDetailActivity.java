package com.newer.kt.Refactor.ui.Avtivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baseproject.utils.Logger;
import com.frame.app.base.activity.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.netease.neliveplayer.NELivePlayer;
import com.newer.kt.NEVideoView;
import com.newer.kt.NeActivity;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.Entitiy.Action;
import com.newer.kt.Refactor.Entitiy.BigClassRoom;
import com.newer.kt.Refactor.Entitiy.Skill;
import com.newer.kt.Refactor.ui.MyGridView;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.Refactor.view.MyLinearLayoutManager;
import com.newer.kt.Refactor.view.ReboundScrollView;
import com.newer.kt.adapter.BigClassGifAdapter;
import com.newer.kt.myClass.DownLoaderTask;
import com.newer.kt.myClass.PixelUtils;
import com.newer.kt.myClass.ZipExtractorTask;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.widget.MediaController;

/**
 * Created by leo on 16/11/10.
 */

public class BigClassDetailActivity extends BaseActivity {
    @Bind(R.id.gridView)
    MyGridView mGridView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private MyLinearLayoutManager linearLayoutManager;


    private static final String TAG = "BigClassDetailActivity";


    private RelativeLayout head1;
    private NEVideoView full_holder;
    private TextView tv_guankan;

    private String shool_big_classroom_id;//大课间id
    private BigClassRoom  bigClassRoom = new BigClassRoom();
    private String vidioLocalPath; //视频本地地址
    private boolean isFind;//本地视频是否已存在
    private boolean initalized = false;

    private MediaController mController;
    private FrameLayout mFlVideoGroup;
    //当前是否为全屏
    private Boolean mIsFullScreen = false;
    /*需要隐藏显示的View*/
    private ArrayList<View> mViews;
    private RelativeLayout titleBar;
    private ReboundScrollView sv_main;
    private TextView mTvStartClass;


    //技能列表
    private List<Skill> skills = new ArrayList<Skill>();
    private SkillAdapter skillAdapter;

    //action列表
    private List<Action> actions = new ArrayList<Action>();
    private BigClassGifAdapter actionAdapter;
    private FrameLayout fl_video_group;

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_xunlian_bigclass);
        ButterKnife.bind(this);
        mFlVideoGroup = (FrameLayout) findViewById(R.id.fl_video_group);
        mController = new MediaController(this, true, mFlVideoGroup);
        //上来先隐藏controller
        mController.setVisibility(View.GONE);

        titleBar = ((RelativeLayout) this.findViewById(R.id.titlebar));
        sv_main = (ReboundScrollView)findViewById(R.id.sv_main);
        mTvStartClass = (TextView) findViewById(R.id.start_class);

        mViews = new ArrayList<>();
        mViews.add(titleBar);
        mViews.add(sv_main);
        mViews.add(mTvStartClass);


        head1 = ((RelativeLayout) this.findViewById(R.id.head1));

        head1.setFocusable(true);
        head1.setFocusableInTouchMode(true);
        head1.requestFocus();




        full_holder = ((NEVideoView) this.findViewById(R.id.full_holder));

        fl_video_group = ((FrameLayout) findViewById(R.id.fl_video_group));
        /*if(!fullscreen){//设置RelativeLayout的全屏模式
            RelativeLayout.LayoutParams layoutParams=
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            full_holder.setLayoutParams(layoutParams);

            fullscreen = true;//改变全屏/窗口的标记
        }else{//设置RelativeLayout的窗口模式
            RelativeLayout.LayoutParams lp=new  RelativeLayout.LayoutParams(320,240);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            full_holder.setLayoutParams(lp);
            fullscreen = false;//改变全屏/窗口的标记
        }*/

        //head1.setVisibility(View.GONE);

        mGridView = (MyGridView)findViewById(R.id.gridView);

        tv_guankan = ((TextView) findViewById(R.id.tv_guankan));
        tv_guankan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFind){
                    goPlay();
                }else{
                    tv_guankan.setClickable(false);
                    doDownLoadWork();
                }


            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


        // 通过上个页面传递过来的Intent获取播放参数
        getIntentData(getIntent());

        skillAdapter = new SkillAdapter(skills,this);
        mGridView.setAdapter(skillAdapter);

        getSchoolBigClassDetail();
        actionAdapter =new BigClassGifAdapter(actions, getThis(), linearLayoutManager);
        mRecyclerView.setLayoutManager(linearLayoutManager = new MyLinearLayoutManager(this));
        mRecyclerView.setAdapter(actionAdapter);

    }

    @OnClick(R.id.image_musci)
    public void music() {

    }


    @OnClick(R.id.image_setting)
    public void setting() {

    }


    //退出当前Activity
    public void doBack(View view) {
        finish();
    }

    @OnClick(R.id.start_class)
    public void tart() {
        Intent intent = new Intent(getThis(),BigClassChooseActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() { // android系统调用
        Logger.d("sgh", "onBackPressed before super");
        super.onBackPressed();
        Logger.d("sgh", "onBackPressed");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("===========================onSaveInstanceState");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        System.out.println("===========================onConfigurationChanged");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            mIsFullScreen = true;
            //去掉系统通知栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            hideViews(true);
            //调整mFlVideoGroup布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            mFlVideoGroup.setLayoutParams(params);
            //原视频大小
//            public static final int VIDEO_LAYOUT_ORIGIN = 0;
            //最优选择，由于比例问题还是会离屏幕边缘有一点间距，所以最好把父View的背景设置为黑色会好一点
//            public static final int VIDEO_LAYOUT_SCALE = 1;
            //拉伸，可能导致变形
//            public static final int VIDEO_LAYOUT_STRETCH = 2;
            //会放大可能超出屏幕
//            public static final int VIDEO_LAYOUT_ZOOM = 3;
            //效果还是竖屏大小（字面意思是填充父View）
//            public static final int VIDEO_LAYOUT_FIT_PARENT = 4;
//            full_holder.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        } else {
            mIsFullScreen = false;
            /*清除flag,恢复显示系统状态栏*/
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            hideViews(false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.MATCH_PARENT,
                    PixelUtils.dip2px(this,220));
            mFlVideoGroup.setLayoutParams(params);
        }
    }

    public void hideViews(boolean hide) {
        if (hide) {
            for (int i = 0; i < mViews.size(); i++) {
                mViews.get(i).setVisibility(View.GONE);
            }
        } else {
            for (int i = 0; i < mViews.size(); i++) {
                mViews.get(i).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //没有布局中没有设置返回键，只能响应硬件返回按钮，你可根据自己的意愿添加一个。若全屏就切换为小屏
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mIsFullScreen) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mController.setFullScreenIconState(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLowMemory() { // android系统调用
        super.onLowMemory();
    }

    @Override
    public void onPause() {
        System.out.println("=====================================onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        System.out.println("=====================================onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);

        // 通过Intent获取播放需要的相关参数
        getIntentData(intent);



        // 进行播放
//        goPlay();
    }

    /**
     * 获取上个页面传递过来的数据
     */
    private void getIntentData(Intent intent) {

        if (intent != null) {
            shool_big_classroom_id = intent.getStringExtra("shool_big_classroom_id");
        }

    }


    //大课间列表  http://api.ktfootball.com/school_big_class_rooms/detail
    public void getSchoolBigClassDetail() {


        String url = Constants.KTHOST + "school_big_class_rooms/detail";
        RequestParams p = new RequestParams(url);
//        p.addQueryStringParameter("authenticity_token", "K9MpaPMdj0jij2m149sL1a7TcYrWXmg5GLrAJDCNBx8");
        p.addQueryStringParameter("authenticity_token", MD5.getToken(url));
        p.addQueryStringParameter("shool_big_classroom_id", shool_big_classroom_id);
        x.http().get(p, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                showDialogToast(result);
                Gson gson = new Gson();
                //1. 获得 解析者
                JsonParser parser = new JsonParser();

                //2. 获得 根节点元素
                JsonElement element = parser.parse(result);

                //3. 根据 文档判断根节点属于 什么类型的 Gson节点对象
                JsonObject root = element.getAsJsonObject();

                //4. 取得 节点 下 的某个节点的 value
                JsonPrimitive flagjson = root.getAsJsonPrimitive("response");
                String flag = flagjson.getAsString();

                if("success".equals(flag)){
                    bigClassRoom = gson.fromJson(result,BigClassRoom.class);

                    vidioLocalPath = getCacheDir() + "/"+bigClassRoom.getName()+"/"+bigClassRoom.getName()+".mp4";
                    if(new File(vidioLocalPath).exists()){
                        isFind = true;
                        tv_guankan.setText("播放");

                    }

                    skillAdapter.refresh(bigClassRoom.getSkills());
                    actionAdapter.refresh(bigClassRoom);



                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {


            }
        });

    }

    public void initVidioView(){
        //验证是否硬件支持
//        if (!LibsChecker.checkVitamioLibs(this)) {
//            return;
//        }
        full_holder.set(vidioLocalPath);
//        vv.setVideoPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/测试.mp4");

        full_holder.setOnCompletionListener(new NELivePlayer.OnCompletionListener() {

            @Override
            public void onCompletion(NELivePlayer neLivePlayer) {
                full_holder.stopPlayback();
                full_holder.setVisibility(View.GONE);
                fl_video_group.setVisibility(View.GONE);
                head1.setVisibility(View.VISIBLE);
                tv_guankan.setText("重播");
                hideViews(false);

                if(mIsFullScreen){
                    mIsFullScreen = false;
                    BigClassDetailActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }

        });
    }

    public void goPlay() {

            if(!initalized){
//                initVidioView();
                initalized = true;
            }

//            head1.setVisibility(View.GONE);
//
//            full_holder.setVisibility(View.VISIBLE);
//            fl_video_group.setVisibility(View.VISIBLE);
            startActivity(new Intent(getBaseContext(),NeActivity.class).putExtra("path",vidioLocalPath));

//            full_holder.play();


    }

//    private void showDownLoadDialog(){
//        new AlertDialog.Builder(this).setTitle("确认")
//                .setMessage("是否下载？")
//                .setPositiveButton("是", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        Log.d(TAG, "onClick 1 = "+which);
//                        doDownLoadWork();
//                    }
//                })
//                .setNegativeButton("否", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        Log.d(TAG, "onClick 2 = "+which);
//                    }
//                })
//                .show();
//    }

//    public void showUnzipDialog(){
//        new AlertDialog.Builder(this).setTitle("确认")
//                .setMessage("是否解压？")
//                .setPositiveButton("是", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        Log.d(TAG, "onClick 1 = "+which);
//                        doZipExtractorWork();
//                    }
//                })
//                .setNegativeButton("否", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        Log.d(TAG, "onClick 2 = "+which);
//                    }
//                })
//                .show();
//    }

    public void doZipExtractorWork(){
        //ZipExtractorTask task = new ZipExtractorTask("/storage/usb3/system.zip", "/storage/emulated/legacy/", this, true);
        ZipExtractorTask task = new ZipExtractorTask(getCacheDir()+"/"+bigClassRoom.getName()+".zip", getCacheDir()+"", this, true);
        task.execute();
    }

    private void doDownLoadWork(){
        DownLoaderTask task = new DownLoaderTask(bigClassRoom.getVideo_url(), getCacheDir()+"", BigClassDetailActivity.this);
        //DownLoaderTask task = new DownLoaderTask("http://192.168.9.155/johnny/test.h264", getCacheDir().getAbsolutePath()+"/", this);
        task.execute();
    }

    public void downloadProgress(Integer progress){
        tv_guankan.setText(progress+"%");
    }

    public void downloadandziped(){
        tv_guankan.setText("播放");
        isFind = true;
        tv_guankan.setClickable(true);
    }

}



class SkillAdapter extends BaseAdapter{

    private Context context;
    private List<Skill> skills;

    public SkillAdapter(List<Skill> skills,Context context){
        this.skills = skills;
        this.context = context;
    }

        @Override
        public int getCount() {
        return skills.size();
    }

        @Override
        public Object getItem(int position) {
        return skills.get(position);
    }

        @Override
        public long getItemId(int position) {
        return position;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder1();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_big_class1, null);
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.mBg = (ImageView) convertView.findViewById(R.id.image_bg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder1) convertView.getTag();
        }
        viewHolder.mTitle.setText(skills.get(position).getName());
        return convertView;
    }

    public void refresh(List<Skill> skills){
        this.skills.clear();
        this.skills.addAll(skills);
        this.notifyDataSetChanged();

    }

        class ViewHolder1 {
            ImageView mBg;
            TextView mTitle;
        }
    }

