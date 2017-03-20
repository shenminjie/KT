package com.smj;

import com.orhanobut.hawk.Hawk;
import com.smj.upload.UpLoadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenminjie on 17/3/19.
 */

public class LocalDataManager {
    public static final String LOCAL_DATA_CACHE_ARRAY = "LOCAL_DATA_CACHE_ARRAY";

    private static List<UpLoadInfo> mUploadList;

    public static List<UpLoadInfo> getCacheDatas() {
        if (mUploadList == null) {
            mUploadList = Hawk.get(LOCAL_DATA_CACHE_ARRAY);
            if (mUploadList == null) {
                mUploadList = new ArrayList<>();
                saveUpLoadList(mUploadList);
            }
        }
        return mUploadList;
    }


    public static void saveUpLoadList(List<UpLoadInfo> datas) {
        Hawk.put(LOCAL_DATA_CACHE_ARRAY, datas);
    }

    public static void saveUpLoadInfo(UpLoadInfo data) {
        List<UpLoadInfo> datas = getCacheDatas();
        datas.add(data);
        saveUpLoadList(datas);
    }

}
