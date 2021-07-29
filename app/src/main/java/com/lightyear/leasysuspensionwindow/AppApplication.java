package com.lightyear.leasysuspensionwindow;

import android.app.Application;

import com.lightyear.leasytoast.ToastUtils;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/XToast
 *    time   : 2021/01/24
 *    desc   : 应用入口
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
    }
}