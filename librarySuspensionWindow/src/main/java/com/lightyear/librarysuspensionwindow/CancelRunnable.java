package com.lightyear.librarysuspensionwindow;

import java.lang.ref.SoftReference;

/**
*CancelRunnable
*Toast 定时销毁任务
*author Light Year
*email 674919909@qq.com
* created 2021/7/29
*
*/
final class CancelRunnable extends SoftReference<LEasyFloatToast<?>> implements Runnable {

    CancelRunnable(LEasyFloatToast toast) {
        super(toast);
    }

    @Override
    public void run() {
        LEasyFloatToast<?> toast = get();
        if (toast == null || !toast.isShow()) {
            return;
        }
        toast.cancel();
    }
}