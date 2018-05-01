package com.hs.order.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseMvpActivity;
import com.hs.order.R;
import com.hs.order.common.OrderConstant;
import com.hs.order.model.ShipAddre;
import com.hs.order.presenter.EditShipAddrePresenter;
import com.hs.order.presenter.view.EditShipAddreView;


/**
 * Created by rnd on 2018/4/20.
 *
 */

public class ShipAddressEditActivity extends BaseMvpActivity<EditShipAddrePresenter> implements EditShipAddreView{

    private ShipAddre shipAddre;
    private Button mSaveBtn;
    private EditText mShipNameEt;
    private EditText mShipMobileEt;
    private EditText mShipAddressEt;

    private TextView mTitleTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_edit_address;
    }

    @Override
    protected void initView() {
        mSaveBtn = findViewById(R.id.mSaveBtn);
        mShipNameEt = findViewById(R.id.mShipNameEt);
        mShipMobileEt = findViewById(R.id.mShipMobileEt);
        mShipAddressEt = findViewById(R.id.mShipAddressEt);
        mTitleTv = findViewById(R.id.mTitleTv);

        mSaveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.mSaveBtn){
            if(mShipNameEt.getText().toString().isEmpty()||mShipNameEt.getText().toString().equals("")){
                CommonExt.toast("名称不能为空");
                return;
            }
            if(mShipMobileEt.getText().toString().isEmpty()||mShipMobileEt.getText().toString().equals("")){
                CommonExt.toast("电话不能为空");
                return;
            }
            if(mShipAddressEt.getText().toString().isEmpty()||mShipAddressEt.getText().toString().equals("")){
                CommonExt.toast("地址不能为空");
                return;
            }

            if(shipAddre==null){
                mPresenter.addShipAddre(mShipNameEt.getText().toString(),mShipMobileEt.getText().toString(),
                        mShipAddressEt.getText().toString(),this);
            }else{
                shipAddre.setShipusername(mShipNameEt.getText().toString());
                shipAddre.setShipusermobile(mShipMobileEt.getText().toString());
                shipAddre.setShipaddress(mShipAddressEt.getText().toString());
                mPresenter.editShipAddre(shipAddre,this);
            }
        }
    }

    /**
      初始化数据
    */
    private void initData() {
        shipAddre = (ShipAddre) getIntent().getSerializableExtra(OrderConstant.KEY_SHIP_ADDRESS);
        if(shipAddre!=null){
            mTitleTv.setText("编辑地址");
            mShipNameEt.setText(shipAddre.getShipusername());
            mShipMobileEt.setText(shipAddre.getShipusermobile());
            mShipAddressEt.setText(shipAddre.getShipaddress());
        }else{
            mTitleTv.setText("新增地址");
        }
    }



    @Override
    public void onAddShipAddreResult(Boolean result) {
        CommonExt.toast("添加成功");
        finish();
    }

    @Override
    public void onEditShipAddreResult(Boolean result) {
        CommonExt.toast("修改成功");
        finish();
    }
}
