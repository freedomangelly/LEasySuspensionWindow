package com.lightyear.librarysuspensionwindow;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

/**
*ViewTouchWrapper
*{@link View.OnTouchListener} 包装类
*author Light Year
*email 674919909@qq.com
* created 2021/7/29
*
*/
@SuppressWarnings("rawtypes")
final class ViewTouchWrapper implements View.OnTouchListener {

    private final LEasyFloatToast<?> mToast;
    private final OnTouchListener mListener;

    ViewTouchWrapper(LEasyFloatToast<?> toast, View view, OnTouchListener listener) {
        mToast = toast;
        mListener = listener;

        view.setEnabled(true);
        view.setOnTouchListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @SuppressWarnings("unchecked")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mListener.onTouch(mToast, v, event);
    }
}