package cn.njcit.ankeread;

import android.app.Application;


import org.litepal.LitePal;

/**
 * Create by ankele
 * <p>
 * 2020/1/27 - 18:42
 */
public class InitApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
