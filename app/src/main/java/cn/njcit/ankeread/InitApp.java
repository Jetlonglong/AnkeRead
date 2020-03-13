package cn.njcit.ankeread;

import android.app.Application;


import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.io.IOException;

import cn.njcit.ankeread.utils.HttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
