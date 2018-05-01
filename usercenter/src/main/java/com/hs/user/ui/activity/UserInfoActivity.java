package com.hs.user.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.clb.commonlibrary.utils.DateUtil;
import com.hs.base.common.BaseConstant;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseTakePhotoActivity;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.base.utils.GlideUtils;
import com.hs.provider.common.ProviderConstant;
import com.hs.user.R;
import com.hs.user.model.User;
import com.hs.user.presenter.UserInfoPresenter;
import com.hs.user.presenter.view.UserInfoView;
import com.hs.user.utils.UserPrefsUtils;
import com.jph.takephoto.model.TResult;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import org.json.JSONObject;


public class UserInfoActivity extends BaseTakePhotoActivity<UserInfoPresenter> implements UserInfoView{
    private RelativeLayout mUserIconView;
    private ImageView mUserIconIv;
    private EditText mUserNameEt;
    private RadioButton mGenderMaleRb,mGenderFemaleRb;
    private TextView mUserMobileTv;
    private EditText mUserSignEt;
    private TextView mRightTv;
    private String mLocalFileUrl;
    private String mRemoteFileUrl;
    private String username,usericon,mobile,sign,sex,userid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected void initView() {
        mUserIconView = findViewById(R.id.mUserIconView);
        mUserIconIv = findViewById(R.id.mUserIconIv);
        mUserNameEt = findViewById(R.id.mUserNameEt);
        mGenderMaleRb = findViewById(R.id.mGenderMaleRb);
        mGenderFemaleRb = findViewById(R.id.mGenderFemaleRb);
        mUserMobileTv = findViewById(R.id.mUserMobileTv);
        mUserSignEt = findViewById(R.id.mUserSignEt);
        mRightTv = findViewById(R.id.mRightTv);

        mUserIconView.setOnClickListener(this);
        mRightTv.setOnClickListener(this);

    }

    private void initData(){
        //获取当前的数据
        username = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_NAME);
        usericon = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_ICON);
        mobile = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_MOBILE);
        sex = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_GENDER);
        sign = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_SIGN);
        userid = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_ID);

        mRemoteFileUrl = usericon;

        if(!usericon.equals("")){
            GlideUtils.loadUrlImage(this,usericon,mUserIconIv);
        }

        mUserNameEt.setText(username);
        mUserMobileTv.setText(mobile);

        if(sex.equals("0")){
            mGenderMaleRb.setChecked(true);
        }else{
            mGenderFemaleRb.setChecked(true);
        }

        mUserSignEt.setText(sign);

    }


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_user_info;
    }


    @Override
    public void takeSuccess(TResult result) {
        mLocalFileUrl = result.getImage().getCompressPath();
        mPresenter.uploadToken(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mUserIconView) {
            showAlertView();

        } else if (i == R.id.mRightTv) {
            int cuserid = Integer.parseInt(userid);
            int csex;
            if (mGenderMaleRb.isChecked()) {
                csex = 0;
            } else {
                csex = 1;
            }
            mPresenter.editUser(cuserid, mUserNameEt.getText().toString().trim(), mRemoteFileUrl,
                    csex, mUserSignEt.getText().toString().trim(), this);

        }
    }

    /**
     * 上传图片到七牛
     * @param filePath 要上传的图片路径
     * @param token 在七牛官网上注册的token
     */
    private void uploadImageToQiniu(String filePath, String token) {
        UploadManager uploadManager = new UploadManager();
        // 设置图片名字
        String key = "icon_"+ DateUtil.getCurrentDate("yyyyMMddHHmmss");

        uploadManager.put(filePath, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                // info.error中包含了错误信息，可打印调试
                // 上传成功后将key值上传到自己的服务器
                try {
                    //String url = res.toString();
                    //Log.i("TAG","url"+res.get("hash"));
                    mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + res.get("key");

                    GlideUtils.loadUrlImage(UserInfoActivity.this, mRemoteFileUrl,mUserIconIv);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, null);
    }

    @Override
    public void onGetUploadTokenResult(String result) {
        uploadImageToQiniu(mLocalFileUrl,result);
    }

    @Override
    public void onEditUserResult(User result) {
        CommonExt.toast("修改资料成功");
        UserPrefsUtils.putUserInfo(result);
    }

}
