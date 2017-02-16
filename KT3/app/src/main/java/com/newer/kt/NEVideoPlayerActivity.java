//package com.newer.kt;
//
//import android.animation.AnimatorSet;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.content.res.Configuration;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Display;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.netease.neliveplayer.NELivePlayer;
//import com.netease.neliveplayer.NEMediaPlayer;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.TreeMap;
//
//public class NEVideoPlayerActivity extends Activity {
//    public final static String TAG = "NEVideoPlayerActivity";
//    public NEVideoView mVideoView;  //用于画面显示
//    private View mBuffer; //用于指示缓冲状态
//
//    private Boolean userHasFansRoom;
//    private int watchUserCount;
//    private boolean FullScreenMode = false;
//
//
//    public static final int NELP_LOG_UNKNOWN = 0; //!< log输出模式：输出详细
//    public static final int NELP_LOG_DEFAULT = 1; //!< log输出模式：输出详细
//    public static final int NELP_LOG_VERBOSE = 2; //!< log输出模式：输出详细
//    public static final int NELP_LOG_DEBUG = 3; //!< log输出模式：输出调试信息
//    public static final int NELP_LOG_INFO = 4; //!< log输出模式：输出标准信息
//    public static final int NELP_LOG_WARN = 5; //!< log输出模式：输出警告
//    public static final int NELP_LOG_ERROR = 6; //!< log输出模式：输出错误
//    public static final int NELP_LOG_FATAL = 7; //!< log输出模式：一些错误信息，如头文件找不到，非法参数使用
//    public static final int NELP_LOG_SILENT = 8; //!< log输出模式：不输出
//
//    private String mVideoPath; //文件路径
//    private String mDecodeType;//解码类型，硬解或软解
//    private String mMediaType; //媒体类型
//    private boolean mHardware = true;
//    //    private ImageButton mPlayBack;
////	private TextView mFileName; //文件名称
////	private ImageView mAudioRemind; //播音频文件时提示
//    private String mTitle;
//    private Uri mUri;
//    private boolean pauseInBackgroud = true;
//    private RelativeLayout mPlayToolbar;
//
//    private TextView tv_wifi_network;
//    private ViewPager viewPager;
//    private Button back_bt;
//    private RelativeLayout rl_follower, top_show;
//    private ConnectivityManager mConnectivityManager;
//    private NetworkInfo mNetworkInfo;
//    private int networkType;
//    private int rankPosition = 0;
//    private LinearLayout ll_rank;
//    private TextView tv_public;
//    private TextView tv_viewer;
//    private TextView tv_leftBracket;
//    private TextView tv_rightBracket;
//    private TextView tv_viewerNumber;
//    private RelativeLayout rl_viewer;
//    private LinearLayout ll_emoji;
//    //    private ImageView bt_prop_nu;
//    private Button bt_prop_nu;
//    private int propRankPosition = 0;
//    private View rl_bottom;
//    private LinearLayout rl_input;
//    private EditText et_input;
//    private LinearLayout ll_chat;
//    private LinearLayout fl_prop;
//    private ViewPager prop_viewPager;
//    private ImageView prop_hot_image;
//    private ImageView prop_vip_image;
//    private ImageView prop_bag_image;
//    private Button prop_send_button;
//    private FrameLayout fl_emoji_face;
//    private ImageView emoji_face_dark;
//    private ImageView emoji_face_hightlight;
//    private DanmakuView mDanmakuView;
//    private FrameLayout fl_follower_anchor, video_frame;
//    private ImageView btn_concerned;
//    private LinearLayout ll_follower;
//    private ImageView iv_followed;
//    private TextView follower_nu;
//    private String userId;
//    private FrameLayout ll_user_manager;
//    private Button button_close_user_manager;
//    private LinearLayout ll_out;
//    private LinearLayout ll_ban;
//    private boolean out_enable = false;
//    private boolean ban_enable = false;
//    private View ll_back;
//
//    private RoundProgressBar loveIconGeneratePro;
//    private TextView tv_loveIconNumber;
//    private String anchorGuid;
//    Timer timer;
//    int progress = 0;
//    int watchCountTotal;
//
//    private FrameLayout fl_screen_scale;
//    private ImageView video_size_scale_max;
//    private ImageView video_size_scale_min;
//    private FrameLayout fl_talk;
//
//    public TextView tv_viewer_nu;
//    private LinearLayout ll_viewer;
//
//    private LinearLayout anchor_message;
//
////    private TextView viewer;
//
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    private GoogleApiClient client;
//
//    NEMediaPlayer mMediaPlayer;
//    String bigurl = "";
//
//    @Override
//    public void implReadPhoneState() {
//        syncVideoView();
//    }
//
//    Context contet;
//
//    private View activityRootView;
//    //屏幕高度
//    private int screenHeight = 0;
//    //软件盘弹起后所占高度阀值
//    private int keyHeight = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        Log.d(TAG, "on create");
//        super.onCreate(savedInstanceState);
//
//        MainActivity.toroom = false;
//
//
//        setContentView(R.layout.ne_player);
//        anchor_message = (LinearLayout) findViewById(R.id.anchor_message);
//
//        activityRootView = findViewById(R.id.root_layout);
//        //获取屏幕高度
//        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
//        //阀值设置为屏幕高度的1/3
//        keyHeight = screenHeight / 3;
//
//        AlertDialog myDialog = new AlertDialog.Builder(this).create();
//        fl_talk = (FrameLayout) findViewById(R.id.fl_talk);
//        //接收MainActivity传过来的参数
//        Intent intent = getIntent();
//
//        mMediaType = getIntent().getStringExtra("media_type");
//        mDecodeType = getIntent().getStringExtra("decode_type");
//
////        mVideoPath = getIntent().getStringExtra("videoPath");
//        mVideoPath = "/storage/emulated/0/幼儿园大课间.mp4";
//        userHasFansRoom = getIntent().getBooleanExtra("userHasFansRoom", false);
//        watchUserCount = getIntent().getIntExtra("watchUserCount", 0);
//        userId = getIntent().getStringExtra("userId");
//        watchCountTotal = getIntent().getIntExtra("watchCountTotal", 0);
////        watchUserCount = getIntent().getIntExtra("watchUserCount", 8653);
////        userId = getIntent().getStringExtra("userId");
//
////        mMediaType = getIntent().getStringExtra("media_type");
////        mDecodeType = getIntent().getStringExtra("decode_type");
////        mVideoPath = getIntent().getStringExtra("videoPath");
//
//        PublicLayout.loveIconNumber = getIntent().getIntExtra("loveNumber", 0);
//        anchorGuid = getIntent().getStringExtra("anchorGuid");
//        video_frame = (FrameLayout) findViewById(R.id.video_frame);
//
//        publicLayout = new PublicLayout(this);
//        publicLayout.anchorGuid = anchorGuid;
//        top_show = (RelativeLayout) findViewById(R.id.top_show);
//        fl_screen_scale = (FrameLayout) findViewById(R.id.fl_screen_scale);
//        video_size_scale_max = (ImageView) findViewById(R.id.video_size_scale_max);
//        video_size_scale_min = (ImageView) findViewById(R.id.video_size_scale_min);
//        loveIconGeneratePro = (RoundProgressBar) publicLayout.findViewById(R.id.loveIconGeneratePro);
//        tv_loveIconNumber = (TextView) publicLayout.findViewById(R.id.tv_loveIconNumber);
//
//        tv_loveIconNumber.setText("" + PublicLayout.loveIconNumber);
//        back_bt = (Button) findViewById(R.id.ic_back01);
//        rl_follower = (RelativeLayout) findViewById(R.id.rl_follower);
//        tv_wifi_network = (TextView) findViewById(R.id.tv_wifi_network);
//        ll_rank = (LinearLayout) findViewById(R.id.ll_rank);
////        anchor_portrait = (ImageView) findViewById(R.id.anchor_portrait);
//        tv_public = (TextView) findViewById(R.id.tv_public);
//        tv_viewer = (TextView) findViewById(R.id.viewer);
//        tv_rightBracket = (TextView) findViewById(R.id.right_bracket);
//        tv_leftBracket = (TextView) findViewById(R.id.left_bracket);
//        tv_viewerNumber = (TextView) findViewById(R.id.viewer_number);
//        rl_viewer = (RelativeLayout) findViewById(R.id.rl_viewer);
//        ll_chat = (LinearLayout) findViewById(R.id.ll_chat);
//        fl_prop = (LinearLayout) findViewById(R.id.fl_prop);
//        bt_prop_nu = (Button) findViewById(R.id.bt_prop_nu);
//        prop_viewPager = (ViewPager) findViewById(R.id.prop_viewPager);
//        prop_viewPager.setAdapter(new propViewPagerAdapter());
//        prop_viewPager.setCurrentItem(0);
//        prop_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == 2) {
//
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        if (FullScreenMode) {
//            video_size_scale_max.setVisibility(View.INVISIBLE);
//            video_size_scale_min.setVisibility(View.VISIBLE);
//        } else {
//            video_size_scale_max.setVisibility(View.VISIBLE);
//            video_size_scale_min.setVisibility(View.INVISIBLE);
//        }
//        tv_viewer_nu = (TextView) findViewById(R.id.tv_viewer_nu);
//
//        ll_viewer = (LinearLayout) findViewById(R.id.ll_viewer);
//
//        prop_hot_image = (ImageView) findViewById(R.id.prop_hot_image);
//        prop_vip_image = (ImageView) findViewById(R.id.prop_vip_image);
//        prop_bag_image = (ImageView) findViewById(R.id.prop_bag_image);
//        fullscreen_powerImageView = (PowerImageView) findViewById(R.id.fullscreen_powerimageview);
//        fullscreen_powerImageView.setCallback(this);
//        fullscreen_powerImageView.setAutoPlay(true);
////        fullscreen_powerImageView.setRes(R.drawable.anfd);
////        fullscreen_powerImageView.setRes(R.drawable.car);
//        prop_send_button = (Button) findViewById(R.id.prop_send_button);
//        fl_follower_anchor = (FrameLayout) findViewById(R.id.fl_follower_anchor);
//        btn_concerned = (ImageView) findViewById(R.id.btn_concerned);
//        ll_follower = (LinearLayout) findViewById(R.id.ll_follower);
//        iv_followed = (ImageView) findViewById(R.id.iv_follow);
//        follower_nu = (TextView) findViewById(R.id.follower_nu);
//        ll_user_manager = (FrameLayout) findViewById(R.id.ll_user_manager);
//        button_close_user_manager = (Button) findViewById(R.id.button_close_user_manager);
//
//        ll_out = (LinearLayout) findViewById(R.id.ll_out);
//        ll_ban = (LinearLayout) findViewById(R.id.ll_ban);
//
//
//        ll_ban.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        ll_out.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        button_close_user_manager.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ll_user_manager.setVisibility(View.GONE);
//            }
//        });
//
//        follower_nu.setText("" + watchCountTotal);
//
//
//        fl_follower_anchor.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.fl_follower_anchor) {
////                    btn_concerned.setImageResource(R.drawable.btn_concerned_onclick);
////                    ll_follower.setVisibility(View.GONE);
////                    iv_followed.setVisibility(View.VISIBLE);
//                    if (!userHasFansRoom) {
//                        //发送请求，添加关注，回调中更改界面,userHasFansRoom 修改为true
//
//                        new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/room/fansRoom") {
//
//                            @Override
//                            public void callback(Message msg, boolean error, Activity aty) {
//
//                                String code = (String) JsonUtil.findJsonLink("code", getObject());
//
//                                if (code.equals("ok")) {
//                                    Log.e(TAG, "code ok");
//                                    int fansRoomCount = (int) JsonUtil.findJsonLink("data-fansRoomCount", getObject());
//                                    User.getInstance().setFansRoomCount("" + fansRoomCount);
//                                    ll_follower.setVisibility(View.GONE);
//                                    iv_followed.setVisibility(View.VISIBLE);
//                                    userHasFansRoom = true;
//                                }
//                            }
//                        }.addParam("RoomId", PublicLayout.RoomId).addParam("encode", 0).addParam("Oper", 1).addParam("UserId", userId).addContext(NEVideoPlayerActivity.this).start();
//
//                    } else if (userHasFansRoom) {
//                        if (true) return;
//                        //发送请求，取消关注，回调中更改界面, userHasFansRoom 修改为false;
//                        new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/room/fansRoom") {
//                            @Override
//                            public void callback(Message msg, boolean error, Activity aty) {
//                                String code = (String) JsonUtil.findJsonLink("code", getObject());
//                                if (code.equals("ok")) {
//                                    int fansRoomCount = (int) JsonUtil.findJsonLink("data-fansRoomCount", getObject());
//                                    User.getInstance().setFansRoomCount("" + fansRoomCount);
//                                    ll_follower.setVisibility(View.VISIBLE);
//                                    iv_followed.setVisibility(View.GONE);
//                                    userHasFansRoom = false;
//                                }
//                            }
//                        }.addParam("RoomId", PublicLayout.RoomId).addParam("encode", 0).addParam("Oper", 2).addParam("UserId", userId).addContext(NEVideoPlayerActivity.this).start();
//                    }
//                }
//            }
//        });
//
//        mDanmakuView = (DanmakuView) findViewById(R.id.danmakuView);
////        List<IDanmakuItem> list = initItems();
////        mDanmakuView.addItem(list, true);
//
//        prop_send_button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (!send_Btn_flag) {
//                    if (publicLayout.record != null) {
////                        if (!send_Btn_flag) {
////                            send_Btn_flag = true;
//                            final String escapeCode = ((Map<String, String>) publicLayout.record.get("propMapping")).get("escapeCode").toString();
//                            final String propCode = publicLayout.record.get("propCode").toString();
//                            final String propName = publicLayout.record.get("propName").toString();
////                final String num = publicLayout.record.get("num") == null ? "1" : publicLayout.record.get("num").toString();
//                            startpayGift(propCode, propName, escapeCode, publicLayout.num + "");
//
//                        }
////                    }
//
////                fullscreen_powerImageView.setAutoPlay(true);
////                fullscreen_powerImageView.setVisibility(View.VISIBLE);
//////                fullscreen_powerImageView.setRes(R.drawable.man);
////
////                fullscreen_powerImageView.setAutoPlay(true);
////                fullscreen_powerImageView.setVisibility(View.VISIBLE);
//////                fullscreen_powerImageView.setRes(R.drawable.man);
////                fullscreen_powerImageView.setRes(R.drawable.man);
////                fullscreen_powerImageView.setVisibility(View.VISIBLE);
//                }
////            }
//        });
//
////        Intent i1 = new Intent(this, LoveIconProgressGenerateService.class);
////        startService(i1);
//
////        bt_prop_nu.setText("X1");
//
//        final View viewPropRank = findViewById(R.id.view_proprank);
//        findViewById(R.id.fl_prop_bottom).findViewById(R.id.ic_coin).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(view.getContext(), UserCharge.class));
//            }
//        });
//        findViewById(R.id.fl_prop_bottom).findViewById(R.id.balance).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(view.getContext(), UserCharge.class));
//            }
//        });
//        prop_hot_image.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (propRankPosition == 1) {
////                    Animation animation = AnimationUtils.loadAnimation(NEVideoPlayerActivity.this,R.anim.proprankposition_midtoleft);
//
//                } else if (propRankPosition == 2) {
//                    Animation animation = AnimationUtils.loadAnimation(NEVideoPlayerActivity.this, R.anim.proprankposition_righttoleft);
//                    animation.setFillAfter(true);
//                    viewPropRank.startAnimation(animation);
//                }
//                prop_viewPager.setCurrentItem(0);
////                propRankPosition = 0;
//            }
//        });
//
//        prop_vip_image.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (propRankPosition == 0) {
//
//                } else if (rankPosition == 2) {
//
//                }
//                prop_viewPager.setCurrentItem(1);
////                propRankPosition = 1;
//            }
//        });
//
//        prop_bag_image.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (propRankPosition == 0) {
//                    Animation animation = AnimationUtils.loadAnimation(NEVideoPlayerActivity.this, R.anim.proprankposition_lefttoright);
//                    animation.setFillAfter(true);
//                    viewPropRank.startAnimation(animation);
//                } else if (propRankPosition == 1) {
//
//                }
//                prop_viewPager.setCurrentItem(2);
////                propRankPosition = 2;
//            }
//        });
//
//
//        prop_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == 0) {
//                    Log.e("==========", "position0");
//                    if (propRankPosition == 1) {
//                        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.proprankposition_midtoleft);
//                        animation.setFillAfter(true);
//                        viewPropRank.startAnimation(animation);
//                    }
//                    propRankPosition = 0;
//                } else if (position == 1) {
//                    Log.e("==========", "position1");
//                    if (propRankPosition == 0) {
//                        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.proprankposition_lefttomid);
//                        animation.setFillAfter(true);
//                        viewPropRank.startAnimation(animation);
//                    } else if (propRankPosition == 2) {
//                        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.proprankposition_righttomid);
//                        animation.setFillAfter(true);
//                        viewPropRank.startAnimation(animation);
//                    }
//                    propRankPosition = 1;
//                } else if (position == 2) {
//                    Log.e("==========", "position2");
//                    if (propRankPosition == 1) {
//                        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.proprankposition_midtoright);
//                        animation.setFillAfter(true);
//                        viewPropRank.startAnimation(animation);
//                    }
//                    propRankPosition = 2;
//                }
//
//            }
//
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        bt_prop_nu.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopupWindow(v);
//            }
//        });
//
//        tv_public.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (rankPosition != 0) {
//                    viewPager.setCurrentItem(0);
//                    tv_public.setTextColor(Color.parseColor("#f16e20"));
//                    tv_viewer.setTextColor(Color.parseColor("#000000"));
//                    tv_viewerNumber.setTextColor(Color.parseColor("#000000"));
//                    tv_rightBracket.setTextColor(Color.parseColor("#000000"));
//                    tv_leftBracket.setTextColor(Color.parseColor("#000000"));
//                    tv_viewer_nu.setTextColor(Color.parseColor("#000000"));
//                }
//                rankPosition = 0;
//            }
//        });
//
//        ll_viewer.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (rankPosition != 1) {
//                    viewPager.setCurrentItem(1);
//                    tv_public.setTextColor(Color.parseColor("#000000"));
//                    tv_viewer.setTextColor(Color.parseColor("#f16e20"));
//                    tv_viewerNumber.setTextColor(Color.parseColor("#f16e20"));
//                    tv_rightBracket.setTextColor(Color.parseColor("#f16e20"));
//                    tv_leftBracket.setTextColor(Color.parseColor("#f16e20"));
//                    tv_viewer_nu.setTextColor(Color.parseColor("#f16e20"));
//                }
//                rankPosition = 1;
//            }
//        });
//
//        if (userHasFansRoom) {
//            ll_follower.setVisibility(View.GONE);
//            iv_followed.setVisibility(View.VISIBLE);
//        } else {
//            ll_follower.setVisibility(View.VISIBLE);
//            iv_followed.setVisibility(View.GONE);
//        }
//
//        video_size_scale_max.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int mCurrentOrientation = NEVideoPlayerActivity.this.getResources().getConfiguration().orientation;
//                Log.e("current ori is", "" + mCurrentOrientation);
//                WindowManager.LayoutParams attrs = getWindow().getAttributes();
//                if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
//                    NEVideoPlayerActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    int mOri = NEVideoPlayerActivity.this.getResources().getConfiguration().orientation;
//                    Log.e("current ori is", "" + mOri);
//                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//                    getWindow().setAttributes(attrs);
//                    //设置全屏
//                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//                }
//                if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//                    NEVideoPlayerActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    int mOri = NEVideoPlayerActivity.this.getResources().getConfiguration().orientation;
//                    Log.e("current ori is", "" + mOri);
//                    attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//                    getWindow().setAttributes(attrs);
//                    //取消全屏设置
//                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//                }
//
//                video_size_scale_max.setVisibility(View.GONE);
//                video_size_scale_min.setVisibility(View.VISIBLE);
//            }
//        });
//
//        video_size_scale_min.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int mCurrentOrientation = NEVideoPlayerActivity.this.getResources().getConfiguration().orientation;
//                Log.e("current ori is", "" + mCurrentOrientation);
//                WindowManager.LayoutParams attrs = getWindow().getAttributes();
//                if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
//                    NEVideoPlayerActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    int mOri = NEVideoPlayerActivity.this.getResources().getConfiguration().orientation;
//                    Log.e("current ori is", "" + mOri);
//                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//                    getWindow().setAttributes(attrs);
//                    //设置全屏
//                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//                }
//                if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//                    NEVideoPlayerActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    int mOri = NEVideoPlayerActivity.this.getResources().getConfiguration().orientation;
//                    Log.e("current ori is", "" + mOri);
//                    attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//                    getWindow().setAttributes(attrs);
//                    //取消全屏设置
//                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//                }
//
//                video_size_scale_max.setVisibility(View.VISIBLE);
//                video_size_scale_min.setVisibility(View.GONE);
//            }
//        });
//
//
////		 anchor_portrait.setOnClickListener(new OnClickListener() {
////			 @Override
////			 public void onClick(View v) {
////                 if(rankPosition != 0){
////                     if(rankPosition == 1){
////                         Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.indicator_scale);
////                         animation.setFillAfter(true);
////                         ll_rank.startAnimation(animation);
////                     }else if(rankPosition == 2){
////                         Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.indicator_scale_2);
////                         animation.setFillAfter(true);
////                         animation.setAnimationListener(new Animation.AnimationListener() {
////                             @Override
////                             public void onAnimationStart(Animation animation) {
////
////                             }
////
////                             @Override
////                             public void onAnimationEnd(Animation animation) {
////                                 AnimatorSet set = new AnimatorSet();
////                                 ValueAnimator bounceBack = ObjectAnimator.ofInt(findViewById(R.id.roundprogress), "progress", 0, 50);
////                                 bounceBack.setDuration(170);
////                                 bounceBack.setInterpolator(new DecelerateInterpolator());
////                                 ValueAnimator bounceBack2 = ObjectAnimator.ofInt(findViewById(R.id.roundprogress), "progress", 51, 100);
////                                 bounceBack2.setDuration(300);
////                                 bounceBack2.setInterpolator(new DecelerateInterpolator());
////
////                                 set.play(bounceBack2).after(bounceBack);
////                                 set.start();
////                             }
////
////                             @Override
////                             public void onAnimationRepeat(Animation animation) {
////
////                             }
////                         });
////                         ll_rank.startAnimation(animation);
////                     }
////                     viewPager.setCurrentItem(0);
////                     tv_public.setTextColor(Color.parseColor("#000000"));
////                     tv_viewer.setTextColor(Color.parseColor("#000000"));
////                     tv_viewerNumber.setTextColor(Color.parseColor("#000000"));
////                     tv_rightBracket.setTextColor(Color.parseColor("#000000"));
////                     tv_leftBracket.setTextColor(Color.parseColor("#000000"));
////                 }
////                 rankPosition = 0;
////			 }
////		 });
//        new Compt() {
//            @Override
//            public void run() throws Exception {
//                super.run();
//            }
//
//            @Override
//            public void callback(Message msg, boolean error, Activity aty) {
//                super.callback(msg, error, aty);
//                viewPager = (ViewPager) findViewById(R.id.viewPager_play);
//                viewPager.setAdapter(new PlayViewPagerAdapter());
//                viewPager.setCurrentItem(0);
//                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int state) {
//
//                    }
//
//                    AnimatorSet set = new AnimatorSet();
//
//                    @Override
//                    public void onPageSelected(int position) {
//                        if (position == 0) {
//                            if (rankPosition == 1) {
//                                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rank_toleft);
//                                animation.setFillAfter(true);
//                                ll_rank.startAnimation(animation);
//                                tv_public.setTextColor(Color.parseColor("#f16e20"));
//                                tv_viewer.setTextColor(Color.parseColor("#000000"));
//                                tv_viewerNumber.setTextColor(Color.parseColor("#000000"));
//                                tv_rightBracket.setTextColor(Color.parseColor("#000000"));
//                                tv_leftBracket.setTextColor(Color.parseColor("#000000"));
//                                tv_viewer_nu.setTextColor(Color.parseColor("#000000"));
//                            }
//                            rankPosition = 0;
//
//                        } else if (position == 1) {
//                            if (rankPosition == 0) {
//                                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rank_toright);
//                                animation.setFillAfter(true);
//                                ll_rank.startAnimation(animation);
//                                tv_public.setTextColor(Color.parseColor("#000000"));
//                                tv_viewer.setTextColor(Color.parseColor("#f16e20"));
//                                tv_viewerNumber.setTextColor(Color.parseColor("#f16e20"));
//                                tv_rightBracket.setTextColor(Color.parseColor("#f16e20"));
//                                tv_leftBracket.setTextColor(Color.parseColor("#f16e20"));
//                                tv_viewer_nu.setTextColor(Color.parseColor("#f16e20"));
//
//                            }
//                            rankPosition = 1;
//                        }
//                    }
//                });
//
//            }
//        }.start();
//
//
////        tv_follower.setVisibility(View.VISIBLE);
//
//        ll_back = back_bt;
//        back_bt.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.ic_back01) {
//                    finish();
//                }
//            }
//        });
//
//
//        Log.d(TAG, "playType = " + mMediaType);
//        Log.d(TAG, "decodeType = " + mDecodeType);
//        Log.d(TAG, "videoPath = " + mVideoPath);
//
//        Log.d(TAG, "userHasFansRoom = " + userHasFansRoom);
//        Log.d(TAG, "watchUserCount = " + watchUserCount);
//        Log.d(TAG, "userId = " + userId);
//
//        if (mMediaType.equals("localaudio")) { //本地音频文件采用软件解码
//            mDecodeType = "software";
//        }
//
//
//        String intentAction = intent.getAction();
//        if (!TextUtils.isEmpty(intentAction) && intentAction.equals(Intent.ACTION_VIEW)) {
////            mVideoPath = intent.getStringExtra("videoPath");
//            mVideoPath = "/storage/emulated/0/幼儿园大课间.mp4";
//            Log.d(TAG, "videoPath = " + mVideoPath);
//        }
//
//        if (mDecodeType.equals("hardware")) {
//            mHardware = true;
//        } else if (mDecodeType.equals("software")) {
//            mHardware = false;
//        }
//
////		mPlayBack = (ImageButton)findViewById(R.id.player_exit);//退出播放
////		mPlayBack.getBackground().setAlpha(0);
////		mFileName = (TextView)findViewById(R.id.file_name);
//
//        mUri = Uri.parse(mVideoPath);
//        if (mUri != null) { //获取文件名，不包括地址
//            List<String> paths = mUri.getPathSegments();
//            String name = paths == null || paths.isEmpty() ? "null" : paths.get(paths.size() - 1);
//            setFileName(name);
//        }
//
////	    mAudioRemind = (ImageView)findViewById(R.id.audio_remind);
//        if (mMediaType.equals("localaudio")) {
////	    	mAudioRemind.setVisibility(View.VISIBLE);
//            //mAudioRemind.setBackgroundColor(Color.rgb(255, 0, 0));
//        } else {
////	    	mAudioRemind.setVisibility(View.INVISIBLE);
//        }
//
////	    mPlayToolbar = (RelativeLayout)findViewById(R.id.play_toolbar);
////	    mPlayToolbar.setVisibility(View.INVISIBLE);
//
//        mBuffer = findViewById(R.id.buffering_prompt);
////		mMediaController = new NEMediaController(this);
//
//
////		else {
////			Toast toast = Toast.makeText(this, "Network is unavailable.", Toast.LENGTH_LONG);
////			toast.setGravity(Gravity.TOP,0,height/4);
////			TextView v = (TextView) toast.getParent().findViewById(android.R.id.message);
////			v.setTextColor(Color.RED);
////			toast.show();
//////            finish();
////		}
//        mVideoView = (NEVideoView) findViewById(R.id.video_view);
//
//        new Compt() {
//            @Override
//            public void callback(Message msg, boolean error, Activity aty) {
//                super.callback(msg, error, aty);
//                Display display = getWindowManager().getDefaultDisplay();
//                int height = display.getHeight();
//
//                //Toast when enter the room if not in wifi network  or network is not available
//                mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
//
//                if (mNetworkInfo != null) {
//                    networkType = mNetworkInfo.getType();
//                    if (networkType != ConnectivityManager.TYPE_WIFI) {
//                        Toast toast = Toast.makeText(NEVideoPlayerActivity.this, R.string.use_nowifi_network, Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.TOP, 0, height / 4);
//                        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
//                        v.setTextColor(Color.RED);
//                        toast.show();
//
////                        tv_wifi_network.setText(R.string.use_nowifi_network);
////                        tv_wifi_network.setVisibility(View.VISIBLE);
////                        tv_wifi_network.postDelayed(new Runnable() {
////                            @Override
////                            public void run() {
////                                tv_wifi_network.setVisibility(View.GONE);
////                            }
////                        }, 3000);
//
////                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
////                dialog.setMessage("You are using 2G/3G/4G network,Are you going to play the video or not?");
////                dialog.setCancelable(false);
////                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////
////                    }
////                });
////                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        finish();
////                    }
////                });
////                dialog.show();
//                    }
//                }
//                syncLoveView();
//
//
//                syncVideoView();
//
//            }
//
//        }.start();
////        throw new RuntimeException("test");
//    }
//
//    public boolean send_Btn_flag;
//
//    public void syncVideoView() {
//
//
////        new Compt() {
////            @Override
////            public void run() throws Exception {
////                super.run();
////                Thread.sleep(3000);
////            }
////
////            @Override
////            public void callback(Message msg, boolean error, Activity aty) {
////                super.callback(msg, error, aty);
////                if (FullscreenActivity.libneliveplayer.isFinish() && FullscreenActivity.libnelpengine.isFinish() && FullscreenActivity.libnelprender.isFinish()) {
//////                    System.load(FullscreenActivity.libneliveplayer.getAbsPath());
//////                    System.load(FullscreenActivity.libnelprender.getAbsPath());
//////                    System.load(FullscreenActivity.libnelpengine.getAbsPath());
////
////
////                } else
////                    rerun();
////            }
////        }.start();
//
//        if (!mVideoPath.equals("")) {
//            if (mMediaType.equals("livestream")) {
//                mVideoView.setBufferStrategy(0); //直播低延时
////                Toast.makeText(NEVideoPlayerActivity.this,"BufferStrategy is 0",Toast.LENGTH_SHORT).show();
//            } else {
////                mVideoView.setBufferStrategy(1); //点播抗抖动
//                mVideoView.setBufferStrategy(1); //点播抗抖动
////                Toast.makeText(NEVideoPlayerActivity.this,"BufferStrategy is 1",Toast.LENGTH_SHORT).show();
////                Toast.makeText(NEVideoPlayerActivity.this, "You can expand the video to fullscreen mode", Toast.LENGTH_SHORT).show();
//            }
////		mVideoView.setMediaController(mMediaController);
//            mVideoView.setBufferPrompt(mBuffer);
////        mVideoView.setVideoScalingMode(NEVideoView.VIDEO_SCALING_MODE_FULL);
//            NELivePlayer.OnCompletionListener mCompletionListener = new NELivePlayer.OnCompletionListener() {
//
//
//                @Override
//                public void onCompletion(NELivePlayer neLivePlayer) {
//
//                    if (mMediaType.equalsIgnoreCase("livestream")) {
//
//                        top_show.setVisibility(View.VISIBLE);
//                        video_frame.setVisibility(View.GONE);
//
//
//                    } else {
//                        //    mVideoView.setMediaController(mMediaController);
//                        mVideoView.setBufferPrompt(mBuffer);
//
//                        mVideoView.setMediaType(mMediaType);
//                        mVideoView.setHardwareDecoder(mHardware);
//                        mVideoView.setPauseInBackground(pauseInBackgroud);
//                        mVideoView.setVideoPath(mVideoPath);
//                        mMediaPlayer.setLogLevel(NELP_LOG_SILENT); //设置log级别
//                        mVideoView.requestFocus();
//                        mVideoView.start();
//                    }
//
//
//                }
//
//
//            };
//
//
//            NELivePlayer.OnBufferingUpdateListener mBufferingUpdateListener = new NELivePlayer.OnBufferingUpdateListener() {
//
//                @Override
//                public void onBufferingUpdate(NELivePlayer neLivePlayer, int i) {
////                    Log.e("current buffer per", "" + i);
//                }
//            };
//
//            mVideoView.setOnCompletionListener(mCompletionListener);
//            mVideoView.setOnBufferingUpdateListener(mBufferingUpdateListener);
//            mVideoView.setMediaType(mMediaType);
//            mVideoView.setHardwareDecoder(mHardware);
//            mVideoView.setPauseInBackground(pauseInBackgroud);
//            mVideoView.setVideoPath(mVideoPath);
//            mMediaPlayer = new NEMediaPlayer();
//            Exception x = null;
//            mMediaPlayer.setLogLevel(NELP_LOG_SILENT); //设置log级别
//            try {
//                mVideoView.setVideoPath(mVideoPath);
//            } catch (Exception e) {
//                x = e;
//
//            }
//            if (x == null) {
//                mVideoView.requestFocus();
//                mVideoView.start();
//            }
////            mMediaPlayer.setOnBufferingUpdateListener(new NELivePlayer.OnBufferingUpdateListener() {
////                @Override
////                public void onBufferingUpdate(NELivePlayer neLivePlayer, int i) {
////
////                }
////            });
//
////		mPlayBack.setOnClickListener(mOnClickEvent); //监听退出播放的事件响应
////		mMediaController.setOnShownListener(mOnShowListener); //监听mediacontroller是否显示
////		mMediaController.setOnHiddenListener(mOnHiddenListener); //监听mediacontroller是否隐藏
//        } else {
////            Toast.makeText(NEVideoPlayerActivity.this, "mVideoPath: " + mVideoPath, Toast.LENGTH_SHORT).show();
//            Log.e("error", "mVideoPath: " + mVideoPath);
//        }
//        mVideoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (ll_back.getVisibility() == View.INVISIBLE) {
//                    ll_back.setVisibility(View.VISIBLE);
//                    rl_follower.setVisibility(View.VISIBLE);
//                    if (mMediaType.equals("videoondemand")) {
//                        fl_screen_scale.setVisibility(View.VISIBLE);
//                    }
//                    ll_back.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            ll_back.setVisibility(View.INVISIBLE);
//                            rl_follower.setVisibility(View.INVISIBLE);
//                            fl_screen_scale.setVisibility(View.INVISIBLE);
//                        }
//                    }, 3000);
//                } else if (ll_back.getVisibility() == View.VISIBLE) {
//                    ll_back.setVisibility(View.INVISIBLE);
//                    rl_follower.setVisibility(View.INVISIBLE);
//                    fl_screen_scale.setVisibility(View.INVISIBLE);
//                }
//                return false;
//            }
//        });
//
//    }
//
//
//    void showBigPic(final String url) {
////        try {
//        if (url.equals("")) {
//            return;
//        }
//        fullscreen_powerImageView.setAutoPlay(true);
////        fullscreen_powerImageView.setVisibility(View.VISIBLE);
////                fullscreen_powerImageView.setRes(R.drawable.man);
//        Toast.makeText(this, url, Toast.LENGTH_LONG).show();
//
////            fullscreen_powerImageView.setUrl(url);
////        fullscreen_powerImageView.setRes(R.drawable.man);
////        fullscreen_powerImageView.setRes(R.drawable.car);
////        }catch(Exception e){
////            e.printStackTrace();
////        }
//    }
//
//    private void syncLoveView() {
//        contentView = LayoutInflater.from(NEVideoPlayerActivity.this).inflate(R.layout.popupwindow_layout, null);
//        contentView.measure(0, 0);
//        TextView tv1 = (TextView) contentView.findViewById(R.id.x1);
//        TextView tv10 = (TextView) contentView.findViewById(R.id.x10);
//        TextView tv50 = (TextView) contentView.findViewById(R.id.x50);
//
//        tv1.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bt_prop_nu.setText("X1");
////                DisplayMetrics m = new DisplayMetrics();
////                getWindowManager().getDefaultDisplay().getMetrics(m);
////                bt_prop_nu.setTextSize(16*m.densityDpi/160/2);
//                popupWindow.dismiss();
////                bt_prop_nu.requestLayout();
////                ((View)bt_prop_nu.getParent()).requestLayout();
////                bt_prop_nu.invalidate();
////                ((View)bt_prop_nu.getParent()).invalidate();
//
//            }
//        });
//
//        tv10.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bt_prop_nu.setText("X10");
//                popupWindow.dismiss();
//            }
//        });
//
//        tv50.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bt_prop_nu.setText("X50");
//                popupWindow.dismiss();
//            }
//        });
//    }
//
//
//    private void calTime() {
//
//        if (timer == null) {
//            timer = new Timer();
//
//            if (progress >= 0 && progress <= 15) {
//                loveIconGeneratePro.setProgress(progress);
//            } else {
//                loveIconGeneratePro.setProgress(0);
//                progress = 0;
//            }
//
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    progress++;
//                    loveIconGeneratePro.setProgress(progress);
//                    SPUtil.getInstance().putValue("passedtime", progress);
//                    Log.e("Testmmmmmmmm", "current progress is " + progress);
//
//                    if (progress == 15) {
//                        new DefaultParamsHttpCompt("http://cloud.golomee.com/api/v1/room/heartbeatLink") {
//                            @Override
//                            public void callback(Message msg, boolean error, Activity aty) {
//                                super.callback(msg, error, aty);
//                                if (isValid()) {
//                                    progress = 0;
//                                    loveIconGeneratePro.setProgress(0);
//                                    PublicLayout.loveIconNumber = (int) JsonUtil.findJsonLink("data", getObject());
//                                    Log.e("Testmmmmmmmm", "current loveIconNumber is " + PublicLayout.loveIconNumber);
//
//                                    new Handler(Looper.getMainLooper(), new Handler.Callback() {
//                                        @Override
//                                        public boolean handleMessage(Message msg) {
////                                            loveIconGeneratePro.setProgress(loveIconNumber);
//                                            tv_loveIconNumber.setText("" + PublicLayout.loveIconNumber);
//                                            return false;
//                                        }
//                                    }).sendEmptyMessage(0);
//                                }
//                            }
//                        }.addParam("RoomId", PublicLayout.RoomId).addParam("Guid", User.getInstance().guid).addParam("encode", 0).start();
//
//                    } else if (progress > 15) {
//                        progress = 0;
//                        loveIconGeneratePro.setProgress(0);
//                    }
//                }
//            }, 1 * 1000, 60 * 1000);
//        }
//    }
//
//
//    private PopupWindow popupWindow;
//    View contentView;
//    TextView tv1;
//    TextView tv10;
//    TextView tv50;
//
//    @Override
//    public void callback() {
//        fullscreen_powerImageView.setVisibility(View.INVISIBLE);
//        Log.e("================", "Test callback");
//    }
//
//    public void cancelDialog() {
//        d.dismiss();
//        d.cancel();
//    }
//
//    DialogUtil.DialogAbstract paydialog;
//
//    public void startpayGift(final String propCode, final String propName, final String escapecode, final String num) {
//        final PayCallback paycallback = this.paycallback;
//        new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/costMyProp") {
//            @Override
//            public void callback(Message msg,
//                                 boolean error, Activity aty) {
//                super.callback(msg, error, aty);
//                if (!isValid()) {
//                    new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/buyProp") {
//
//                        @Override
//                        public void callback(Message msg, boolean error, Activity aty) {
//                            super.callback(msg, error, aty);
//
//                            if (!isValid()) {
////                                if (paydialog == null) {
//                                    paydialog = new DialogUtil.DialogAbstract();
//
//                                    DialogUtil.DialogAbstract ab = paydialog;
//                                    ab.context = NEVideoPlayerActivity.this;
//                                    ab.message = getString(R.string.lack_to_pay_gift);
//                                    ab.negativeButtonClickListener = DialogUtil.getNewCancelOption(ab.context);
//
//                                    ab.negativeButtonText = getString(R.string.cancel);
//                                    ab.positiveButtonText = getString(R.string.pay);
//                                    ab.positiveButtonClickListener = new DialogInterface.OnClickListener() {
//
//
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            startActivity(new Intent(NEVideoPlayerActivity.this, UserCharge.class));
//                                            paydialog = null;
////                                                    new BluePayUtil(NEVideoPlayerActivity.this).payCashcard(User.getInstance().userid, PublisherCode.PUBLISHER_12CALL);
//
//                                        }
//
//
//                                    };
//                                    send_Btn_flag = false;
//                                    DialogUtil.showChoiceDialog(ab);
////                                }
//
//                            } else {
//                                startpayGift(propCode, propName, escapecode, num);
//                            }
//                        }
//                    }.addParam("encode", "0").addParam("UserId", User.getInstance().userid).addParam("Num", num).addParam("PropCode", propCode).fill(new Object[][]{{"dataObject", R.id.sum}}, getWindow().getDecorView()).assignVar(new Object[][]{{"dataObject", "money"}}, User.getInstance()).start();
//                } else {
//                    publicLayout.sendgift("/" + escapecode + " ", propName, num + "");
//                    ll_chat.setVisibility(View.VISIBLE);
//                    fl_prop.setVisibility(View.GONE);
//                    send_Btn_flag = false;
//                }
//
//            }
//        }.addParam("UserId", User.getInstance().userid).addParam("PropCode", propCode).addParam("Num", num).addParam("AnchorGuid", User.getInstance().showerguid).start();
//    }
//
//    public void payUI(final Context context) {
//        final DialogUtil.DialogAbstract ab = new DialogUtil.DialogAbstract();
//        ab.context = NEVideoPlayerActivity.this;
//        ab.view = LayoutInflater.from(ab.context).inflate(R.layout.pay, null);
//        ab.view.findViewById(R.id.sms).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new BluePayUtil(NEVideoPlayerActivity.this).setCallback(paycallback).paySMSUI(context, "whatgift");
//                cancelDialog();
//            }
//        });
//        ab.view.findViewById(R.id.pay12call).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UserCharge.chargeCard(PublisherCode.PUBLISHER_12CALL);
//                cancelDialog();
//            }
//        });
//        ab.view.findViewById(R.id.Truemoney).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UserCharge.chargeCard(PublisherCode.PUBLISHER_TRUEMONEY);
//                cancelDialog();
//            }
//        });
//        ab.view.findViewById(R.id.Happy).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UserCharge.chargeCard(PublisherCode.PUBLISHER_HAPPY);
//                cancelDialog();
//            }
//        });
//        ab.neutralButtonClickListener = new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//                dialog.dismiss();
//                dialog.cancel();
//            }
//        };
//        d = DialogUtil.showNeutralDialog(ab);
//    }
//
//    Dialog d;
//    PayCallback paycallback = new PayCallback() {
//        @Override
//        public void onFinished(int code, BlueMessage msg) {
//            super.onFinished(code, msg);
//            final PayCallback finalPaycallback = this;
////                                new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/buyProp"){
////                                    @Override
////                                    public void callback(Message msg, boolean error, Activity aty) {
////                                        super.callback(msg, error, aty);
////                                        if(isValid()){
////                                            new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/costMyProp") {
////                                            @Override
////                                            public void callback(Message msg, boolean error, Activity aty) {
////                                                super.callback(msg, error, aty);
////                                                if(isValid()){
////                                                    publicLayout.send("/"+propCode+" ");
////                                                }
////                                            }}.addParam("UserId", User.getInstance().userid).addParam("PropCode", propCode).addParam("Num", num).start();
////                                        }else{
////                                            final DialogUtil.DialogAbstract ab = new DialogUtil.DialogAbstract();
////                                            ab.context = NEVideoPlayerActivity.this;
////                                            ab.message = getString(R.string.continuetopay);
////                                            ab.negativeButtonClickListener = DialogUtil.getNewCancelOption(ab.context);
////                                            ab.negativeButtonText = getString(R.string.cancel);
////                                            ab.positiveButtonText = getString(R.string.pay);
////                                            ab.positiveButtonClickListener = new DialogInterface.OnClickListener() {
////                                                @Override
////                                                public void onClick(DialogInterface dialog, int which) {
////                                                    payUI();
////                                                    dialog.dismiss();;
////                                                    dialog.cancel();
////                                                }
////                                            };
////                                            DialogUtil.showChoiceDialog(ab);
////                                        }
////                                    }
////                                }.addParam("encode", "0").addParam("UserId", User.getInstance().userid).addParam("Num", num).addParam("PropCode", propCode).start();
//
//        }
//    };
//
//
//    class propViewPagerAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        @Override
//        public Object instantiateItem(final ViewGroup container, int position) {
//            if (position == 0) {
//                View hotPropLayout = new hotPropLayout(NEVideoPlayerActivity.this);
//                container.addView(hotPropLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                final HttpCompt[] cost = new HttpCompt[1];
//                new DefaultParamsHttpCompt("http://dev.golomee.com/api/v1/prop/groupPropGiving.action").addParam("encode", "0").fill(R.drawable.load).fill(new Object[][]{{"data:props[propKind:1]", R.id.gridView, R.layout.prop_gridview_cell_layout}}, hotPropLayout).start();
//                setGridItemClick(hotPropLayout, cost);
//                return hotPropLayout;
//            }
////            else if (position == 1) {
////                View vipPropLayout = new vipPropLayout(NEVideoPlayerActivity.this);
////                container.addView(vipPropLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
////                final HttpCompt[] cost = new HttpCompt[1];
////                new DefaultParamsHttpCompt("http://dev.golomee.com/api/v1/prop/groupPropGiving.action").addParam("encode", "0").fill(R.drawable.load).fill(new Object[][]{{"data:props[propKind:2]", R.id.gridView, R.layout.prop_gridview_cell_layout}}, vipPropLayout).start();
////                setFullSnGridItemClick(vipPropLayout, cost);
////                return vipPropLayout;
////            }
//            else if (position == 1) {
//                final View bagPropLayout = new bagPropLayout(NEVideoPlayerActivity.this);
//                new DefaultParamsHttpCompt("http://dev.golomee.com/api/v1/user/getMyProp") {
//
//                    @Override
//                    public void callback(Message msg, boolean error, Activity aty) {
//                        super.callback(msg, error, aty);
//                        try {
//                            List list = parseJsonString(new JSONArray(JsonUtil.findJsonLink("data", getObject()).toString()));
//                            new ViewSetter() {
//                                @Override
//                                public boolean setViewOther(View theView, Object value, int itemlayout, Object item) {
//                                    if (theView.getId() == R.id.propName) {
//                                        ((View) theView.getParent()).findViewById(R.id.price).setVisibility(View.GONE);
//                                    }
//                                    return super.setViewOther(theView, value, itemlayout, item);
//                                }
//                            }.setImageConfig(R.drawable.load).setView(bagPropLayout.findViewById(R.id.gridView), list, R.layout.prop_gridview_cell_layout);
//                            if (list.size() <= 0) {
//                                bagPropLayout.findViewById(R.id.gridView).setVisibility(View.GONE);
//                                bagPropLayout.findViewById(R.id.no_pic).setVisibility(View.VISIBLE);
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }.addParam("UserId", User.getInstance().userid).start();
//                final HttpCompt[] cost = new HttpCompt[1];
//                setBagGridItemClick(bagPropLayout, cost);
//                container.addView(bagPropLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                return bagPropLayout;
//            }
//            return null;
//        }
//
//        void setGridItemClick(View hotPropLayout, final HttpCompt[] cost) {
//            ((GridView) hotPropLayout.findViewById(R.id.gridView)).setAdapter(new MapAdapter(NEVideoPlayerActivity.this, new MapAdapter.AdaptInfo().setSelectedColor(Color.parseColor("#dfdfdf"), Color.parseColor("#ffffffff"), R.id.root)));
//
//            ((GridView) hotPropLayout.findViewById(R.id.gridView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                View latestview;
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    view.findViewById(R.id.root).setBackgroundColor(Color.parseColor("#dfdfdf"));
////                    if (latestview != null) {
////                        latestview.findViewById(R.id.root).setBackgroundColor(Color.parseColor("#ffffffff"));
////                    }
////                    latestview = view;
//
////                    Map map = (Map) ((MapAdapter) parent.getAdapter()).getItemDataSrc().getItem(position);
////                    final String propCode = ((Map<String, String>) map.get("propMapping")).get("escapeCode").toString();
////                    final String propName = map.get("propName").toString();
////                    final String num = map.get("num") == null ? "1" : map.get("num").toString();
////                    final String pic = map.get("pic").toString();
//
//
//                    publicLayout.record = (Map<String, Object>) ((MapAdapter) parent.getAdapter()).getItem(position);
//                    publicLayout.selectedgiftpos = ((MapAdapter) parent.getAdapter()).selectedNum = position;
////                    cost[0] = new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/costMyProp") {
////                        @Override
////                        public void callback(Message msg,
////                                             boolean error, Activity aty) {
////                            super.callback(msg, error, aty);
////
////                            if (!isValid()) {
////                                new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/buyProp") {
////                                    @Override
////                                    public void callback(Message msg, boolean error, Activity aty) {
////                                        super.callback(msg, error, aty);
////                                        if (!isValid()) {
////                                            final DialogUtil.DialogAbstract ab = new DialogUtil.DialogAbstract();
////                                            ab.context = NEVideoPlayerActivity.this;
////                                            ab.message = getString(R.string.lack_to_pay_gift)
////                                                    + "\n" + propName;
////                                            ab.negativeButtonClickListener = DialogUtil.getNewCancelOption(ab.context);
////                                            ab.negativeButtonText = getString(R.string.cancel);
////                                            ab.positiveButtonText = getString(R.string.pay);
////                                            ab.positiveButtonClickListener = new DialogInterface.OnClickListener() {
////                                                @Override
////                                                public void onClick(DialogInterface dialog, int which) {
//////                                                        new BluePayUtil(NEVideoPlayerActivity.this).setViewpayFunction(NEVideoPlayerActivity.this, propName);
////                                                    startActivity(new Intent(NEVideoPlayerActivity.this, UserCharge.class));
////                                                    dialog.dismiss();
////                                                    dialog.cancel();
////                                                }
////                                            };
////                                            DialogUtil.showChoiceDialog(ab);
////
////
////                                        } else {
////                                            Toast.makeText(NEVideoPlayerActivity.this, getString(R.string.paid) + " " + propName + " x " + num, Toast.LENGTH_SHORT).show();
////                                            cancelPropVisible();
////                                            cost[0].start();
////
////                                        }
////                                    }
////
////                                }.addParam("UserId", User.getInstance().userid).addParam("PropCode", propCode).addParam("Num", num).start();
////                            } else {
////                                cancelPropVisible();
////                                publicLayout.sendgift("/" + propCode + " ", propCode, num);
////                            }
////                        }
////                    }.addParam("UserId", User.getInstance().userid).addParam("AnchorGuid", User.getInstance().showerguid).addParam("PropCode", propCode).addParam("Num", num);
////                    cost[0].start();
//                    ((MapAdapter) parent.getAdapter()).notifyDataSetChanged();
//                }
//            });
//        }
//
//        void setFullSnGridItemClick(View hotPropLayout, final HttpCompt[] cost) {
//            ((GridView) hotPropLayout.findViewById(R.id.gridView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Map map = (Map) ((MapAdapter) parent.getAdapter()).getItemDataSrc().getItem(position);
//                    final String propCode = ((Map<String, String>) map.get("propMapping")).get("escapeCode").toString();
//                    final String propName = map.get("propName").toString();
//                    final String num = map.get("num") == null ? "1" : map.get("num").toString();
//                    final String pic = map.get("pic").toString();
//                    cost[0] = new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/costMyProp") {
//                        @Override
//                        public void callback(Message msg,
//                                             boolean error, Activity aty) {
//                            super.callback(msg, error, aty);
//
//                            if (getObject().toString().contains("109")) {
//                                new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/buyProp") {
//                                    @Override
//                                    public void callback(Message msg, boolean error, Activity aty) {
//                                        super.callback(msg, error, aty);
//                                        if (!isValid()) {
//                                            final DialogUtil.DialogAbstract ab = new DialogUtil.DialogAbstract();
//                                            ab.context = NEVideoPlayerActivity.this;
//                                            ab.message = getString(R.string.lack_to_pay_gift)
//                                                    + "\n" + propName;
//                                            ab.negativeButtonClickListener = DialogUtil.getNewCancelOption(ab.context);
//                                            ab.negativeButtonText = getString(R.string.cancel);
//                                            ab.positiveButtonText = getString(R.string.pay);
//                                            ab.positiveButtonClickListener = new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
////                                                        new BluePayUtil(NEVideoPlayerActivity.this).setViewpayFunction(NEVideoPlayerActivity.this, propName);
//                                                    startActivity(new Intent(NEVideoPlayerActivity.this, UserCharge.class));
//                                                    dialog.dismiss();
//                                                    dialog.cancel();
//                                                }
//                                            };
//                                            DialogUtil.showChoiceDialog(ab);
//
//
//                                        } else {
////                                            Toast.makeText(NEVideoPlayerActivity.this, getString(R.string.paid) + " " + propName + " x " + num, Toast.LENGTH_SHORT).show();
//                                            cancelPropVisible();
//                                            cost[0].start();
//
//                                        }
//                                    }
//
//                                }.addParam("UserId", User.getInstance().userid).addParam("PropCode", propCode).addParam("Num", num).start();
//                            } else {
//                                cancelPropVisible();
//                                JSONObject jo = new JSONObject();
//                                try {
//                                    jo.put("pic", pic);
//                                    jo.put("propName", propName);
//                                    jo.put("num", num);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                                publicLayout.send(jo.toString(), PublicLayout.FULL_GIFT_MESSAGE, jo);
//                            }
//                        }
//                    }.addParam("UserId", User.getInstance().userid).addParam("AnchorGuid", User.getInstance().showerguid).addParam("PropCode", propCode).addParam("Num", num);
//                    cost[0].start();
//                }
//            });
//        }
//
//        void setBagGridItemClick(View hotPropLayout, final HttpCompt[] cost) {
//            ((GridView) hotPropLayout.findViewById(R.id.gridView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    Map map = (Map) ((MapAdapter) parent.getAdapter()).getItemDataSrc().getItem(position);
////                    final String propCode = ((Map<String, String>) map.get("propMapping")).get("escapeCode").toString();
////                    final String propName = map.get("propName").toString();
////                    final String num = map.get("num") == null ? "1" : map.get("num").toString();
////                    final String pic = map.get("pic").toString();
////                    cost[0] = new DefaultParamsHttpCompt("http://139.196.241.70/api/v1/prop/costMyProp") {
////                        @Override
////                        public void callback(Message msg,
////                                             boolean error, Activity aty) {
////                            super.callback(msg, error, aty);
////
////                            if (getObject().toString().contains("109")) {
////
////                            } else {
////                                cancelPropVisible();
////                                publicLayout.sendgift("/" + propCode + " ", propCode, num + "");
////                            }
////                        }
////                    }.addParam("UserId", User.getInstance().userid).addParam("AnchorGuid", User.getInstance().showerguid).addParam("PropCode", propCode).addParam("Num", num);
////                    cost[0].start();
//                }
//            });
//        }
//
//        private List parseJsonString(JSONArray fromJSONArray) {
//            List<TreeMap<String, String>> toList = new ArrayList<TreeMap<String, String>>();
//            int size = fromJSONArray.length();
//
//            try {
//                int i;
//                for (i = 0; i < size; i++) {
////                Viewer viewer = new Viewer();
//                    TreeMap<String, String> map = new TreeMap<String, String>();
//
//                    JSONObject jsonObject1 = fromJSONArray.getJSONObject(i);
////                String userHeadPic = jsonObject.getString("userHeadPic");
////                String userName = jsonObject.getString("userName");
//                    int num = jsonObject1.getInt("num");
//                    JSONObject jsonObject2 = jsonObject1.getJSONObject("prop");
//                    String picurl = jsonObject2.getString("pic");
//                    String name = jsonObject2.getString("propName");
//
//                    map.put("propName", name);
//                    map.put("num", num + "");
//                    map.put("pic", picurl);
//
////                viewer.setUserHeadPic(userHeadPic);
////                viewer.setUserName(userName);
////                toList.add(viewer);
//                    toList.add(map);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return toList;
//        }
//
//    }
//
//
//    private void showPopupWindow(View vw) {
//        View popupView;
//        final TextView tview = (TextView) vw;
//        int width1 = contentView.getMeasuredWidth();
//        int height1 = contentView.getMeasuredHeight();
//
//        Log.e("Button size width1 ", "" + width1);
//        Log.e("Button size height1 ", "" + height1);
//
//        final int width = bt_prop_nu.getMeasuredWidth();
//        final int height = bt_prop_nu.getMeasuredHeight();
//        Drawable bk = bt_prop_nu.getBackground();
//
//        int[] location = new int[2];
//        tview.getLocationOnScreen(location);
//
////        Log.e("===","x: "+location[0]+" y: "+location[1]);
////        Log.e("==="," width "+width+" height "+height);
//
//        if (popupWindow == null) {
//            popupView = LayoutInflater.from(NEVideoPlayerActivity.this).inflate(R.layout.popupwindow_layout, null);
//            TextView tv1 = (TextView) popupView.findViewById(R.id.x1);
//            TextView tv10 = (TextView) popupView.findViewById(R.id.x10);
//            TextView tv50 = (TextView) popupView.findViewById(R.id.x50);
//            tv1.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    publicLayout.num = 1;
//                    popupWindow.dismiss();
//                    tview.setText("x1");
//
//                }
//            });
//            tv10.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    publicLayout.num = 10;
//                    popupWindow.dismiss();
//                    tview.setText("x10");
//
//                }
//            });
//            tv50.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    publicLayout.num = 50;
//                    popupWindow.dismiss();
//                    tview.setText("x50");
//
//                }
//            });
//            popupWindow = new PopupWindow(popupView, width, height1, true);
////        int popupWindowWidth = popupWindow.getWidth();
//            popupWindow.setTouchable(true);
////        popupWindow.setBackgroundDrawable(bk);
//            popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.propnumselectpopupwindowbg_shape));
//        }
//        int popupWindowHeight = popupWindow.getHeight();
//
//
//        popupWindow.showAtLocation(tview, Gravity.NO_GRAVITY, location[0], location[1] - popupWindowHeight - 20);
//
//
////        tv1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height));
////        tv10.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height));
////        tv50.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height));
//
//
////        Log.e("===","popupWindowHeight"+popupWindowHeight);
////        Log.e("===","popupWindowWidth"+popupWindowWidth);
//
//
////        popupWindow.setBackgrondDrawable(getResources().getDrawable(R.drawable.selectmenu_bg_downward));
////        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
////        popupWindow.showAsDropDown(view);
////        popupWindow.showAsDropDown(view,0,height);
//    }
//
//    PublicLayout publicLayout;
//
//    private class PlayViewPagerAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            Context context = container.getContext();
//            if (position == 0) {
//
//                rl_bottom = publicLayout.findViewById(R.id.rl_bottom);
//                rl_input = (LinearLayout) publicLayout.findViewById(R.id.rl_input);
//                et_input = (EditText) publicLayout.findViewById(R.id.et_input);
//                ll_emoji = (LinearLayout) publicLayout.findViewById(R.id.ll_emoji);
//                fl_emoji_face = (FrameLayout) publicLayout.findViewById(R.id.fl_emoji_face);
//                emoji_face_dark = (ImageView) publicLayout.findViewById(R.id.emoji_face_dark);
//                emoji_face_hightlight = (ImageView) publicLayout.findViewById(R.id.emoji_face_highlight);
//
//
////                love_icon_progressBar = (RoundProgressBar) publicLayout.findViewById(R.id.love_icon_progressbar);
//                container.addView(publicLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                MsgListener.getInstance().setMsgLayout(publicLayout);
//
//                return publicLayout;
//            } else if (position == 1) {
////                View viewerLayout = new ViewerLayout(context);
////                View viewerLayoutNew = new ViewerLayoutNew(context);
//                ViewerLayoutNew1 viewerLayoutNew1 = new ViewerLayoutNew1(context);
////                tv_viewer_nu.setText("("+viewerLayoutNew1.onlineViewerNu+")");
////                tv_viewer_nu.setVisibility(View.VISIBLE);
//                container.addView(viewerLayoutNew1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                return viewerLayoutNew1;
//            }
//            return null;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//
//        }
//    }
//
//
//    private boolean input_state;
//    private long exitTime;
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        input_state = et_input.hasFocus();
////        input_state = et_input.getVisibility();
//        Log.e("mmmmmmmmm", "" + input_state);
//        Log.e("mmmmmmmmm", "step1");
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (cancelPropVisible()) return true;
//
//            if (ll_emoji.getVisibility() == View.VISIBLE) {
//                ll_emoji.setVisibility(View.GONE);
//                emoji_face_dark.setVisibility(View.VISIBLE);
//                emoji_face_hightlight.setVisibility(View.INVISIBLE);
//                return true;
//            }
//
//            if (input_state == true) {
//                rl_bottom.setVisibility(View.VISIBLE);
//                rl_input.setVisibility(View.INVISIBLE);
//                return true;
//            } else if (rl_input.getVisibility() == View.VISIBLE) {
//                rl_bottom.setVisibility(View.VISIBLE);
//                rl_input.setVisibility(View.INVISIBLE);
//                return true;
//            } else {
//                Log.e("mmmmmmmmm", "step8");
//                finish();
//            }
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//
//    boolean cancelPropVisible() {
//        if (fl_prop.getVisibility() == View.VISIBLE) {
//            Log.e("mmmmmmmmm", "step2");
//            fl_prop.setVisibility(View.INVISIBLE);
//            ll_chat.setVisibility(View.VISIBLE);
//            return true;
//        }
//        return false;
//    }
////    public static Handler handler = new Handler(){
////        @Override
////        public void handleMessage(Message msg) {
////            switch (msg.what){
////                case 1:
////                    int p = msg.arg1;
////                    love_icon_progressBar.setProgress(p);
////            }
////            super.handleMessage(msg);
////        }
////    };
//
//    OnClickListener mOnClickEvent = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
////			if (v.getId() == R.id.player_exit) {
////				mVideoView.release_resource();
////				onDestroy();
////				finish();
////			}
//        }
//    };
//
//    OnShownListener mOnShowListener = new OnShownListener() {
//
//        @Override
//        public void onShown() {
////            mPlayToolbar.setVisibility(View.VISIBLE);
////            mPlayToolbar.requestLayout();
//            mVideoView.invalidate();
////            mPlayToolbar.postInvalidate();
//        }
//    };
//
//    OnHiddenListener mOnHiddenListener = new OnHiddenListener() {
//
//        @Override
//        public void onHidden() {
////            mPlayToolbar.setVisibility(View.INVISIBLE);
//        }
//    };
//
//    public void setFileName(String name) { //设置文件名并显示出来
//        mTitle = name;
////		if (mFileName != null)
////			mFileName.setText(mTitle);
////
////		mFileName.setGravity(Gravity.CENTER);
//    }
//
//    @Override
//    protected void onStop() {
//        Log.d(TAG, "NEVideoPlayerActivity onStop");
//        super.onStop();
//
//    }
//
//    @Override
//    protected void onPause() {
//        Log.d(TAG, "NEVideoPlayerActivity onPause");
//
//        if (pauseInBackgroud)
//            mVideoView.pause(); //锁屏时暂停
//
//        mDanmakuView.hide();
//
////        isWatch = false;
//        timer.cancel();
//        timer = null;
//        super.onPause();
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        new DefaultParamsHttpCompt("http://dev.golomee.com/api/v1/user/exitAnchorRoom") {
//            @Override
//            public void callback(Message msg, boolean error, Activity aty) {
//                super.callback(msg, error, aty);
//                RongIMClient.getInstance().quitChatRoom(PublicLayout.RongyunTargetid, new RongIMClient.OperationCallback() {
//                    @Override
//                    public void onSuccess() {
//                        Log.i("", "--------- quit success");
//                    }
//
//                    @Override
//                    public void onError(RongIMClient.ErrorCode errorCode) {
//                        Log.i("", "--------- quit success " + errorCode);
//                    }
//                });
//            }
//        }.addParam("UserId", User.getInstance().userid).addParam("encode", "0").addParam("RoomId", PublicLayout.RoomId).start();
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.d(TAG, "NEVideoPlayerActivity onDestroy");
//        mVideoView.release_resource();
//
//
//
//
//        mDanmakuView.clear();
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onStart() {
//        Log.d(TAG, "NEVideoPlayerActivity onStart");
//        if (FullScreenPlayActivity.mCurrentPosition != -1) {
//            mVideoView.seekTo(FullScreenPlayActivity.mCurrentPosition);
//            FullScreenPlayActivity.mCurrentPosition = -1;
//        }
//        super.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        activityRootView.addOnLayoutChangeListener(onLayoutChangeListener);
//        Log.d(TAG, "NEVideoPlayerActivity onResume");
//        if (pauseInBackgroud && !mVideoView.isPaused()) {
//            mVideoView.start(); //锁屏打开后恢复播放
//        }
//
//        progress = ((Integer) SPUtil.getInstance().getValue("passedtime", Integer.class)).intValue();
//
//        if (progress >= 15 || progress < 0) {
//            progress = 0;
//        }
//
//        calTime();
//
//        mDanmakuView.show();
//        ((TextView) findViewById(R.id.sum)).setText(User.getInstance().getMoney());
//        super.onResume();
//    }
//
//    @Override
//    protected void onRestart() {
//        Log.d(TAG, "NEVideoPlayerActivity onRestart");
//
//
//        super.onRestart();
//    }
//
////    private List<IDanmakuItem> initItems() {
////        List<IDanmakuItem> list = new ArrayList<>();
////        for (int i = 0; i < 10; i++) {
////            IDanmakuItem item = new DanmakuItem(this, i + " : plain text danmuku", mDanmakuView.getWidth());
////            list.add(item);
////        }
////
////        String msg = " : text with image   ";
////        for (int i = 0; i < 10; i++) {
////            ImageSpan imageSpan = new ImageSpan(this, R.drawable.ic_gift02);
////            SpannableString spannableString = new SpannableString(i + msg);
////            spannableString.setSpan(imageSpan, spannableString.length() - 2, spannableString.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
////            IDanmakuItem item = new DanmakuItem(this, spannableString, mDanmakuView.getWidth(), 0, 0, 0, 1.5f);
////            list.add(item);
////        }
////        return list;
////    }
//
//    @Nullable
//    public static String getPulltreamUrl(List<Map<String, String>> list, Context ctx) {
//        String url = "";
//        if (list.size() == 1) {
//            url = list.get(0).get("url");
//        } else {
//            if (WIFIUtil.isWIFI(ctx)) {
//                for (Map m : list) {
//                    if (m.containsKey("level") && m.get("level").equals("sd")) {
//                        url = (String) m.get("url");
//                    }
//                }
//                if (url.equals("") && list.size() > 0) {
//                    url = list.get(0).get("url");
//                }
//            } else if (WIFIUtil.isMobile(ctx)) {
//                for (Map m : list) {
//                    if (m.containsKey("level") && m.get("level").equals("sd")) {
//                        url = (String) m.get("url");
//                    }
//                }
//                if (url.equals("") && list.size() > 0) {
//                    url = list.get(0).get("url");
//                }
//            }
//
//        }
//
//        return url;
//    }
//
//
//    private int mLayout = mVideoView.VIDEO_SCALING_MODE_FIT;
//
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        NEVideoPlayerActivity.this.getResources().getConfiguration();
////        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
//        if (NEVideoPlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//
//            video_frame.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            mLayout = mVideoView.VIDEO_SCALING_MODE_FIT_HEIGHT;
//            fl_talk.setVisibility(View.GONE);
//
//            FullScreenMode = true;
//            video_size_scale_max.setVisibility(View.INVISIBLE);
//            video_size_scale_min.setVisibility(View.VISIBLE);
//
//            Log.e("current step executive", "onConfiguration changed, now is landscape");
////        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//
//        } else if (NEVideoPlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//
//            video_frame.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (246 * ScreenSchema.getDensity())));
//
//            mLayout = mVideoView.VIDEO_SCALING_MODE_FIT;//原始尺寸
//            fl_talk.setVisibility(View.VISIBLE);
//
//            FullScreenMode = false;
//            video_size_scale_max.setVisibility(View.VISIBLE);
//            video_size_scale_min.setVisibility(View.INVISIBLE);
//
//            Log.e("current step executive", "onConfiguration changed, now is portrait");
//        }
//        if (mVideoView != null)
//            mVideoView.setVideoScalingMode(mLayout);
//    }
//
//    int frameheight;
//    int height;
//    private int public_layout_height;
//    private int r_public_layout_height;
//    View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() {
//        @Override
//        public void onLayoutChange(View v, int left, int top, int right,
//                                   int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//
//
//            if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
////                anchor_message.setVisibility(View.GONE);
////                View video_frame = findViewById(R.id.fl_talk);
////                View r_public_layout = findViewById(R.id.rl_bottom);
////                View public_layout = findViewById(R.id.fl_bottom);
////                if (height == 0) {
////                    height = v.getMeasuredHeight();
////                    frameheight = video_frame.getLayoutParams().height;
////                    public_layout_height = public_layout.getLayoutParams().height;
////                    r_public_layout_height = r_public_layout.getLayoutParams().height;
////                }
////
////                if (frameheight - height < public_layout_height*3) {
////                    video_frame.getLayoutParams().height = v.getMeasuredHeight() + 150;
////                    video_frame.measure(video_frame.getMeasuredWidth(), video_frame.getLayoutParams().height);
////                    video_frame.requestLayout();
////                    video_frame.invalidate();
////
////                }
//////                Toast.makeText(NEVideoPlayerActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
////
////            } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
////                anchor_message.setVisibility(View.VISIBLE);
////                if (frameheight - height < public_layout_height*3) {
//////                    View video_frame = findViewById(R.id.fl_talk);
//////                    View public_layout = findViewById(R.id.fl_bottom);
//////                    View r_public_layout = findViewById(R.id.rl_bottom);
//////                    r_public_layout.measure(r_public_layout.getMeasuredWidth(), 0 );
//////                    video_frame.measure(video_frame.getMeasuredWidth(), 0);
//////
//////                    public_layout.measure(public_layout.getMeasuredWidth(), 0 );
//////                    r_public_layout.setFitsSystemWindows(true);
//////                    video_frame.setFitsSystemWindows(true);
//////                    public_layout.setFitsSystemWindows(true);
//////                    public_layout.requestLayout();
//////                    public_layout.invalidate();
//////
//////                    video_frame.requestLayout();
//////                    video_frame.invalidate();
//////                    video_frame.getLayoutParams().height =frameheight+150;
////                    video_frame.invalidate();
////                    video_frame.requestLayout();
////                }
//
//
////                Toast.makeText(NEVideoPlayerActivity.this, bottom + " " + oldBottom + "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    };
//
//}
//
