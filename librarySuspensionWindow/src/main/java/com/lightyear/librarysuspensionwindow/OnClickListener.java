package com.lightyear.librarysuspensionwindow;

import android.view.View;

/**
*OnClickListener
*View 的点击事件封装
*author Light Year
*email 674919909@qq.com
* created 2021/7/29
*
*/
public interface OnClickListener<V extends View> {

    /**
     * 点击回调
     */
    void onClick(LEasyFloatToast<?> toast, V view);
}