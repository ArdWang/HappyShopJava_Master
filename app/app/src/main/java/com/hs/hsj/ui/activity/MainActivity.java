package com.hs.hsj.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.hs.base.ui.activity.BaseActivity;
import com.hs.base.utils.ActivityCollector;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.base.widgets.BottomNavBar;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.event.UpdateCartSizeEvent;
import com.hs.goods.ui.fragment.CartFragment;
import com.hs.goods.ui.fragment.CategoryFragment;
import com.hs.hsj.R;
import com.hs.hsj.ui.fragment.HomeFragment;
import com.hs.hsj.ui.fragment.MeFragment;
import com.hs.message.ui.fragment.MessageFragment;
import com.hs.provider.event.MessageBadgeEvent;
import com.umeng.socialize.UMShareAPI;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.Stack;


public class MainActivity extends BaseActivity {

    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private CartFragment mCartFragment;
    private MessageFragment mMsgFragment;
    private MeFragment mMeFragment;
    //Fragment 栈管理
    private Stack<Fragment> mStack;
    private BottomNavBar mBottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        initFragment();
        initBottomNav();
        changeFragment(0);
        initObserve();
        loadCartSize();
    }

    private void initView(){
        mBottomNavBar = findViewById(R.id.mBottomNavBar);
        mStack = new Stack<>();
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mCategoryFragment = new CategoryFragment();
        mCartFragment = new CartFragment();
        mMsgFragment = new MessageFragment();
        mMeFragment = new MeFragment();

        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        manager.add(R.id.mContaier,mHomeFragment);
        manager.add(R.id.mContaier,mCategoryFragment);
        manager.add(R.id.mContaier,mCartFragment);
        manager.add(R.id.mContaier,mMsgFragment);
        manager.add(R.id.mContaier,mMeFragment);
        manager.commit();

        mStack.add(mHomeFragment);
        mStack.add(mCategoryFragment);
        mStack.add(mCartFragment);
        mStack.add(mMsgFragment);
        mStack.add(mMeFragment);
    }

    private void initBottomNav(){
        mBottomNavBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                changeFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        mBottomNavBar.checkMsgBadge(false);
    }

    /**
       切换Tab，切换对应的Fragment
    */
    private void changeFragment(int position) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment:mStack){
            manager.hide(fragment);
        }
        manager.show(mStack.get(position));
        manager.commit();
    }

    private void initObserve(){
        EventBus.getDefault().post(AppPrefsUtils.getInstance().getInt(GoodsConstant.SP_CART_SIZE));
    }


    /**
     * EventBus 事件处理
     * @param
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageBadgeEvent(MessageBadgeEvent o){
        mBottomNavBar.checkMsgBadge(o.isVisible());
    }


    /**
     * EventBus 事件处理
     * @param
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateCartSizeEvent(UpdateCartSizeEvent event){
        loadCartSize();
    }


    /**
       加载购物车数量
    */
    private void loadCartSize(){
        //int a = AppPrefsUtils.getInstance().getInt(GoodsConstant.SP_CART_SIZE);
        //CommonExt.toast(a+"");
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInstance().getInt(GoodsConstant.SP_CART_SIZE));
    }


    /**
     * 键盘事件处理
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /*
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    /**
     * 退出程序显示提示
     */
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }
            else {
                ActivityCollector.finishAll();
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //友盟分享需要的集成
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
