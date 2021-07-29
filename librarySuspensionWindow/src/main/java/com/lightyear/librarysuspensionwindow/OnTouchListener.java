package com.lightyear.librarysuspensionwindow;

import android.view.MotionEvent;
import android.view.View;

/**
*OnTouchListener
*View 的触摸事件封装
*author Light Year
*email 674919909@qq.com
* created 2021/7/29
*
*/
public interface OnTouchListener<V extends View> {

    /**
     * 触摸回调
     */
    boolean onTouch(LEasyFloatToast<?> toast, V view, MotionEvent event);
}