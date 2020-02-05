package cn.njcit.ankeread.utils;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Create by ankele
 * <p>
 * 2020/1/19 - 17:24
 */
public class HttpUtils {
    private static OkHttpClient client = new OkHttpClient();

    public static void httpGet(String address, Callback callback){
        Request request = new Request
                .Builder()
                .url(address)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void httpPostJson(String address, Map<String,Object> map,Callback callback){
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(mediaType,gson.toJson(map));
        Request request = new Request
                .Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postFromBody(String address,FormBody.Builder builder,Callback callback){
        FormBody formBody = builder.build();
        Request request = new Request
                .Builder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(address)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
