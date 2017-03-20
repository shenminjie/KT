package com.smj.upload;

/**
 * Created by chenminjie on 17/3/19.
 */

public interface UpLoadInfo {

    int TYPE_PINGCE = 1;
    int TYPE_DAKEJIAN = 2;

    /**
     * 上传的名字
     *
     * @return
     */
    String getUpLoadName();

    /**
     * id
     *
     * @return
     */
    String getId();

    /**
     * path
     *
     * @return
     */
    String getVideoPath();

    /**
     * 获取类型
     *
     * @return
     */
    int getType();

}
