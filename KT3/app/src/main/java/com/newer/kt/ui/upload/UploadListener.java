package com.newer.kt.ui.upload;

import com.smj.LocalDataInfo;
import com.smj.upload.UpLoadInfo;

import org.json.JSONObject;

/**
 * Created by chenminjie on 17/3/19.
 */

public interface UploadListener {

    void onProgressUpdate(int i, LocalDataInfo info);

    void onStart(LocalDataInfo info);

    void onSuccess(JSONObject var1, LocalDataInfo info);

    void onFailure(JSONObject var1,LocalDataInfo info);

    void onFinished(LocalDataInfo info);
}
