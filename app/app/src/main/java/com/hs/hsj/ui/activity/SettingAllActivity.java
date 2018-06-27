package com.hs.hsj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.hs.hsj.R;
import com.hs.hsj.ui.fragment.FeedBackFragment;
import com.hs.hsj.ui.fragment.SpaceFragment;
import com.hs.provider.common.ProviderConstant;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;


public class SettingAllActivity extends RxFragmentActivity{

    private int value;

    private TextView mTitleTv;

    public String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingall);
        initView();
        initData();
    }

    private void initView(){
        mTitleTv = findViewById(R.id.mTitleTv);
    }

    private void initData(){
        try{
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            value = bundle.getInt("value");
            userName = bundle.getString(ProviderConstant.KEY_SP_USER_NAME);
            mTitleTv.setText(bundle.getString("titleName"));
            if(value!=0x05) {
                showCurrentFragment(value, intent);
            }else{
                finish();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showCurrentFragment(int index, Intent intent){
        Fragment fragment = null;
        switch (index){
            case 0x01:
                break;
            //清理手机存储空间
            case 0x02:
                fragment = new FeedBackFragment();
                intent = getIntent();
                mTitleTv.setText(intent.getStringExtra("titleName"));
                break;
            case 0x03:
                fragment = new SpaceFragment();
                intent = getIntent();
                mTitleTv.setText(intent.getStringExtra("titleName"));
                break;
            case 0x04:

                break;

            case 0x05:

                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.currentSet, fragment).commit();
    }
}
