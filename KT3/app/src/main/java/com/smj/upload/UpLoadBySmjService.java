package com.smj.upload;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ui.upload.UploadListener;
import com.smj.LocalDataInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.List;

public class UpLoadBySmjService extends Service {

    private UpLoadBinder mBinder = new UpLoadBinder();

    private String mToken;

    public UpLoadBySmjService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        long user_id = PreferenceManager.getDefaultSharedPreferences(this).getLong(LoginActivity.PRE_CURRENT_USER_ID, 0);
        QueryBuilder.build("users/get_role").add("user_id", user_id).get(new QueryBuilder.Callback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.has("youku_token")) {
                        mToken = jsonObject.getString("youku_token");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class UpLoadBinder extends Binder {

        /**
         * 开始上传
         *
         * @param localDataInfos localDataInfos
         */
        public void startUpLoad(List<LocalDataInfo> localDataInfos) {
            if (UpLoadManager.getInstance().getStatus() == UpLoadManager.STATUS_RESET) {
                if (!TextUtils.isEmpty(mToken)) {
                    UpLoadManager.getInstance().start(localDataInfos, mToken);
                }
            }
        }

        public void setListener(UploadListener listener) {
            UpLoadManager.getInstance().setListener(listener);
        }

        /**
         * 获取进度
         *
         * @return return
         */
        public int getUpLoadStatus() {
            return UpLoadManager.getInstance().getStatus();
        }
    }
}
