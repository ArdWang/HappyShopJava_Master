package com.hs.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.hs.base.common.BaseApplication;
import com.hs.base.common.BaseConstant;
import android.content.SharedPreferences.Editor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rnd on 2018/4/11.
 *
 */

public class AppPrefsUtils {
    private static SharedPreferences sp = BaseApplication.getContext()
            .getSharedPreferences(BaseConstant.TABLE_PREFS, Context.MODE_PRIVATE);
    private static Editor ed;

    //单列模式
    private static class SingletonHolder{
        private static final AppPrefsUtils INSTANCE = new AppPrefsUtils();
    }

    /**
     * 获取AppPrefsUtils
     * @return
     */
    public static AppPrefsUtils getInstance(){
        return AppPrefsUtils.SingletonHolder.INSTANCE;
    }



    @SuppressLint("CommitPrefEdits")
    private AppPrefsUtils(){
        ed = sp.edit();
    }

    /**
        String数据
     */
    public void putString(String key, String value) {
        ed.putString(key, value);
        ed.apply();
    }

    /**
        默认 ""
     */
    public String getString(String key) {
        return sp.getString(key, "");
    }

    /**
        Int数据
     */
    public void putInt(String key, int value) {
        ed.putInt(key, value);
        ed.apply();
    }

    /**
        默认 0
     */
    public int getInt(String key){
        return sp.getInt(key, 0);
    }

    /**
       Set数据
    */
    public void putStringSet(String key, Set<String> set) {
        Set<String> localSet = getStringSet(key);
        localSet.addAll(set);
        ed.putStringSet(key, localSet);
        ed.commit();
    }

    /**
        默认空set
     */
    public Set<String> getStringSet(String key) {
        Set<String> set = new HashSet<>();
        return sp.getStringSet(key, set);
    }

    /**
        删除key数据
     */
    public void remove(String key) {
        ed.remove(key);
        ed.commit();
    }

}
