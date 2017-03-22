package com.smj;

import com.orhanobut.hawk.Hawk;
import com.smj.saishi.SaishiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenminjie on 17/3/19.
 */

public class LocalDataManager {
    public static final String LOCAL_DATA_CACHE_ARRAY = "LOCAL_DATA_CACHE_ARRAY";
    public static final String LOCAL_DATA_FINISH_UPLOAD = "LOCAL_DATA_FINISH_UPLOAD";

    private static List<LocalDataInfo> mUploadList;

    /**
     * 获取缓存数据 已经提交
     *
     * @return
     */
    public static List<LocalDataInfo> getCacheDatas() {
        List<LocalDataInfo> datas = Hawk.get(LOCAL_DATA_FINISH_UPLOAD);
        if (datas == null) {
            return new ArrayList<>();
        }
        return datas;
    }

    /**
     * 保存缓存数据 已经提交
     */
    public static void saveCacheDatas(List<LocalDataInfo> dataInfos) {
        Hawk.put(LOCAL_DATA_FINISH_UPLOAD, dataInfos);
    }

    public static List<LocalDataInfo> getUnUploadCacheDatas() {
        if (mUploadList == null) {
            mUploadList = Hawk.get(LOCAL_DATA_CACHE_ARRAY);
            if (mUploadList == null) {
                mUploadList = new ArrayList<>();
                saveUnUpLoadList(mUploadList);
            }
        }
        return mUploadList;
    }

    /**
     * 保存缓存数据 未上传
     *
     * @param datas
     */
    public static void saveUnUpLoadList(List<LocalDataInfo> datas) {
        Hawk.put(LOCAL_DATA_CACHE_ARRAY, datas);
    }

    public static void saveUnUpLoadInfo(LocalDataInfo data) {
        List<LocalDataInfo> datas = getUnUploadCacheDatas();
        datas.add(data);
        saveUnUpLoadList(datas);
    }

    /**
     * 删除数据
     *
     * @param info
     */
    public static void removeUnUpLoadData(LocalDataInfo info) {
        mUploadList.remove(info);
        saveUnUpLoadList(mUploadList);
    }

    /**
     * 保存赛事数据到本地
     *
     * @param saishiRequest saishiRequest
     * @param videoPath     本地保存地址
     * @param videoName     视频名字
     */
    public static void saveSaishiInfo(SaishiRequest saishiRequest, String videoPath, String videoName) {
        LocalDataInfo localDataInfo = new LocalDataInfo();
        localDataInfo.setType(LocalDataInfo.TYPE_SAISHI);
        localDataInfo.setSaishiRequest(saishiRequest);
        localDataInfo.setSaishiVideoName(videoName);
        localDataInfo.setCreatetime(System.currentTimeMillis());
        localDataInfo.setVideoPath(videoPath);

        LocalDataManager.saveUnUpLoadInfo(localDataInfo);
    }
}
