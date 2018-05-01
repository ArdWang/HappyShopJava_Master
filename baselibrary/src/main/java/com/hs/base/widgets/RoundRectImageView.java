package com.hs.base.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by rnd on 2018/4/13.
 * 圆角图标
 * 左上角与右上角为圆角
 */

@SuppressLint("AppCompatCustomView")
public class RoundRectImageView extends ImageView {

    /*圆角的半径，依次为左上角xy半径，右上角，右下角，左下角*/
    private float[] rids = {15.0f, 15.0f, 15.0f, 15.0f, 0.0f,0.0f,0.0f,0.0f};


    public RoundRectImageView(Context context) {
        super(context);
    }


    public RoundRectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public RoundRectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 画图
     * by Hankkin at:2015-08-30 21:15:53
     *
     * @param canvas
     */
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        Paint paint =new Paint();
        paint.isAntiAlias();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        /*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
        path.addRoundRect(new RectF(0, 0, w, h), rids, Path.Direction.CW);
        path.setFillType(Path.FillType.INVERSE_WINDING);
        //canvas.clipPath(path);
        canvas.drawPath(path,paint);

        super.onDraw(canvas);
    }
}
