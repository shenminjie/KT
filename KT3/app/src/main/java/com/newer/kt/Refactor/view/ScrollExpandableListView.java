package com.newer.kt.Refactor.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by litli on 2017/3/13.
 */

public class ScrollExpandableListView extends ExpandableListView {
    public ScrollExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //根据模式计算每个child的高度和宽度
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
