package com.hs.base.widgets;

import android.content.Context;
import android.widget.ImageView;
import com.hs.base.utils.GlideUtils;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by rnd on 2018/4/12.
 *
 */

public class BannerImageLoader extends ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView);
    }
}
