package com.smj.upload;

import android.os.AsyncTask;

import com.newer.kt.Refactor.KTApplication;
import com.newer.kt.ui.upload.UploadListener;
import com.youku.uploader.IUploadResponseHandler;
import com.youku.uploader.YoukuUploader;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * upload
 * Created by shenminJie on 17/3/19.
 */

public class UpLoadManager {
    /**
     * single instance
     */
    private static UpLoadManager manager;

    /**
     * upload queue
     */
    private UploadQueue<UpLoadInfo> mQueue;

    /**
     * upload tools
     */
    private YoukuUploader mYoukuUploader;

    /**
     * upload listener
     */
    private UploadListener mUpLoadListener;

    /**
     * upload token
     */
    private String mToken;

    /**
     * staus
     */
    private int mStatus;

    public static final int STATUS_UPLOADING = 1;
    public static final int STATUS_RESET = 2;


    private UpLoadManager() {
        mQueue = new UploadQueue<>();
    }

    public static UpLoadManager getInstance() {
        if (manager == null) {
            manager = new UpLoadManager();
        }
        return manager;
    }

    /**
     * 开始上传
     *
     * @param infos
     * @param mToken
     */
    public void start(List<UpLoadInfo> infos, String mToken, UploadListener listener) {
        if (mStatus == STATUS_UPLOADING) {
            return;
        }
        if (mQueue.size() != 0) {
            return;
        }
        mQueue.clear();
        for (UpLoadInfo upLoadInfo : infos) {
            mQueue.put(upLoadInfo);
        }
        this.mToken = mToken;
        this.mUpLoadListener = listener;

        //开始上传
        if (mQueue.size() != 0) {
            upload();
        }
    }

    /**
     * 停止上传
     */
    public void cancle() {
        if (mQueue != null) {
            mQueue.clear();
        }
        if (mYoukuUploader != null) {
            mYoukuUploader.cancel();
        }
        mStatus = STATUS_RESET;
    }

    /**
     * upload
     */
    public void upload() {
        if (mQueue.size() == 0) {
            return;
        }
        mStatus = STATUS_UPLOADING;
        mYoukuUploader = YoukuUploader.getInstance("5570b4b24049e570",
                "c40bcc367334ef63e42ef4562b460e7f", KTApplication.getContext());
        HashMap params = new HashMap();
        //basic info
        params.put("username", "kicktempo");
        params.put("password", "kick.2011");
        params.put("access_token", mToken);
        final HashMap uploadInfo = new HashMap();


        final UpLoadInfo upLoadInfo = mQueue.get();
        uploadInfo.put("title", upLoadInfo.getUpLoadName());
        uploadInfo.put("tags", "原创");
        uploadInfo.put("file_name", upLoadInfo.getVideoPath());
        mYoukuUploader.upload(params, uploadInfo, new IUploadResponseHandler() {
            @Override
            public void onStart() {
                mUpLoadListener.onStart(upLoadInfo);
            }

            @Override
            public void onProgressUpdate(int i) {
                mUpLoadListener.onProgressUpdate(i, upLoadInfo);
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {
                mUpLoadListener.onSuccess(jsonObject, upLoadInfo);
            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                mUpLoadListener.onFailure(jsonObject, upLoadInfo);
            }

            @Override
            public void onFinished() {
                mUpLoadListener.onFinished(upLoadInfo);
                // here got a bug with youkuloader thread !=null
                if (mYoukuUploader.cancel()) {
                    upload();
                } else {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            upload();
                        }
                    }.execute();
                }
            }
        });
    }


    public int getStatus() {
        return mStatus;
    }
}
