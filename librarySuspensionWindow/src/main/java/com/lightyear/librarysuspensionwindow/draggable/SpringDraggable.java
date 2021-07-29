package com.lightyear.librarysuspensionwindow.draggable;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
*SpringDraggable
*拖拽后回弹处理实现类
*author Light Year
*email 674919909@qq.com
* created 2021/7/29
*
*/
public class SpringDraggable extends BaseDraggable {

    /** 手指按下的坐标 */
    private float mViewDownX;
    private float mViewDownY;

    /** 回弹的方向 */
    private final int mOrientation;

    public SpringDraggable() {
        this(LinearLayout.HORIZONTAL);
    }

    public SpringDraggable(int orientation) {
        mOrientation = orientation;
        switch (mOrientation) {
            case LinearLayout.HORIZONTAL:
            case LinearLayout.VERTICAL:
                break;
            default:
                throw new IllegalArgumentException("You cannot pass in directions other than horizontal or vertical");
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float rawMoveX;
        float rawMoveY;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录按下的位置（相对 View 的坐标）
                mViewDownX = event.getX();
                mViewDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 记录移动的位置（相对屏幕的坐标）
                rawMoveX = event.getRawX();
                rawMoveY = event.getRawY() - getStatusBarHeight();
                // 更新移动的位置
                updateLocation(rawMoveX - mViewDownX, rawMoveY - mViewDownY);
                break;
            case MotionEvent.ACTION_UP:
                // 记录移动的位置（相对屏幕的坐标）
                rawMoveX = event.getRawX();
                rawMoveY = event.getRawY() - getStatusBarHeight();

                // 自动回弹吸附
                switch (mOrientation) {
                    case LinearLayout.HORIZONTAL:
                        final float rawFinalX;
                        // 获取当前屏幕的宽度
                        int screenWidth = getScreenWidth();
                        if (rawMoveX < screenWidth / 2f) {
                            // 回弹到屏幕左边
                            rawFinalX = 0f;
                        } else {
                            // 回弹到屏幕右边
                            rawFinalX = screenWidth;
                        }
                        // 从移动的点回弹到边界上
                        startHorizontalAnimation(rawMoveX - mViewDownX, rawFinalX  - mViewDownX, rawMoveY - mViewDownY);
                        break;
                    case LinearLayout.VERTICAL:
                        final float rawFinalY;
                        // 获取当前屏幕的高度
                        int screenHeight = getScreenHeight();
                        if (rawMoveY < screenHeight / 2f) {
                            // 回弹到屏幕顶部
                            rawFinalY = 0f;
                        } else {
                            // 回弹到屏幕底部
                            rawFinalY = screenHeight;
                        }
                        // 从移动的点回弹到边界上
                        startVerticalAnimation(rawMoveX - mViewDownX, rawMoveY - mViewDownY, rawFinalY);
                        break;
                    default:
                        break;
                }
                // 如果用户移动了手指，那么就拦截本次触摸事件，从而不让点击事件生效
                return isTouchMove(mViewDownX, event.getX(), mViewDownY, event.getY());
            default:
                break;
        }
        return false;
    }

    /**
     *  获取屏幕的宽度
     */
    private int getScreenWidth() {
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     *  获取屏幕的高度
     */
    private int getScreenHeight() {
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 执行水平回弹动画
     *
     * @param startX        X 轴起点坐标
     * @param endX          X 轴终点坐标
     * @param y             Y 轴坐标
     */
    private void startHorizontalAnimation(float startX, float endX, final float y) {
        ValueAnimator animator = ValueAnimator.ofFloat(startX, endX);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateLocation((float) animation.getAnimatedValue(), y);
            }
        });
        animator.start();
    }

    /**
     * 执行垂直回弹动画
     *
     * @param x             X 轴坐标
     * @param startY        Y 轴起点坐标
     * @param endY          Y 轴终点坐标
     */
    private void startVerticalAnimation(final float x, float startY, final float endY) {
        ValueAnimator animator = ValueAnimator.ofFloat(startY, endY);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateLocation(x, (float) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
}