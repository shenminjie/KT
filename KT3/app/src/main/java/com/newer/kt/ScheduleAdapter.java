package com.newer.kt;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.newer.kt.download.DownloadTrigger;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by bajieaichirou on 17/3/7.
 */
public class ScheduleAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ScheduleInfo> contentList;
    private List<String> titleList;

    public ScheduleAdapter(Context context, List<String> titleList, List<ScheduleInfo> contentList) {
        this.context = context;
        this.contentList = contentList;
        this.titleList = titleList;
    }


    @Override
    public int getGroupCount() {
        return titleList.size();
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return contentList.get(groupPosition).getScheduleList().size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return titleList.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return contentList.get(groupPosition).getScheduleList();
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ScheduleGroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_schedule_list_group, null);
            groupHolder = new ScheduleGroupHolder();
            groupHolder.mGroupTitleTxt = (TextView) convertView.findViewById(R.id.schedule_group_item_txt);
            groupHolder.mGroupImg = (ImageView) convertView.findViewById(R.id.schedule_group_item_img);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ScheduleGroupHolder) convertView.getTag();
        }

        if (!isExpanded) {
            groupHolder.mGroupImg.setBackgroundResource(R.drawable.arrow_down);
        } else {
            groupHolder.mGroupImg.setBackgroundResource(R.drawable.arrow_up);
        }
        groupHolder.mGroupTitleTxt.setText(titleList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = null;
        int type = contentList.get(groupPosition).getScheduleList().get(childPosition).getType();
        if (type == 0) {//目标或者器材
            ScheduleItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_schedule_list_target, null);
                itemHolder = new ScheduleItemHolder();
                itemHolder.mChildTxt = (TextView) convertView.findViewById(R.id.item_target_txt);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ScheduleItemHolder) convertView.getTag();
            }
            itemHolder.mChildTxt.setText(contentList.get(groupPosition).getScheduleList().get(childPosition).getSchedule_value());
        } else {//内容
            ContentHolder contentHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_schedule_list_teach_content, null);
                contentHolder = new ContentHolder();
                contentHolder.mChildTitleTxt = (TextView) convertView.findViewById(R.id.teach_content_item_title);
                contentHolder.mChildTimeTxt = (TextView) convertView.findViewById(R.id.teach_content_item_time);
                contentHolder.mChildContentTitleTxt = (TextView) convertView.findViewById(R.id.teach_content_item_txt);
                contentHolder.mChildContentDetialTxt = (TextView) convertView.findViewById(R.id.teach_content_item_detail);
                contentHolder.mChildImg = (ImageView) convertView.findViewById(R.id.teach_content_item_img);
                contentHolder.mChildLayout = (AutoLinearLayout) convertView.findViewById(R.id.teach_content_title_layout);
                contentHolder.mChildContentLayout = (AutoLinearLayout) convertView.findViewById(R.id.teach_content_layout);
                convertView.setTag(contentHolder);
            } else {
                contentHolder = (ContentHolder) convertView.getTag();
            }
            String childTitle = contentList.get(groupPosition).getScheduleList().get(childPosition).getContentTitle();
            if (childTitle.isEmpty()) {
                convertView.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (ContextCompat.checkSelfPermission(view.getContext(),
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED){
                                    ActivityCompat.requestPermissions((Activity) view.getContext(),
                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            0);
                                }else if(ContextCompat.checkSelfPermission(view.getContext(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED){
                                    ActivityCompat.requestPermissions((Activity) view.getContext(),
                                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            0);
                                }
                                else {
                                    String videourl = (String) contentList.get(groupPosition).getScheduleList().get(childPosition).getData().get("gym_video_url");
                                    DownloadTrigger.query(view.getContext(), videourl, view, (ProgressBar) view.findViewById(R.id.progreebar), null);
                                }
                            }
                        }
                );
                contentHolder.mChildLayout.setVisibility(View.GONE);
                contentHolder.mChildContentLayout.setVisibility(View.VISIBLE);
                contentHolder.mChildContentTitleTxt.setText(contentList.get(groupPosition).getScheduleList().get(childPosition).getChildTitle());
                if (contentList.get(groupPosition).getScheduleList().get(childPosition).getData().containsKey("image")) {
                    ImageLoader.getInstance().displayImage(contentList.get(groupPosition).getScheduleList().get(childPosition).getData().get("image").toString(), contentHolder.mChildImg);
                }
            } else {
                convertView.setOnClickListener(null);
                contentHolder.mChildLayout.setVisibility(View.VISIBLE);
                contentHolder.mChildContentLayout.setVisibility(View.GONE);
                contentHolder.mChildTitleTxt.setText(childTitle);
                contentHolder.mChildTimeTxt.setText(contentList.get(groupPosition).getScheduleList().get(childPosition).getData().get("duration") + "");

            }
            contentHolder.mChildContentDetialTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String videourl = (String) contentList.get(groupPosition).getScheduleList().get(childPosition).getData().get("gym_video_url");

                    Intent in = new Intent(context, ActivityScheduleDetail.class).putExtra("video_path",videourl);
                    in.putExtra("detail_title", "热身 准备活动");
                    in.putExtra("detail_classfy", "KT足球游戏");
                    in.putExtra("detail_organization", "学生站列一排或两排进行对齐,围着操场进行热身慢跑");
                    in.putExtra("detail_requirement", "1、不要低头,要抬头,双眼注视前方\\n2、跑步时,双手自然放松,拳头不要握得太紧,也可以伸开双手,掌心向内。\\n 3、双脚落地要轻快\n" +
                            "                   \"");

                    context.startActivity(in);
                }
            });
        }
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

class ScheduleGroupHolder {
    public TextView mGroupTitleTxt;
    public ImageView mGroupImg;

}

class ScheduleItemHolder {
    public TextView mChildTxt;
}

class ContentHolder {
    public TextView mChildTitleTxt;
    public TextView mChildTimeTxt;
    public TextView mChildContentTitleTxt;
    public TextView mChildContentDetialTxt;
    public ImageView mChildImg;
    public AutoLinearLayout mChildLayout;
    public AutoLinearLayout mChildContentLayout;
}
