package com.hs.hsj.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hs.base.ui.fragment.BaseFragment;
import com.hs.hsj.R;
import com.hs.hsj.utils.DataCleanManager;
import com.hs.hsj.widgets.CustomDialog;

public class SpaceFragment extends BaseFragment implements View.OnClickListener{

    private TextView mClearTotal;
    private RelativeLayout mClearBtn;
    private String total;
    private CustomDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_space,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mClearTotal = view.findViewById(R.id.mClearTotal);
        mClearBtn = view.findViewById(R.id.mClearBtn);
        mClearBtn.setOnClickListener(this);
    }

    private void initData(){
        try {
            total = DataCleanManager.getTotalCacheSize(getActivity());
            mClearTotal.setText(total);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mClearBtn:
                showDialog();
                break;
        }
    }

    /**
     * 清除提示操作
     */

    private void showDialog(){
        final CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setTips("清除提示");
        builder.setContent("你确定要清除 "+total+" 缓存吗？");

        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //执行确定操作
                dialogInterface.dismiss();
                try {
                    //清理存储空间
                    DataCleanManager.clearAllCache(getActivity());
                    String total = DataCleanManager.getTotalCacheSize(getActivity());
                    mClearTotal.setText(total);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }).setnegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //执行取消操作
                dialogInterface.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();
        //dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

    }
}
