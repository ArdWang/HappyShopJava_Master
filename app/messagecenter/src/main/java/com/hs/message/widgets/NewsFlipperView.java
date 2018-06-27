package com.hs.message.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.hs.base.utils.DisplayUtils;
import com.hs.message.R;
import java.util.List;

/**
 * Created by rnd on 2018/4/12.
 * 此类里面的字体需要找px转sp的工具类
 */

public class NewsFlipperView extends FrameLayout{
    private ViewFlipper mFlipperView;
    private Context context;

    public NewsFlipperView(@NonNull Context context) {
        super(context);
    }

    public NewsFlipperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View rootView = View.inflate(context, R.layout.layout_news_flipper, null);
        mFlipperView = rootView.findViewById(R.id.mFlipperView);
        mFlipperView.setInAnimation(context, R.anim.news_bottom_in);
        mFlipperView.setOutAnimation(context, R.anim.news_bottom_out);
        addView(rootView);
    }

    public NewsFlipperView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        View rootView = View.inflate(context, R.layout.layout_news_flipper, null);
        mFlipperView = rootView.findViewById(R.id.mFlipperView);
        mFlipperView.setInAnimation(context, R.anim.news_bottom_out);
        mFlipperView.setOutAnimation(context, R.anim.news_bottom_in);
        addView(rootView);
    }


    /**
        构建公告
     */
    private View buildNewsView(String text) {
        TextView textView =new TextView(context);
        textView.setText(text);
        float a= getResources().getDimension(R.dimen.text_small_size);
        float q = DisplayUtils.px2sp(context,a);
        textView.setTextSize(q);
        //Log.i("font is TAG",q+"");
        textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        return textView;
    }

    /**
        设置公告数据
     */
    public void setData(List<String> data) {
        for (String text:data) {
            mFlipperView.addView(buildNewsView(text));
        }
        mFlipperView.startFlipping();
    }

}
