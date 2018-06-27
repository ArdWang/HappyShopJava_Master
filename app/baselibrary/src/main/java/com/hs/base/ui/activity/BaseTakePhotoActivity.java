package com.hs.base.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.clb.commonlibrary.utils.DateUtil;
import com.hs.base.ext.CommonExt;
import com.hs.base.presenter.BasePresenter;
import com.hs.base.presenter.view.BaseView;
import com.hs.base.utils.PMUtils;
import com.hs.base.widgets.ProgressLoading;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import java.io.File;

/**
 * Created by rnd on 2018/4/10.
 *
 */

public abstract class BaseTakePhotoActivity<P extends BasePresenter> extends BaseActivity implements
        BaseView,TakePhoto.TakeResultListener,View.OnClickListener,InvokeListener {
    private TakePhoto mTakePhoto;
    private File mTempFile;
    public P mPresenter;
    private ProgressLoading progressLoading;
    private InvokeParam invokeParam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mTakePhoto = new TakePhotoImpl(this,this);
        mTakePhoto.onCreate(savedInstanceState);

    }


    protected void init(){
        setContentView(getLayoutResID());
        initView();
        mPresenter = PMUtils.getT(this,0);
        if(this instanceof BaseView){
            mPresenter.setMV(this);
        }
        progressLoading = ProgressLoading.create(this);
        //ARouter注册
        ARouter.getInstance().inject(this);

    }


    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    /**
     * 安卓版本高于6.0以上的时候需要
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }


    protected abstract int getLayoutResID();


    protected void initView(){

    }

    protected void showAlertView(){
        new AlertView("选择照片", "", "取消", null,
                new String[]{"拍照", "相册"}, this, AlertView.Style.ActionSheet, new OnItemClickListener(){
            public void onItemClick(Object o,int position){
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false);
                switch (position){
                   case 0:
                       createTempFile();
                       mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile));
                       break;

                   case 1:
                       mTakePhoto.onPickFromGallery();
                       break;
                }
            }
        }).show();
    }



    @Override
    public void takeSuccess(TResult result) {
        Log.d("TakePhoto",result.getImage().getCompressPath());
    }

    @Override
    public void takeCancel() {

    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.e("takePhoto",msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTakePhoto.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void showLoading() {
        progressLoading.showLoading();
    }

    @Override
    public void hideLoading() {
        progressLoading.hideLoading();
    }

    @Override
    public void onError(String message) {
        CommonExt.toast(message);
    }

    /**
     * 创建临时照片文件
     */
    public void createTempFile(){
        String s = "yyyyMMddHHmmss";
        String tempFileName = DateUtil.getCurrentDate(s)+".png";
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            mTempFile = new File(Environment.getExternalStorageDirectory(),tempFileName);
            return;
        }

        mTempFile = new File(getFilesDir(),tempFileName);
    }

}
