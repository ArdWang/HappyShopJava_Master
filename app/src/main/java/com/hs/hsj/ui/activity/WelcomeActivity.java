package com.hs.hsj.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.hs.base.ui.activity.BaseActivity;
import com.hs.hsj.R;
import com.hs.hsj.utils.APKVersionCodeUtils;

import java.io.File;

public class WelcomeActivity extends BaseActivity{
    private static final int times = 3000;
    private TextView mCurrentName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mCurrentName = findViewById(R.id.mCurrentName);

        String current = "Current Version "+ APKVersionCodeUtils.getVerName(this)+", "+APKVersionCodeUtils.getVersionCode(this);
        mCurrentName.setText(current);
    }

    @Override
    protected void onResume() {
        try {
            if (isok()) {
                String b = "/storage/emulated/0/HappyGo";
                File file = new File(b);
                if (!file.exists()) {
                    file.mkdirs();
                    Log.i("Create_file", "文件夹不存在创建文件夹");
                } else {
                    Log.i("Create_file", "文件夹存在不需要创建");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onResume();
    }

    private boolean isok() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

                finish();
            }
        }, times);
    }
}
