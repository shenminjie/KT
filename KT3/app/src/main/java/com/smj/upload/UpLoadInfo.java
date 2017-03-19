package com.smj.upload;

/**
 * Created by chenminjie on 17/3/19.
 */

public interface UpLoadInfo {
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
}
