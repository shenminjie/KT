package com.newer.kt.Refactor.ui.Fragment.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bajie.demo.view.CustomRecyclerView;
import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.ktmatch.ChooseMatcherActivity;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ktmatch.TeamAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.sina.weibo.sdk.api.share.Base;

import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shengchengerweima.CamScanActivity;

public class Stu_Manager extends BaseActivity implements View.OnClickListener {

    private List rt;
    private TeamAdapter adapter;
    private ImageView image_vs_item_back;
    private PopupWindow mPopWindow;
    private TextView mMenuTv;
    private ImageView iv_addStu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu__manager);
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));
        iv_addStu = ((ImageView) findViewById(R.id.iv_addStu));
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        iv_addStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });


        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getThis())
                .getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);
        QueryBuilder.build("offline/get_club_data").add("club_id", clubid).get(new QueryBuilder.EnhancedCallback("users") {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {
                rt = (List) object;
                setData((ArrayList) rt);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
    }


    private void setData(ArrayList valueList) {
        if (adapter == null) {
            adapter = new TeamAdapter(this, valueList);
            ((ListView) findViewById(R.id.lv_class_stuManager)).setAdapter(adapter);
        } else {
            adapter.getList().addAll(valueList);
            adapter.notifyDataSetChanged();
        }
//        ((CustomRecyclerView)findViewById(R.id.lv_class_stuManager)).setPullLoadMoreCompleted();
    }

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    public class TeamAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<Map<String, String>> valueList;
        private Map<String, String> map;

        public TeamAdapter(Context context, ArrayList<Map<String, String>> list) {
            this.mContext = context;
            this.valueList = list;
        }

        public DisplayImageOptions getListOptions() {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    // 设置图片在下载期间显示的图片
                    .showImageOnLoading(R.drawable.default_head)
                    // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageForEmptyUri(R.drawable.default_head)
                    // 设置图片加载/解码过程中错误时候显示的图片
                    .showImageOnFail(R.drawable.default_head)
                    // 设置下载的图片是否缓存在内存中
                    .cacheInMemory(false)
                    // 设置下载的图片是否缓存在SD卡中
                    .cacheOnDisc(true)
                    // 保留Exif信息
                    .considerExifParams(true)
                    // 设置图片以如何的编码方式显示
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                    // 设置图片的解码类型
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    // .decodingOptions(android.graphics.BitmapFactory.Options
                    // decodingOptions)//设置图片的解码配置
                    .considerExifParams(true)
                    // 设置图片下载前的延迟
                    .delayBeforeLoading(100)// int
                    // delayInMillis为你设置的延迟时间
                    // 设置图片加入缓存前，对bitmap进行设置
                    // .preProcessor(BitmapProcessor preProcessor)
                    .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                    // .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                    .build();
            return options;
        }

        public ArrayList<Map<String, String>> getList() {
            return valueList;
        }

        public void setList(ArrayList<Map<String, String>> list) {
            this.valueList = list;
        }

        @Override
        public int getCount() {
            return valueList == null ? 0 : valueList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View itemView, ViewGroup viewGroup) {
            if (itemView == null) {
                itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_team_item, null);

            }
            TextView mNameTxt = (TextView) itemView.findViewById(R.id.item_name);
            TextView mGradeTxt = (TextView) itemView.findViewById(R.id.item_grade);
            TextView mClassTxt = (TextView) itemView.findViewById(R.id.item_class);
            TextView mForceTxt = (TextView) itemView.findViewById(R.id.item_force);
            TextView mSoreTxt = (TextView) itemView.findViewById(R.id.item_score);
            ImageView mSexImg = (ImageView) itemView.findViewById(R.id.item_sex_img);
            ImageView mIcon = (ImageView) itemView.findViewById(R.id.item_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mNameTxt.setText(valueList.get(position).get("user_id"));
            mClassTxt.setText(valueList.get(position).get("school_cls").replaceAll("\"", ""));
            mForceTxt.setText(valueList.get(position).get("power"));
            mGradeTxt.setText(valueList.get(position).get("school_grade"));
            if (mGradeTxt.getText().equals("null")) {
                mGradeTxt.setText("--");
            }
            if (mClassTxt.getText().equals("null")) {
                mClassTxt.setText("--");
            }

            mSoreTxt.setText(valueList.get(position).get("scores"));
            mSexImg.setImageResource(valueList.get(position).get("gender").equals("MM") ? R.mipmap.nv : R.mipmap.nan);
//        mIcon.setImageResource(valueList.get(position).get("gender").equals("MM")?R.mipmap.nv:R.mipmap.nan);

//            ImageLoader.getInstance().displayImage(valueList.get(position).get("avatar").toString(),mIcon,getListOptions());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(view.getContext(), Student_Info.class));
                }
            });

                    return itemView;
                }

            }


    private void showPopupWindow() {
        View contentView = LayoutInflater.from(Stu_Manager.this).inflate(R.layout.popuplayout, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView pop_addStu = (TextView) contentView.findViewById(R.id.pop_addStu);
        TextView pop_addClass = (TextView) contentView.findViewById(R.id.pop_addClass);
        pop_addStu.setOnClickListener(this);
        pop_addClass.setOnClickListener(this);


        mPopWindow.showAsDropDown(iv_addStu);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pop_addStu: {
                Intent intent = new Intent(getApplicationContext(), CamScanActivity.class);
                startActivity(intent);
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_addClass: {
                Intent intent = new Intent(getApplicationContext(), AddClass.class);
                startActivity(intent);
                mPopWindow.dismiss();
            }
            break;


        }
    }
}

