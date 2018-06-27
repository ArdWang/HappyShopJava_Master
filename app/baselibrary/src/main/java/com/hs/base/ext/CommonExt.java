package com.hs.base.ext;


import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.hs.base.R;
import com.hs.base.common.BaseApplication;
import com.hs.base.common.BaseConstant;
import com.hs.base.utils.GlideUtils;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnd on 2018/4/9.
 * 扩展一些方法
 */

public class CommonExt {

    public static List<String> imgUrlList;
    public static List<String> hotsList;
    public static List<String> titles;
    //折扣
    public static List<String> discList;
    public static List<String> topcList;

    public static void getHomeList(){
        imgUrlList = new ArrayList<>();
        hotsList = new ArrayList<>();
        titles = new ArrayList<>();
        discList = new ArrayList<>();
        topcList = new ArrayList<>();

        imgUrlList.add(BaseConstant.HOME_BANNER_ONE);
        imgUrlList.add(BaseConstant.HOME_BANNER_TWO);
        imgUrlList.add(BaseConstant.HOME_BANNER_THREE);
        imgUrlList.add(BaseConstant.HOME_BANNER_FOUR);

        discList.add(BaseConstant.HOME_DISCOUNT_ONE);
        discList.add(BaseConstant.HOME_DISCOUNT_TWO);
        discList.add(BaseConstant.HOME_DISCOUNT_THREE);
        discList.add(BaseConstant.HOME_DISCOUNT_FOUR);
        discList.add(BaseConstant.HOME_DISCOUNT_FIVE);

        topcList.add(BaseConstant.HOME_TOPIC_ONE);
        topcList.add(BaseConstant.HOME_TOPIC_TWO);
        topcList.add(BaseConstant.HOME_TOPIC_THREE);
        topcList.add(BaseConstant.HOME_TOPIC_FOUR);
        topcList.add(BaseConstant.HOME_TOPIC_FIVE);

        titles.add(BaseConstant.bannerTitle1);
        titles.add(BaseConstant.bannerTitle2);
        titles.add(BaseConstant.bannerTitle3);
        titles.add(BaseConstant.bannerTitle4);

        hotsList.add("夏日炎炎，第一波福利还有30秒到达战场");
        hotsList.add("新用户立领1000元优惠券");
    }

    /**
     * 扩展方法 判断按钮是否可以点击
     * @param et
     * @param btn
     * @param a
     */
    public static void buttonEnable(EditText et, final Button btn,final boolean a){

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(a) {
                    btn.setEnabled(true);
                }else{
                    btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 弹出消息框
     * @param msg
     */
    public static void toast(String msg){
        Toast.makeText(BaseApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    /**
     ImageView加载网络图片
    */
    public static void loadUrl(String url, ImageView imageView) {
        GlideUtils.loadUrlImage(BaseApplication.getContext(), url, imageView);
    }

    /**
     扩展视图可见性
     */
    public static void setVisible (View view,Boolean visible){
        if(visible){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }

    }

    public static void startMultiLoading(MultiStateView viewState){
        viewState.setViewState(MultiStateView.VIEW_STATE_LOADING);
        Drawable animBackground = viewState.findViewById(R.id.loading_anim_view).getBackground();
        ((AnimationDrawable) animBackground).start();

    }




    /**
     三方控件扩展
     */
    /*public static EditText getEditText(EditText editText){
        //return editText.findViewById(R.id.text_count);
    }*/







}
