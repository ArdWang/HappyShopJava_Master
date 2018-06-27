package com.hs.hsj.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hs.base.ext.CommonExt;
import com.hs.base.widgets.RoundRectImageView;
import com.hs.hsj.R;
import java.util.List;

/**
 * Created by rnd on 2018/4/13.
 *
 */
public class TopicAdapter extends PagerAdapter{
    private Context context;
    private List<String> list;
    private OnItemClick onItemClick;

    public TopicAdapter(Context context,List<String> list,OnItemClick onItemClick){
        this.context = context;
        this.list = list;
        this.onItemClick = onItemClick;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View rooView = LayoutInflater.from(context).inflate(R.layout.layout_topic_item, null);
        RoundRectImageView mTopicIv = rooView.findViewById(R.id.mTopicIv);
        CommonExt.loadUrl(list.get(position),mTopicIv);

        rooView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClick!=null){
                    onItemClick.onItemClick(v,position);
                }
            }
        });

        container.addView(rooView);
        return rooView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public interface OnItemClick{
        void onItemClick(View view, int pos);
    }
}
