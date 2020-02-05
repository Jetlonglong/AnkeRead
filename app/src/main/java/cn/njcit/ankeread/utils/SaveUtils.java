package cn.njcit.ankeread.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Create by ankele
 * <p>
 * 2020/1/19 - 20:54
 */
public class SaveUtils {
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    public static void saveSex(Context context,String sex){
        editor = context.getSharedPreferences("SEX",Context.MODE_PRIVATE).edit();
        editor.putString("sex",sex);
        editor.apply();
    }

    public static String getSex(Context context){
        sharedPreferences = context.getSharedPreferences("SEX",Context.MODE_PRIVATE);
        return sharedPreferences.getString("sex","男");
    }
}
