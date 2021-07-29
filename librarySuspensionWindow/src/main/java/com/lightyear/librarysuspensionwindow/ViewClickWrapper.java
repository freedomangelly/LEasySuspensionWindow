package com.lightyear.librarysuspensionwindow;

import android.view.View;

/**
*ViewClickWrapper
*desc   : {@link View.OnClickListener} 包装类
*author Light Year
*email 674919909@qq.com
* created 2021/7/29
*
*/
@SuppressWarnings("rawtypes")
final class ViewClickWrapper implements View.OnClickListener {

    private final LEasyFloatToast<?> mToast;
    private final OnClickListener mListener;

    ViewClickWrapper(LEasyFloatToast<?> toast, View view, OnClickListener listener) {
        mToast = toast;
        mListener = listener;

        view.setClickable(true);
        view.setOnClickListener(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onClick(View v) {
        mListener.onClick(mToast, v);
    }
}