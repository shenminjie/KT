package com.newer.kt.ui.upload;

import com.smj.upload.UpLoadInfo;

import org.json.JSONObject;

/**
 * Created by chenminjie on 17/3/19.
 */

public interface UploadListener {

    void onProgressUpdate(int i, UpLoadInfo info);

    void onStart(UpLoadInfo info);

    void onSuccess(JSONObject var1, UpLoadInfo info);

    void onFailure(JSONObject var1,UpLoadInfo info);

    void onFinished(UpLoadInfo info);
}
