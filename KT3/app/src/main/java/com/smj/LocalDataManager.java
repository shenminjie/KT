package com.smj;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenminjie on 17/3/19.
 */

public class LocalDataManager {
    public static final String PINGCE_LOCAL_DATA_CACHE_ARRAY = "PINGCE_LOCAL_DATA_CACHE_ARRAY";

    private static List<PingceLocalData> mPingceLocalData;

    public static List<PingceLocalData> getPingCeLocalCacheData() {
        if (mPingceLocalData == null) {
            mPingceLocalData = Hawk.get(PINGCE_LOCAL_DATA_CACHE_ARRAY);
            if (mPingceLocalData == null) {
                mPingceLocalData = new ArrayList<>();
                savePingceList(mPingceLocalData);
            }
        }
        return mPingceLocalData;
    }


    public static void savePingceList(List<PingceLocalData> datas) {
        Hawk.put(PINGCE_LOCAL_DATA_CACHE_ARRAY, datas);
    }

    public static void savePingceItem(PingceLocalData data) {
        List<PingceLocalData> datas = getPingCeLocalCacheData();
        datas.add(data);
        savePingceList(datas);
    }

}
