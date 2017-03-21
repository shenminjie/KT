package com.smj.upload;

import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.frame.app.utils.LogUtils;
import com.newer.kt.Refactor.KTApplication;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ui.upload.UploadListener;
import com.smj.LocalDataInfo;
import com.smj.LocalDataManager;
import com.smj.gradlebean.Classes;
import com.smj.gradlebean.Users;
import com.youku.uploader.IUploadResponseHandler;
import com.youku.uploader.YoukuUploader;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private UploadQueue<LocalDataInfo> mQueue;

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
    private int mStatus = STATUS_RESET;

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

    public void setListener(UploadListener listener) {
        this.mUpLoadListener = listener;
    }

    /**
     * 开始上传
     *
     * @param infos
     * @param mToken
     */
    public void start(List<LocalDataInfo> infos, String mToken) {
        if (mStatus == STATUS_UPLOADING) {
            return;
        }
        if (mQueue.size() != 0) {
            return;
        }
        mQueue.clear();
        for (LocalDataInfo upLoadInfo : infos) {
            mQueue.put(upLoadInfo);
        }
        this.mToken = mToken;

        //开始上传
        if (mQueue.size() != 0) {
            upload();
        }
    }


    /**
     * 停止上传
     */
    public void cancle() {
        mStatus = STATUS_RESET;
        if (mQueue != null) {
            mQueue.clear();
        }
        if (mYoukuUploader != null) {
            mYoukuUploader.cancel();
        }
    }

    /**
     * 获取正在上传的数据
     *
     * @return
     */
    public List<LocalDataInfo> getUpLoadingData() {
        List<LocalDataInfo> datas = new ArrayList<>();
        for (int i = 0; i < mQueue.size(); i++) {
            datas.add(mQueue.get(i));
        }
        return datas;
    }

    /**
     * upload
     */
    public void upload() {
        if (mQueue.size() == 0) {
            mStatus = STATUS_RESET;
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


        final LocalDataInfo dataInfo = mQueue.get(0);
        uploadInfo.put("title", dataInfo.getUpLoadName());
        uploadInfo.put("tags", "原创");
        uploadInfo.put("file_name", dataInfo.getVideoPath());
        mYoukuUploader.upload(params, uploadInfo, new IUploadResponseHandler() {
            @Override
            public void onStart() {
                if (mUpLoadListener != null)
                    mUpLoadListener.onStart(dataInfo);
            }

            @Override
            public void onProgressUpdate(int i) {
                if (mUpLoadListener != null)
                    mUpLoadListener.onProgressUpdate(i, dataInfo);
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {
                Log.e("tag", "tag-----优酷上传成功");
                if (mUpLoadListener != null) {
                    mUpLoadListener.onSuccess(jsonObject, dataInfo);
                }
                //去掉队列
                keepUpLoad();
                LocalDataInfo upLoadSuccessData = mQueue.get();
                if (upLoadSuccessData.getType() == LocalDataInfo.TYPE_PINGCE) {
                    commitPingce(jsonObject, upLoadSuccessData);
                } else {
                    commitDakejian(jsonObject, upLoadSuccessData);
                }
            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                if (mUpLoadListener != null) {
                    mUpLoadListener.onFailure(jsonObject, dataInfo);
                }
                keepUpLoad();
            }

            @Override
            public void onFinished() {
                if (mUpLoadListener != null) {
                    mUpLoadListener.onFinished(dataInfo);
                }
            }
        });
    }

    /**
     * cimmit dakejian
     */
    private void commitDakejian(JSONObject var1, final LocalDataInfo info) {
        try {
            String youku_video_url = var1.getString("video_id");
            String clubid = "" + PreferenceManager.getDefaultSharedPreferences(KTApplication.getContext())
                    .getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);
            long user_id = PreferenceManager.getDefaultSharedPreferences(KTApplication.getContext()).getLong(LoginActivity.PRE_CURRENT_USER_ID, 0);
            String classroom_id = info.getDakejianBasicInfo().getId();
            StringBuilder clzBuilder = new StringBuilder();
            for (Classes classes : info.getDakejianClasses()) {
                clzBuilder.append(classes.getId());
                clzBuilder.append(",");
            }
            int is_finished = 0;
            String creatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(info.getCreatetime()));
            QueryBuilder.build("offline/upload_big_classroom_course_record")
                    .add("club_id", clubid)
                    .add("user_id", user_id)
                    .add("youku_video_url", youku_video_url)
                    .add("classroom_id", classroom_id)
                    .add("classes", clzBuilder.toString())
                    .add("is_finished", is_finished)
                    .add("finished_time", creatime)
                    .post(new QueryBuilder.Callback() {
                        @Override
                        public void onSuccess(String result) {
                            LocalDataManager.removeUnUpLoadData(info);
                            List<LocalDataInfo> save = LocalDataManager.getCacheDatas();
                            save.add(info);
                            LocalDataManager.saveCacheDatas(save);
                            if (mUpLoadListener != null) {
                                mUpLoadListener.commitSuccess(info);
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Log.e("tag", ex + "");
                        }

                        @Override
                        public void onDebug(RequestParams rp) {
                            Log.e("tag", rp + "");
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * cimmit  pingce
     */
    private void commitPingce(JSONObject var1, final LocalDataInfo info) {
        try {
            String videoId = var1.getString("video_id");
            List<Users> students = info.getPingceStudent();
            StringBuilder builder = new StringBuilder();
            for (Users users : students) {
                builder.append(users.getUser_id() + ",");
            }
            QueryBuilder.build("shool_user_tests/save_user_skill_test_record_video_url")
                    .add("user_skill_test_record_id", builder.toString())
                    .add("video_url", videoId)
                    .post(new QueryBuilder.Callback() {
                        @Override
                        public void onSuccess(String result) {
                            LocalDataManager.removeUnUpLoadData(info);
                            List<LocalDataInfo> save = LocalDataManager.getCacheDatas();
                            save.add(info);
                            LocalDataManager.saveCacheDatas(save);
                            if (mUpLoadListener != null) {
                                mUpLoadListener.commitSuccess(info);
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onDebug(RequestParams rp) {

                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * keep upload
     */
    private void keepUpLoad() {
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


    public int getStatus() {
        return mStatus;
    }
}
