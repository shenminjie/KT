package com.newer.kt.download;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.storage.StorageManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.utils.SPUtil;

import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.vov.vitamio.utils.Log;

/**
 * Created by litli on 2017/3/15.
 */

public class DownloadTrigger {
    private static final String DOWNLOAD = "download";
    public static String prentfolder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/KT/";
    private static final int QUERY = 0;
    public static Map<String, Long> longids = new TreeMap<String, Long>();
    public static Map<String, String> paths = new TreeMap<String, String>();

    public static void trigger(String downloadUrl, String parentfolderfile, Context ctx) {
        downloadFilePath = parentfolderfile;
        //downloadUrl为下载地址
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));

//设置文件下载目录和文件名
        request.setDestinationUri(Uri.fromFile(new File(downloadFilePath)));

////设置下载中通知栏提示的标题
//        request.setTitle("test apk");
//
////设置下载中通知栏提示的介绍
//        request.setDescription("测试下载中");

/*
表示下载进行中和下载完成的通知栏是否显示，默认只显示下载中通知，
VISIBILITY_HIDDEN表示不显示任何通知栏提示，
这个需要在AndroidMainfest中添加权限
*/
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);

/*
表示下载允许的网络类型，默认在任何网络下都允许下载，
有NETWORK_MOBILE、NETWORK_WIFI、NETWORK_BLUETOOTH三种及其组合可供选择；
如果只允许wifi下载，而当前网络为3g，则下载会等待
*/
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

//移动网络情况下是否允许漫游
//request.setAllowedOverRoaming(true);

//表示允许MediaScanner扫描到这个文件，默认不允许
//request.allowScanningByMediaScanner();

/*
设置下载文件的mineType,
因为下载管理Ui中点击某个已下载完成文件及下载完成点击通知栏提示都会根据mimeType去打开文件，
所以我们可以利用这个属性。比如上面设置了mimeType为application/package.name，
我们可以同时设置某个Activity的intent-filter为application/package.name，用于响应点击的打开文件
*/
//request.setMimeType("application/package.name");

//添加请求下载的网络链接的http头，比如User-Agent，gzip压缩等
//request.addRequestHeader(String header, String value);
/*
调用downloadManager的enqueue接口进行下载，
返回唯一的downloadId用于下载状态查询和下载完成监听
*/
        DownloadManager downloadManager;
        downloadManager = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = downloadManager.enqueue(request);
        longids.put(downloadUrl, downloadId);
        paths.put(downloadUrl, downloadFilePath);

    }

    List<String> triggered = new ArrayList<String>();
    static String downloadFilePath;

    public static String getPath(Context ctx) {
        StorageManager sm = (StorageManager) ctx.getSystemService(Context.STORAGE_SERVICE);
        // 获取sdcard的路径：外置和内置
        String[] paths = new String[0];
        try {
            paths = (String[]) sm.getClass().getMethod("getVolumePaths", anull).invoke(sm, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Log.d("tag","");
        return paths[0];
    }


    static Map<String, Boolean> status = new TreeMap<String, Boolean>();

    public static void asycheck(final Context ctx, final String url, final View click, final ProgressBar progressbar, final TextView text) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                final String sfile = prentfolder + "" + url.substring(url.lastIndexOf("/") + 1);
                if (new File(sfile).exists()) {

                    try {
                        if (SPUtil.getValue(ctx,"download",url,String.class)!=null) {
                            ctx.sendBroadcast(new Intent("playvideo").putExtra("url", url));
                            return;
                        } else {
                            query(ctx, url, click, progressbar, text);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    query(ctx, url, click, progressbar, text);

                }
                        return;
                    }

        }.start();
    }

    public static boolean query(final Context ctx, final String url, final View click, final ProgressBar progressbar, final TextView text) {
        String sfile = prentfolder + "" + url.substring(url.lastIndexOf("/") + 1);
        if (!new File(sfile).exists()) {
            new File(sfile).getParentFile().mkdirs();
            trigger(url, sfile, ctx);
        }else if(SPUtil.getValue(ctx,"download",url,String.class)!=null){
            return true;
        }

        ScheduledExecutorService scheduledExecutorService = null;
        Future<?> future = null;

//下载的文件存储名  
        String downloadFilePath = paths.get(url);

        if (downloadFilePath == null) {
            new File(sfile).delete();
            query(ctx, url, click, progressbar, text);
            return true;
        }
        final File downFile = new File(downloadFilePath);
        final long[] fileTotalSize = new long[1];
        final Future<?> finalFuture = future;
        final ScheduledExecutorService finalScheduledExecutorService = scheduledExecutorService;
        final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == QUERY) {
                    DownloadManager downloadManager = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(longids.get(url));
                    Cursor cursor = downloadManager.query(query);

                    if (cursor != null && cursor.moveToFirst()) {
                        //此处直接查询文件大小
                        long downSize = downFile.length();

                        //获取文件下载总大小
                        fileTotalSize[0] = cursor.getLong(cursor.getColumnIndex(
                                DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                        cursor.close();


                        if (fileTotalSize[0] != 0) {
                            int percentage = (int) (downSize * 100 / fileTotalSize[0]);
                            if (progressbar != null) {
                                status.put(url, null);

                                progressbar.setVisibility(View.VISIBLE);
                                progressbar.setProgress(percentage);
                                if (percentage == 100) {
                                    SPUtil.putValue(ctx,"download",url,"true");
                                    progressbar.setVisibility(View.GONE);
                                    ctx.sendBroadcast(new Intent("playvideo").putExtra("url", url).putExtra("viewid",click.getId()));
                                    ((TextView)((View)progressbar.getParent()).findViewById(R.id.schedule_group_item_img)).setText("");
                                    status.put(url, true);

                                    return false;
                                }
                            }
                            if (text != null) {
                                text.setText(percentage + "%");
                            }
                        }

                        //终止轮询task
                        if (fileTotalSize[0] == downSize)
                            if(finalFuture!=null){
                                finalFuture.cancel(true);
                            }
                    }
                }
                return false;
            }
        });
        //每过100ms通知handler去查询下载状态
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        final ScheduledExecutorService finalScheduledExecutorService1 = scheduledExecutorService;
        future = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (status.get(url) == null || status.get(url).toString().equals("")) {
                    Message msg = mHandler.obtainMessage();
                    msg.what = DownloadTrigger.QUERY;
                    mHandler.sendMessage(msg);
                } else {
                    finalScheduledExecutorService1.shutdownNow();
                }

            }
        }, 0, 100, TimeUnit.MILLISECONDS);

//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//
//    scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        }
//
//        @Override
//        protected void onDestroy() {
//    super.onDestroy();
//    if (future != null && !future.isCancelled())
//        future.cancel(true);
//
//    if (scheduledExecutorService != null &&
//            !scheduledExecutorService.isShutdown())
//        scheduledExecutorService.shutdown();
//        }
//
//    }
//}
        return true;
    }

    public static void clearUnDownloded() {
        for (String s : status.keySet()) {
            new File(s).delete();
        }

    }

    public static long getFileSize(String urlString) throws IOException, Exception {
        long lenght = 0;
//        String url = URLEncoder.encode(urlString, "UTF-8");
        //URL mUrl =  new URL(urlString);
        URL mUrl = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept-Encoding", "identity");
        conn.setRequestProperty("Referer", urlString);
        //conn.setRequestProperty("Referer", urlString);
//        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.connect();

        int responseCode = conn.getResponseCode();
        // 判断请求是否成功处理
        if (responseCode == HttpStatus.SC_OK) {
            lenght = conn.getContentLength();
        }

        return lenght;
    }

    public static long getUrlSize(String urla)throws Exception{
        URL url = new URL(urla);
        URLConnection uc = url.openConnection();
        String fileName = uc.getHeaderField(6);
        fileName = URLDecoder.decode(fileName.substring(fileName.indexOf("filename=")+9),"UTF-8");
        return uc.getContentLength();
    }


}
