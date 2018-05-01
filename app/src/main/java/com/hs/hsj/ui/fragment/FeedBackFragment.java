package com.hs.hsj.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.fragment.BaseFragment;
import com.hs.hsj.R;
import com.hs.hsj.ui.activity.SettingAllActivity;


public class FeedBackFragment extends BaseFragment implements View.OnClickListener{
    private EditText mFeedbackTitle;
    private EditText mFeedbackCon;
    private Button mFeedbackBtn;
    private SettingAllActivity sa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        sa = (SettingAllActivity)getActivity();
        View view = inflater.inflate(R.layout.fragment_feedback,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       initView(view);
    }

    private void initView(View view) {
        mFeedbackTitle = view.findViewById(R.id.mFeedbackTitle);
        mFeedbackCon = view.findViewById(R.id.mFeedbackCon);
        mFeedbackBtn = view.findViewById(R.id.mFeedbackBtn);
        mFeedbackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mFeedbackBtn:
                if (!sa.userName.isEmpty()) {
                    final String title = mFeedbackTitle.getText().toString().trim();
                    final String con = mFeedbackCon.getText().toString().trim();
                    if(!title.isEmpty()&&!con.isEmpty()) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        // i.setType("text/plain"); //模拟器请使用这行
                        i.setType("message/rfc822"); // 真机上使用这行
                        i.putExtra(Intent.EXTRA_EMAIL,
                                new String[]{"278161009@qq.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, sa.userName + "，" + title);
                        i.putExtra(Intent.EXTRA_TEXT, con);
                        startActivity(Intent.createChooser(i,
                                sa.userName + "，" + title));
                    }

                } else {
                    CommonExt.toast("输入不能为空!");
                }

                break;
        }

    }
}
