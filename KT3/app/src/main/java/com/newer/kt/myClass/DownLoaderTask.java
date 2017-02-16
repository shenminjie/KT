package com.newer.kt.myClass;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.util.Log;

import com.newer.kt.Refactor.ui.Avtivity.BigClassDetailActivity;
/**
 * Created by Administrator on 2016/12/27.
 */
public class DownLoaderTask extends AsyncTask<Void, Integer, Long> {
    private final String TAG = "DownLoaderTask";
    private URL mUrl;
    private File mFile;
    private int maxSize = 0;
    private int mProgress = 0;
    private ProgressReportingOutputStream mOutputStream;
    private Context mContext;
    public DownLoaderTask(String url,String out,Context context){
        super();

        this.mContext = context;

        try {

            String url_str = URLEncoder.encode(url, "UTF-8");

            url_str = url_str.replace("%3A", ":");
            url_str = url_str.replace("%2F", "/");
            mUrl = new URL(url_str);
//            String fileName = new File(mUrl.getFile()).getName();
            String fileName = new File(new URL(url).getFile()).getName();
            mFile = new File(out, fileName);
            System.out.println("fileName================="+mFile.getName());
            Log.d(TAG, "out="+out+", name="+fileName+",mUrl.getFile()="+mUrl.getFile());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        //super.onPreExecute();
        System.out.println("========================onPreExecute");
        ((BigClassDetailActivity)mContext).downloadProgress(0);

    }

    @Override
    protected Long doInBackground(Void... params) {
        // TODO Auto-generated method stub
        return download();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        System.out.println("??????????????????????????????????????????"+(values[0].intValue()*1.0)+"=="+(values[0].intValue())/(maxSize/100));
        ((BigClassDetailActivity)mContext).downloadProgress((values[0].intValue())/(maxSize/100));
//        if(values.length>0){
//            int contentLength = values[1];
//            if(contentLength!=-1){
////                ((BigClassDetailActivity)mContext).downloadProgress(values[0].intValue()*100);
//                ((BigClassDetailActivity)mContext).downloadProgress((values[0].intValue()*100)/maxSize);
//            }
//        }
    }

    @Override
    protected void onPostExecute(Long result) {

        ((BigClassDetailActivity)mContext).doZipExtractorWork();
    }

    private long download(){
        HttpURLConnection connection = null;
        int bytesCopied = 0;
        try {
            connection = (HttpURLConnection)mUrl.openConnection();
            connection.setRequestProperty("Accept-Encoding", "identity");
            // 设置连接属性
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            int code = connection.getResponseCode();
            int length = connection.getContentLength();
            maxSize = length;
            if(mFile.exists()&&length == mFile.length()){
                Log.d(TAG, "file "+mFile.getName()+" already exits!!");
                return 0l;
            }
            mOutputStream = new ProgressReportingOutputStream(mFile);
            publishProgress(0,length);
            bytesCopied =copy(connection.getInputStream(),mOutputStream);
            if(bytesCopied!=length&&length!=-1){
                Log.e(TAG, "Download incomplete bytesCopied="+bytesCopied+", length"+length);
            }
            mOutputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytesCopied;
    }
    private int copy(InputStream input, OutputStream output){
        byte[] buffer = new byte[1024*8];
        BufferedInputStream in = new BufferedInputStream(input, 1024*8);
        BufferedOutputStream out  = new BufferedOutputStream(output, 1024*8);
        int count =0,n=0;
        try {
            while((n=in.read(buffer, 0, 1024*8))!=-1){
                out.write(buffer, 0, n);
                count+=n;
            }
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return count;
    }
    private final class ProgressReportingOutputStream extends FileOutputStream {

        public ProgressReportingOutputStream(File file)
                throws FileNotFoundException {
            super(file);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void write(byte[] buffer, int byteOffset, int byteCount)
                throws IOException {
            // TODO Auto-generated method stub
            super.write(buffer, byteOffset, byteCount);
            mProgress += byteCount;
            publishProgress(mProgress);
        }

    }
}

