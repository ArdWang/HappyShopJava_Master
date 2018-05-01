package com.hs.base.widgets;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.hs.base.R;

/**
 * Created by rnd on 2018/4/8.
 * 自定义headBar
 */

public class HeaderBar extends FrameLayout{
    //是否显示返回图标
    private boolean isShowBack = true;
    //title文字
    private String titleText;
    //右侧文字
    private String rightText;

    private ImageView mLeftIv;

    private TextView mTitleTv;

    private TextView mRightTv;


    public HeaderBar(@NonNull Context context) {
        super(context);
    }

    public HeaderBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar);
        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack,true);
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText);
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText);
        initView(context);
        typedArray.recycle();

    }

    private void initView(final Context context){

        View view = View.inflate(context,R.layout.layout_header_bar,this);
        mLeftIv = view.findViewById(R.id.mLeftIv);
        mTitleTv = view.findViewById(R.id.mTitleTv);
        mRightTv = view.findViewById(R.id.mRightTv);

        if(isShowBack){
            mLeftIv.setVisibility(View.VISIBLE);
        }else{
            mLeftIv.setVisibility(View.GONE);
        }

        if(!titleText.isEmpty()){
            mTitleTv.setText(titleText);
        }

        if(rightText!=null) {
            if (!rightText.isEmpty()) {
                mRightTv.setText(rightText);
                mRightTv.setVisibility(View.VISIBLE);
            }
        }


        /**
         * 点击按钮关闭当前的Activity
         */
        mLeftIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否为同一个对象
                if(context instanceof Activity){
                    ((Activity)context).finish();
                }
            }
        });


    }

    public HeaderBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 获取左侧视图
     * @return n
     */
    public ImageView getLeftView(){
        return mLeftIv;
    }

    /**
     * 获取右侧视图
     * @return n
     */
    public TextView getRightView(){
        return mRightTv;
    }

    /**
     * 获取右侧的文字
     * @return n
     */
    public String getRightText(){
        return mRightTv.getText().toString();
    }


}
