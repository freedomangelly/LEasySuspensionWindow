package com.lightyear.librarysuspensionwindow;

/**
*OnToastListener
*Toast 显示销毁监听
*author Light Year
*email 674919909@qq.com
* created 2021/7/29
*
*/
public interface OnToastListener {

    /**
     * 显示回调
     */
    void onShow(LEasyFloatToast<?> toast);

    /**
     * 消失回调
     */
    void onDismiss(LEasyFloatToast<?> toast);
}