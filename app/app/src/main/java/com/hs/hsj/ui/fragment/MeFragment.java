package com.hs.hsj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.base.ext.CommonExt;
import com.hs.base.ui.fragment.BaseFragment;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.hsj.R;
import com.hs.hsj.common.ShareContent;
import com.hs.hsj.ui.activity.SettingActivity;
import com.hs.hsj.utils.ShareUtils;
import com.hs.order.common.OrderConstant;
import com.hs.order.common.OrderStatus;
import com.hs.order.ui.activity.OrderActivity;
import com.hs.order.ui.activity.ShipAddressActivity;
import com.hs.provider.common.CommonUtils;
import com.hs.provider.common.ProviderConstant;
import com.hs.user.ui.activity.LoginActivity;
import com.hs.user.ui.activity.UserInfoActivity;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rnd on 2018/4/12.
 *
 */

public class MeFragment extends BaseFragment implements View.OnClickListener{
    private CircleImageView mUserIconIv;
    private TextView mUserNameTv;
    private TextView mSettingTv;
    private TextView mAddressTv;
    private TextView mShareTv;

    private TextView mWaitPayOrderTv;
    private TextView mWaitConfirmOrderTv;
    private TextView mCompleteOrderTv;
    private TextView mAllOrderTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_me,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mUserIconIv = view.findViewById(R.id.mUserIconIv);
        mUserNameTv = view.findViewById(R.id.mUserNameTv);
        mSettingTv = view.findViewById(R.id.mSettingTv);
        mAddressTv = view.findViewById(R.id.mAddressTv);
        mShareTv = view.findViewById(R.id.mShareTv);

        mWaitPayOrderTv = view.findViewById(R.id.mWaitPayOrderTv);
        mWaitConfirmOrderTv = view.findViewById(R.id.mWaitConfirmOrderTv);
        mCompleteOrderTv = view.findViewById(R.id.mCompleteOrderTv);
        mAllOrderTv = view.findViewById(R.id.mAllOrderTv);

        mUserNameTv.setOnClickListener(this);
        mUserIconIv.setOnClickListener(this);
        mSettingTv.setOnClickListener(this);
        mAddressTv.setOnClickListener(this);
        mShareTv.setOnClickListener(this);

        mWaitPayOrderTv.setOnClickListener(this);
        mWaitConfirmOrderTv.setOnClickListener(this);
        mCompleteOrderTv.setOnClickListener(this);
        mAllOrderTv.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData(){
        if(CommonUtils.isLogined()){
            String userIcon = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_ICON);
            //是否为空
            if (!userIcon.isEmpty()) {
                CommonExt.loadUrl(userIcon,mUserIconIv);
            }else{
                mUserIconIv.setImageResource(R.drawable.icon_default_user);
            }
            mUserNameTv.setText(AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_NAME));
        }else{
            mUserIconIv.setImageResource(R.drawable.icon_default_user);
            mUserNameTv.setText(getString(R.string.un_login_text));
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v.getId()==R.id.mUserNameTv||v.getId()==R.id.mUserIconIv){
            if(CommonUtils.isLogined()) {
                intent = new Intent(getActivity(), UserInfoActivity.class);
            }else{
                intent = new Intent(getActivity(), LoginActivity.class);
            }
            startActivity(intent);
        }

        else if(v.getId()==R.id.mSettingTv){
            intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }

        else if(v.getId()==R.id.mShareTv){
           new ShareUtils(getContext(),getActivity());
        }

        else if(v.getId()==R.id.mAddressTv){
            if(CommonUtils.isLogined()) {
                intent = new Intent(getActivity(), ShipAddressActivity.class);
                startActivity(intent);
            }else{
                intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        }

        else if(v.getId()==R.id.mWaitPayOrderTv){
            if(CommonUtils.isLogined()) {
                intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_WAIT_PAY);
                startActivity(intent);
            }else{
                intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        }

        else if(v.getId()==R.id.mWaitConfirmOrderTv){
            if(CommonUtils.isLogined()) {
                intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_WAIT_CONFIRM);
                startActivity(intent);
            }else{
                intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        }

        else if(v.getId()==R.id.mCompleteOrderTv){
            if(CommonUtils.isLogined()) {
                intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_COMPLETED);
                startActivity(intent);
            }else{
                intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        }

        else if(v.getId()==R.id.mAllOrderTv){
            if(CommonUtils.isLogined()) {
                intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL);
                startActivity(intent);
            }else{
                intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        }

    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getContext(),"分享成功了",Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getContext(),"分享失败了"+t.getMessage(),Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getContext(),"分享取消了",Toast.LENGTH_LONG).show();
        }
    };


    private UMWeb initUMWeb(Context context) {
        UMWeb web = new UMWeb(ShareContent.url);
        web.setTitle("商城的标题"); //标题
        web.setThumb(new UMImage(context, ShareContent.imageurl));  //缩略图
        web.setDescription("商品大减价大家快来买啊全是好东西哟！~~~");//描述
        return web;
    }
}
