package com.newer.kt.Refactor.ui.Avtivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baseproject.utils.Logger;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.MyGridView;
import com.newer.kt.myClass.DownLoaderTask;
import com.newer.kt.myClass.ZipExtractorTask;
import com.youku.player.ApiManager;
import com.youku.player.VideoQuality;
import com.youku.player.base.YoukuBasePlayerManager;
import com.youku.player.base.YoukuPlayer;
import com.youku.player.base.YoukuPlayerView;
import com.youku.player.plugin.YoukuPlayerListener;
import com.youku.service.download.DownloadManager;
import com.youku.service.download.OnCreateDownloadListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * 播放器播放界面，
 * 
 */
public class PlayerActivity extends Activity {


	private static final String TAG = "PlayerActivity";
	MyGridView mGridView;
	private List<String> mList = new ArrayList<>();

//	private YoukuBasePlayerManager basePlayerManager;
//	// 播放器控件
//	private YoukuPlayerView mYoukuPlayerView;
//
//	// 需要播放的视频id
//	private String vid;

	// 清晰度相关按钮
//	private Button btn_standard, btn_hight, btn_super, btn_1080;

	// 下载视频按钮
	private Button btn_download;

	// 需要播放的本地视频的id
	private String local_vid;

	// 需要播放的地址
	private String local_path;

	// 播放标题
	private String local_title;

	private boolean isnewdownloadapi = false;

	// 标示是否播放的本地视频
	private boolean isFromLocal = true;

	private String id = "";

	// YoukuPlayer实例，进行视频播放控制
//	private YoukuPlayer youkuPlayer;
	private RelativeLayout titleBar;
	private RelativeLayout head1;
	private io.vov.vitamio.widget.VideoView full_holder;
	private TextView tv_guankan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.second1);
		iniView();
//		basePlayerManager = new YoukuBasePlayerManager(this) {
//
//			@Override
//			public void setPadHorizontalLayout() {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onInitializationSuccess(YoukuPlayer player) {
//				// TODO Auto-generated method stub
//				// 初始化成功后需要添加该行代码
//				addPlugins();
//
//				System.out.println("=======================================success");
//
//				// 实例化YoukuPlayer实例
//				youkuPlayer = player;
//
//				// 进行播放
////				goPlay();
//
//			}
//
//			@Override
//			public void onSmallscreenListener() {
//				titleBar.setVisibility(View.VISIBLE);
//
//			}
//
//			@Override
//			public void onFullscreenListener() {
//				// TODO Auto-generated method stub
//				titleBar.setVisibility(View.GONE);
//
//			}
//		};
//		basePlayerManager.onCreate();

		// 通过上个页面传递过来的Intent获取播放参数
		getIntentData(getIntent());

//		if (TextUtils.isEmpty(id)) {
//			vid = "XMTU4MzkyNDcxMg=="; // 默认视频
//		} else {
//			vid = id;
//		}

//		// 播放器控件
//		mYoukuPlayerView = (YoukuPlayerView) this
//				.findViewById(R.id.full_holder);
//		// 控制竖屏和全屏时候的布局参数。这两句必填。
//		mYoukuPlayerView
//				.setSmallScreenLayoutParams(new LinearLayout.LayoutParams(
//						LinearLayout.LayoutParams.MATCH_PARENT,
//						LinearLayout.LayoutParams.WRAP_CONTENT));
//		mYoukuPlayerView
//				.setFullScreenLayoutParams(new LinearLayout.LayoutParams(
//						LinearLayout.LayoutParams.MATCH_PARENT,
//						LinearLayout.LayoutParams.MATCH_PARENT));
//		// 初始化播放器相关数据
//		mYoukuPlayerView.initialize(basePlayerManager);
//		// 添加播放器的回调
//		basePlayerManager.setPlayerListener(new YoukuPlayerListener() {
//
//			@Override
//			public void onCompletion() {
//				// TODO Auto-generated method stub
//				super.onCompletion();
//			}
//		});

		initData();
	}

	private void initData() {

		mList.add("XMTU4MzkyNDcxMg==");
		mList.add("XMTU4MzkyNTA3Mg==");
		mList.add("dadasdas");
		mList.add("dadasdas");
		mList.add("dadasdas");
		mList.add("dadasdas");
		mList.add(" dadasdas ");
		mList.add("dadasdas");
		mList.add("dadasdas");
		mList.add("dadasdas");
		mList.add("dadasdas");
		mList.add("dadasdas");

		mGridView.setAdapter(new BaseAdapter() {
			@Override
			public int getCount() {
				return mList.size();
			}

			@Override
			public Object getItem(int position) {
				return mList.get(position);
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
					convertView = getLayoutInflater().inflate(R.layout.item_big_class1, null);
					viewHolder.mTitle = (TextView) convertView.findViewById(R.id.tv_title);
					viewHolder.mBg = (ImageView) convertView.findViewById(R.id.image_bg);
					convertView.setTag(viewHolder);
				} else {
					viewHolder = (ViewHolder1) convertView.getTag();
				}
				viewHolder.mTitle.setText(mList.get(position));
				return convertView;
			}

			class ViewHolder1 {
				ImageView mBg;
				TextView mTitle;
			}
		});

		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(new File(getCacheDir()+"/大课间第一套/大课间第一套.mp4").exists()){
					goPlay();
				}else{
					showDownLoadDialog();
				}




			}
		});
	}

	@Override
	public void onBackPressed() { // android系统调用
		Logger.d("sgh", "onBackPressed before super");
		super.onBackPressed();
		Logger.d("sgh", "onBackPressed");
//		basePlayerManager.onBackPressed();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
//		basePlayerManager.onConfigurationChanged(newConfig);
		//
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		basePlayerManager.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		boolean managerKeyDown = basePlayerManager.onKeyDown(keyCode, event);
//		if (basePlayerManager.shouldCallSuperKeyDown()) {
//			return super.onKeyDown(keyCode, event);
//		} else {
//			return managerKeyDown;
//		}

		return true;

	}

	@Override
	public void onLowMemory() { // android系统调用
		super.onLowMemory();
//		basePlayerManager.onLowMemory();
	}

	@Override
	protected void onPause() {
		super.onPause();
//		basePlayerManager.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
//		basePlayerManager.onResume();
	}

	@Override
	public boolean onSearchRequested() { // android系统调用

//		return basePlayerManager.onSearchRequested();
		 return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
//		basePlayerManager.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
//		basePlayerManager.onStop();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);

		// 通过Intent获取播放需要的相关参数
		getIntentData(intent);

		// 进行播放
//		goPlay();
	}

	/**
	 * 获取上个页面传递过来的数据
	 */
	private void getIntentData(Intent intent) {

		if (intent != null) {
			// 判断是不是本地视频
			isFromLocal = intent.getBooleanExtra("isFromLocal", false);
			isFromLocal = true;
			if (isFromLocal) { // 播放本地视频
				isnewdownloadapi = intent.getBooleanExtra("isnewdownloadapi",
						false);
				if (isnewdownloadapi) {
					local_vid = intent.getStringExtra("local_vid");
					local_path = intent.getStringExtra("local_path");
					local_title = intent.getStringExtra("local_title");
				} else {
					local_vid = intent.getStringExtra("video_id");
				}

			} else { // 在线播放
				id = intent.getStringExtra("vid");
			}
		}

	}

	public void goPlay() {
		// youkuPlayer.playLocalVideo("abc",
		// "http://7xploe.media1.z0.glb.clouddn.com/XMTQ4NzA1Njc0OA==/hd1/p1_308.mp4",
		// "ceshi");

//		youkuPlayer.playLocalVideo();

//		if (isFromLocal) { // 播放本地视频
//			if (isnewdownloadapi) {
//				youkuPlayer.playLocalVideo(local_vid, local_path, local_title);
//			} else {
//				youkuPlayer.playLocalVideo(local_vid);
//			}
//
//		} else { // 播放在线视频
//
//			System.out.println("===================???????????????????????");
//			youkuPlayer.playVideo(vid);
//		}

		// XNzQ3NjcyNDc2
		// XNzQ3ODU5OTgw
		// XNzUyMzkxMjE2
		// XNzU5MjMxMjcy 加密视频
		// XNzYxNzQ1MDAw 万万没想到
		// XNzgyODExNDY4 魔女范冰冰扑倒黄晓明
		// XNDcwNjUxNzcy 姐姐立正向前走
		// XNDY4MzM2MDE2 向着炮火前进
		// XODA2OTkwMDU2 卧底韦恩突出现 劫持案愈发棘手
		// XODUwODM2NTI0 会员视频
		// XODQwMTY4NDg0 一个人的武林



		head1.setVisibility(View.GONE);
		full_holder.setVisibility(View.VISIBLE);
		//验证是否硬件支持
		if(!LibsChecker.checkVitamioLibs(this)){return;}
			full_holder.setVideoPath(getCacheDir() + "/大课间第一套/大课间第一套.mp4");
//        vv.setVideoPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/测试.mp4");
//			full_holder.setMediaController(new MediaController(PlayerActivity.this));

			full_holder.start();
	}

	private void iniView() {

		titleBar = ((RelativeLayout) this.findViewById(R.id.titlebar));
		head1 = ((RelativeLayout) this.findViewById(R.id.head1));

		head1.setFocusable(true);
		head1.setFocusableInTouchMode(true);
		head1.requestFocus();


		full_holder = ((io.vov.vitamio.widget.VideoView) this.findViewById(R.id.full_holder));
		//head1.setVisibility(View.GONE);

		mGridView = (MyGridView)findViewById(R.id.gridView);

		tv_guankan = ((TextView) findViewById(R.id.tv_guankan));
		tv_guankan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(new File(getCacheDir()+"/大课间第一套/大课间第一套.mp4").exists()){
					goPlay();
				}else{
					showDownLoadDialog();
				}


			}
		});


	}
//
//	public View.OnClickListener listener = new View.OnClickListener() {
//
//		@Override
//		public void onClick(View view) {
//			// TODO Auto-generated method stub
//			switch (view.getId()) {
//			case R.id.biaoqing: // 切换标清
//				change(VideoQuality.STANDARD);
//				break;
//			case R.id.gaoqing: // 切换高清
//				change(VideoQuality.HIGHT);
//				break;
//			case R.id.chaoqing: // 切换超清
//				change(VideoQuality.SUPER);
//				break;
//			case R.id.most: // 切换为1080P
//				change(VideoQuality.P1080);
//				break;
//			case R.id.download: // 下载视频接口测试
//				doDownload();
//				break;
//			}
//
//		}
//	};
//
//	/**
//	 * 更改视频的清晰度
//	 *
//	 * @param quality
//	 *            VideoQuality有四种枚举值：{STANDARD,HIGHT,SUPER,P1080}，分别对应：标清，高清，超清，
//	 *            1080P
//	 */
//
//	private void change(VideoQuality quality) {
//		try {
//			// 通过ApiManager实例更改清晰度设置，返回值（1):成功；（0): 不支持此清晰度
//			// 接口详细信息可以参数使用文档
//			int result = ApiManager.getInstance().changeVideoQuality(quality,
//					basePlayerManager);
//			if (result == 0)
//				Toast.makeText(PlayerActivity.this, "不支持此清晰度", Toast.LENGTH_SHORT).show();
//		} catch (Exception e) {
//			Toast.makeText(PlayerActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//		}
//	}
//
//	/**
//	 * 简单展示下载接口的使用方法，用户可以根据自己的 通过DownloadManager下载视频
//	 */
//	private void doDownload() {
//		// 通过DownloadManager类实现视频下载
//		DownloadManager d = DownloadManager.getInstance();
//		/**
//		 * 第一个参数为需要下载的视频id 第二个参数为该视频的标题title 第三个对下载视频结束的监听，可以为空null
//		 */
//		d.createDownload("XNzgyODExNDY4", "魔女范冰冰扑倒黄晓明",
//				new OnCreateDownloadListener() {
//
//					@Override
//					public void onfinish(boolean isNeedRefresh) {
//						// TODO Auto-generated method stub
//
//					}
//				});
//	}


	private void showDownLoadDialog(){
		new AlertDialog.Builder(this).setTitle("确认")
				.setMessage("是否下载？")
				.setPositiveButton("是", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.d(TAG, "onClick 1 = "+which);
						doDownLoadWork();
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.d(TAG, "onClick 2 = "+which);
					}
				})
				.show();
	}

	public void showUnzipDialog(){
		new AlertDialog.Builder(this).setTitle("确认")
				.setMessage("是否解压？")
				.setPositiveButton("是", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.d(TAG, "onClick 1 = "+which);
						doZipExtractorWork();
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.d(TAG, "onClick 2 = "+which);
					}
				})
				.show();
	}

	public void doZipExtractorWork(){
		//ZipExtractorTask task = new ZipExtractorTask("/storage/usb3/system.zip", "/storage/emulated/legacy/", this, true);
		ZipExtractorTask task = new ZipExtractorTask(getCacheDir()+"/大课间第一套.zip", getCacheDir()+"", this, true);
		task.execute();
	}

	private void doDownLoadWork(){
		DownLoaderTask task = new DownLoaderTask("http://public.ktfootball.com/download/recess/大课间第一套.zip", getCacheDir()+"", this);
		//DownLoaderTask task = new DownLoaderTask("http://192.168.9.155/johnny/test.h264", getCacheDir().getAbsolutePath()+"/", this);
		task.execute();
	}

}
