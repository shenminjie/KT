package com.newer.kt.zqk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.NEVideoPlayerActivity;
import com.newer.kt.NEVideoView;
import com.newer.kt.R;
import com.newer.kt.download.DownloadTrigger;
import com.newer.kt.utils.SPUtil;
import com.sina.weibo.sdk.api.share.Base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ZQKVIdeoActivity extends NEVideoPlayerActivity {
    BaseExpandableListAdapter adapter;
    List<String> titles = new ArrayList<String>();
    Map<String, ArrayList<Map>> maps = new TreeMap<String, ArrayList<Map>>();
    private BroadcastReceiver brr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final int id = getIntent().getIntExtra("viewid",0);
            if(id!=0){
                new Handler(Looper.getMainLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        ((TextView)findViewById(id)).setText("播放");
                        if(adapter!=null){
                            adapter.notifyDataSetChanged();
                        }
                        return false;
                    }
                }).sendEmptyMessage(0);
            }
        }
    };

    private void play(String p) {
        final String[] path = {p};
        if (path[0] != null) {
            path[0] = Environment.getExternalStorageDirectory() + "/KT/" + path[0].substring(path[0].lastIndexOf("/") + 1);
//                view.setVisibility(View.GONE);
            setControllerVisible(false);
            start(path[0]);
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(brr);
        super.onDestroy();
    }

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zqkvideo);
        view = findViewById(R.id.toppic);
        init();
        titles = (List<String>) getIntent().getSerializableExtra("titles");
        maps = (Map<String, ArrayList<Map>>) getIntent().getSerializableExtra("content");
        IntentFilter intent = new IntentFilter();
        intent.addAction("playvideo");
        registerReceiver(brr, intent);
        adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return titles.size();
            }

            @Override
            public int getChildrenCount(int i) {
                return maps.get(getGroup(i).toString()).size();
            }

            @Override
            public Object getGroup(int i) {
                return titles.get(i);
            }

            @Override
            public Object getChild(int i, int i1) {
                return maps.get(getGroup(i).toString()).get(i1);
            }

            @Override
            public long getGroupId(int i) {
                return 0;
            }

            @Override
            public long getChildId(int i, int i1) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(getBaseContext()).inflate(R.layout.adapter_schedule_list_group, null);
                    view.setBackgroundColor(getBaseContext().getResources().getColor(R.color.colorGray));
                }
                ((TextView) view.findViewById(R.id.schedule_group_item_txt)).setText(getGroup(i).toString());
                return view;
            }

            @Override
            public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(getBaseContext()).inflate(R.layout.adapter_schedule_list_group_child, null);
                }
                ((TextView) view.findViewById(R.id.schedule_group_item_txt)).setText(((Map) getChild(i, i1)).get("child_title").toString());
                view.findViewById(R.id.schedule_group_item_img).setBackgroundResource(R.drawable.shape_radio_bofang);
                final View finalView = view;
                ((TextView) view.findViewById(R.id.schedule_group_item_img)).setText("下载");
                view.findViewById(R.id.schedule_group_item_img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((TextView) v).getText().toString().equals("播放")) {
                            String url = ((Map) getChild(i, i1)).get("gym_video_url").toString();
                                       String path = Environment.getExternalStorageDirectory() + "/KT/" + url.substring(url.lastIndexOf("/") + 1);

                            findViewById(R.id.bg).setVisibility(View.GONE);
                            init();
                            start(path);
                            findViewById(R.id.stop_resume).setVisibility(View.VISIBLE);
                        } else {

                            boolean downloaded = DownloadTrigger.query(finalView.getContext(), ((Map) getChild(i, i1)).get("gym_video_url").toString(), finalView, (ProgressBar) finalView.findViewById(R.id.progbar), null);
                            if(downloaded){
//                                ((TextView) v).setText("播放");
//                                v.performClick();
                            }
                        }

                    }
                });
                final TextView download = (TextView) view.findViewById(R.id.schedule_group_item_img);

                String url = ((Map) getChild(i, i1)).get("gym_video_url").toString();
                        final String sfile = DownloadTrigger.prentfolder + "" + url.substring(url.lastIndexOf("/") + 1);
                        if (new File(sfile).exists()) {

                            try {
                                if (
                                        SPUtil.getValue(getBaseContext(), "download", url, String.class) != null ) {
                                    ((Map) getChild(i, i1)).put("download_status", "1");
                                            download.setText("播放");
                                } else {
                                    ((Map) getChild(i, i1)).put("download_status", "0");


                                            download.setText("下载");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
//                                new Handler(Looper.getMainLooper(), new Handler.Callback() {
//                                    @Override
//                                    public boolean handleMessage(Message message) {
//                                        download.setVisibility(View.GONE);
//                                        return false;
//                                    }
//                                }).sendEmptyMessage(0);
                            }
                        } else {
                            ((Map) getChild(i, i1)).put("download_status", "0");
                            download.setText("下载");

                        }
                return view;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return false;
            }
        };
        ((ExpandableListView) findViewById(R.id.expandable)).setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            ((ExpandableListView) findViewById(R.id.expandable)).expandGroup(i);
        }
        ((ExpandableListView) findViewById(R.id.expandable)).setGroupIndicator(null);
//        ((ExpandableListView)findViewById(R.id.expandable));

//        ((ScrollView) findViewById(R.id.scroll)).getChildAt(0).scrollTo(0, 0);
        findViewById(R.id.stop_resume).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mVideoView.isPlaying()){
                mVideoView.pause();
                    view.setBackgroundResource(R.drawable.resume);
                }else{
                    mVideoView.start();

                    view.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
