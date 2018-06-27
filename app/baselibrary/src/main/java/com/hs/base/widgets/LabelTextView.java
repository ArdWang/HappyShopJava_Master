package com.hs.base.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.hs.base.R;

/**
 * Created by rnd on 2018/4/23.
 *
 */
public class LabelTextView extends FrameLayout{

    private CharSequence mLabelText;
    private CharSequence mContentText;
    private Context context;

    TextView mLabelTv;
    TextView mContentTv;

    public LabelTextView(@NonNull Context context) {
        this(context,null);
    }

    public LabelTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @SuppressLint("Recycle")
    public LabelTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context,attrs);

    }

    private void initData(Context context,AttributeSet attrs){
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelText);
        mLabelText = typedArray.getText(R.styleable.LabelText_labelText);
        mContentText = typedArray.getText(R.styleable.LabelText_contentText);
        initView();
        typedArray.recycle();
    }

    private void initView() {
        View view = View.inflate(context,R.layout.layout_label_textview, this);
        mLabelTv = view.findViewById(R.id.mLabelTv);
        mContentTv = view.findViewById(R.id.mContentTv);

        if(mLabelText!=null) {
            mLabelTv.setText(mLabelText);
        }

        if(mContentText!=null) {
            mContentTv.setText(mContentText);
        }

    }

    public void setContentText(String text){
        mContentTv.setText(text);
    }


}
