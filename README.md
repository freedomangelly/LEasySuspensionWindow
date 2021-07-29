# 悬浮窗框架

# 我的理念概述
我的理念的是能用一行代码解决的是，绝对不用两行
其实编写依赖库的目的就是将一个功能尽可能的完善
代码调用方便，简洁

# 这个框架也是在遇到使用Toast受阻，我们的一个小朋友的猪秘编写，我在他的基础上再次的封装，生成了这套框架，使用这个框架最主要的原因就是，Toast显示的时间可以自己控制，而不是固定的那两个时间

[悬浮窗github](https://github.com/freedomangelly/LEasySuspensionWindow)

# 框架优点
* 支持自定义 Toast 动画样式
* 支持自定义 Toast 显示时长
* 支持监听 Toast 的显示和销毁
* 支持监听 Toast 中点击事件
* 支持一键开启 Toast 拖拽功能（悬浮窗功能）
* 支持 Toast 全局显示

#框架环境的集成

```
buildscript {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

 ```java
dependencies {
    // 悬浮窗框架：https://github.com/getActivity/XToast
    implementation 'com.github.freedomangelly:LEasySuspensionWindow:0.0.0.1'
}
```
#api的使用
调用方式使用建造者模式
```
// 传入 Activity 对象表示设置成局部的，不需要有悬浮窗权限
// 传入 Application 对象表示设置成全局的，但需要有悬浮窗权限
new LEasyFloatToast<>(XToastActivity.this)
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
# 不添加权限变通的方法
这套框架是基于悬浮窗的原理进行绘制，我们都知道这个得需要悬浮窗权限
这里有一个变通的方法，就是监听所有的Activity生命周期，在Activity创建的时候进行创建（不过不推荐）
```
public final class FLifecycle implements Application.ActivityLifecycleCallbacks {

    static void with(Application application) {
        application.registerActivityLifecycleCallbacks(new FLifecycle());
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        new LEasyFloatToast<>(activity)
                .setText("hello")
                .show();
    }
...
```
