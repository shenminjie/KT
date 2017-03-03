package com.newer.kt.ktmatch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.newer.kt.R;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by bajieaichirou on 17/3/1.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Map<String, String>> valueList;
    private Map<String, String> map;

    public TeamAdapter(Context context, ArrayList<Map<String, String>> list) {
        this.mContext = context;
        this.valueList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_team_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mNameTxt.setText(valueList.get(position).get("user_id"));
        holder.mClassTxt.setText(valueList.get(position).get("school_cls").replaceAll("\"",""));
        holder.mForceTxt.setText(valueList.get(position).get("power"));
        holder.mGradeTxt.setText(valueList.get(position).get("school_grade"));
        if(holder.mGradeTxt.getText().equals("null")){
            holder.mGradeTxt.setText("--");
        }
        if(holder.mClassTxt.getText().equals("null")){
            holder.mClassTxt.setText("--");
        }

        holder.mSoreTxt.setText(valueList.get(position).get("scores"));
        holder.mSexImg.setImageResource(valueList.get(position).get("gender").equals("MM")?R.mipmap.nv:R.mipmap.nan);
//        holder.mIcon.setImageResource(valueList.get(position).get("gender").equals("MM")?R.mipmap.nv:R.mipmap.nan);

        if(!ImageLoader.getInstance().isInited()){
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    mContext)
                    // max width, max height，即保存的每个缓存文件的最大长宽
                    .memoryCacheExtraOptions(480, 800)
//                    // Can slow ImageLoader, use it carefully (Better don't use it)设置缓存的详细信息，最好不要设置这个
//                    .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null)
                    // 线程池内加载的数量
                    .threadPoolSize(3)
                    // 线程优先级
                    .threadPriority(Thread.NORM_PRIORITY - 2)
            /*
             * When you display an image in a small ImageView
             *  and later you try to display this image (from identical URI) in a larger ImageView
             *  so decoded image of bigger size will be cached in memory as a previous decoded image of smaller size.
             *  So the default behavior is to allow to cache multiple sizes of one image in memory.
             *  You can deny it by calling this method:
             *  so when some image will be cached in memory then previous cached size of this image (if it exists)
             *   will be removed from memory cache before.
             */
                    .denyCacheImageMultipleSizesInMemory()

                    // You can pass your own memory cache implementation你可以通过自己的内存缓存实现
                    // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                    // .memoryCacheSize(2 * 1024 * 1024)
                    //硬盘缓存50MB
                    .diskCacheSize(50 * 1024 * 1024)
                    //将保存的时候的URI名称用MD5
                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                    // 加密
                    .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    .diskCacheFileCount(100) //缓存的File数量
                    // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                    // .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
                    // 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                    .writeDebugLogs() // Remove for release app
                    .build();
            // Initialize ImageLoader with configuration.
            ImageLoader.getInstance().init(config);
        }
        ImageLoader.getInstance().displayImage(valueList.get(position).get("avatar").toString(),holder.mIcon,getListOptions());
    }
    public static DisplayImageOptions getListOptions() {
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
    public int getItemCount() {
        return valueList == null ? 0 : valueList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTxt, mGradeTxt, mClassTxt, mForceTxt, mSoreTxt;
        private ImageView mIcon, mSexImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            mNameTxt = (TextView) itemView.findViewById(R.id.item_name);
            mGradeTxt = (TextView) itemView.findViewById(R.id.item_grade);
            mClassTxt = (TextView) itemView.findViewById(R.id.item_class);
            mForceTxt = (TextView) itemView.findViewById(R.id.item_force);
            mSoreTxt = (TextView) itemView.findViewById(R.id.item_score);
            mSexImg = (ImageView) itemView.findViewById(R.id.item_sex_img);
            mIcon = (ImageView) itemView.findViewById(R.id.item_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChooseMatcherActivity.teamName = mNameTxt.getText().toString();
                    ((Activity)v.getContext()).finish();

                }
            });
        }
    }
}
