package com.newer.kt.entity;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/16.
 * Mail:shenminjie92@sina.com
 */

public interface OnItemListener<T> {

    /**
     * calbback
     * @param t
     * @param position
     */
    void onItemListener(T t,int position);
}
