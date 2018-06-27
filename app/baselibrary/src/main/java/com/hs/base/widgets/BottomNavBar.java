package com.hs.base.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.hs.base.R;

/**
 * Created by rnd on 2018/4/12.
 *
 */

public class BottomNavBar extends BottomNavigationBar{
    //购物车Tab标签
    private TextBadgeItem mCartBadge;
    //消息Tab标签
    private ShapeBadgeItem mMsgBadge;

    public BottomNavBar(Context context) {
        super(context);
    }

    public BottomNavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    public BottomNavBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    private void initView(){
        //首页
        BottomNavigationItem homeItem = new BottomNavigationItem(R.drawable.btn_nav_home_press,
                getResources().getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        //分类
        BottomNavigationItem categoryItem = new BottomNavigationItem(R.drawable.btn_nav_category_press,
                getResources().getString(R.string.nav_bar_category))
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        //购物车
        BottomNavigationItem cartItem = new BottomNavigationItem(R.drawable.btn_nav_cart_press,
                getResources().getString(R.string.nav_bar_cart))
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        mCartBadge =new TextBadgeItem();
        cartItem.setBadgeItem(mCartBadge);

        //消息
        BottomNavigationItem msgItem =new BottomNavigationItem(R.drawable.btn_nav_msg_press,
                getResources().getString(R.string.nav_bar_msg))
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        mMsgBadge =new  ShapeBadgeItem();
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL);
        msgItem.setBadgeItem(mMsgBadge);

        //我的
        BottomNavigationItem userItem =new BottomNavigationItem(R.drawable.btn_nav_user_press,
                getResources().getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal);

        //设置底部导航模式及样式
        setMode(BottomNavigationBar.MODE_FIXED);
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        setBarBackgroundColor(R.color.common_white);

        //添加Tab
        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(msgItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise();

    }

    /**
        检查购物车Tab是否显示标签
     */
    public void checkCartBadge(int count){
        if (count == 0){
            mCartBadge.hide();
        }else{
            mCartBadge.show();
            mCartBadge.setText(count+"");
        }
    }

    /**
        检查消息Tab是否显示标签
     */
    public void checkMsgBadge(Boolean isVisiable){
        if (isVisiable){
            mMsgBadge.show();
        }else {
            mMsgBadge.hide();
        }
    }


}
