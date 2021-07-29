# 悬浮窗框架


#### 集成步骤

```groovy
dependencies {
    // 悬浮窗框架：https://github.com/getActivity/XToast
    implementation 'com.github.freedomangelly:LEasySuspensionWindow:0.0.0.1'
}
```

#### 使用案例

* Java 用法

```java
// 传入 Activity 对象表示设置成局部的，不需要有悬浮窗权限
// 传入 Application 对象表示设置成全局的，但需要有悬浮窗权限
new XToast<>(XToastActivity.this)
        .setView(R.layout.toast_hint)
        // 设置成可拖拽的
        //.setDraggable()
        // 设置显示时长
        .setDuration(1000)
        // 设置动画样式
        //.setAnimStyle(android.R.style.Animation_Translucent)
        // 设置外层是否能被触摸
        //.setOutsideTouchable(false)
        // 设置窗口背景阴影强度
        //.setBackgroundDimAmount(0.5f)
        .setImageDrawable(android.R.id.icon, R.mipmap.ic_dialog_tip_finish)
        .setText(android.R.id.message, "点我消失")
        .setOnClickListener(android.R.id.message, new OnClickListener<TextView>() {

            @Override
            public void onClick(XToast toast, TextView view) {
                // 点击这个 View 后消失
                toast.cancel();
                // 跳转到某个Activity
                // toast.startActivity(intent);
            }
        })
        .show();
```


#### 没有悬浮窗权限如何全局显示？

* 没有悬浮窗权限是不能全局显示在其他应用上的，但是全局显示在自己的应用上是可以实现的

* 但是当前 Activity 创建的悬浮窗只能在当前 Activity 上面显示，如果想在所有的 Activity 都显示需要做特殊处理

* 我们可以通过 Application 来监听所有 Activity 的生命周期方法，然后在每个 Activity.onCreate 时创建悬浮窗

```java
public final class FloatingLifecycle implements Application.ActivityLifecycleCallbacks {

    static void with(Application application) {
        application.registerActivityLifecycleCallbacks(new FloatingLifecycle());
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        new XToast<>(activity)
                .setXXXX()
                .show();
    }

    @Override
    public void onActivityStarted(Activity activity) {}

    @Override
    public void onActivityResumed(Activity activity) {}

    @Override
    public void onActivityPaused(Activity activity) {}

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {}
}
```

#### 框架亮点（原生 Toast 无法实现的功能）

* 支持自定义 Toast 动画样式

* 支持自定义 Toast 显示时长

* 支持监听 Toast 的显示和销毁

* 支持监听 Toast 中点击事件

* 支持一键开启 Toast 拖拽功能

* 支持 Toast 全局显示


