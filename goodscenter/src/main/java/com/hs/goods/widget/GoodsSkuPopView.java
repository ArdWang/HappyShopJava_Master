package com.hs.goods.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hs.base.ext.CommonExt;
import com.hs.base.utils.YuanFenConverter;
import com.hs.goods.R;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.event.AddCartEvent;
import com.hs.goods.event.SkuChangedEvent;
import com.hs.goods.model.goods.GoodsSku;
import com.hs.provider.common.CommonUtils;
import com.hs.provider.router.RouterPath;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import ren.qinc.numberbutton.NumberButton;

/**
 * Created by Administrator on 2018/4/15.
 *
 */
public class GoodsSkuPopView extends PopupWindow implements View.OnClickListener{
    private Context context;
    //根视图
    private View mRootView;

    private ImageView mCloseIv;

    private Button mAddCartBtn;

    private NumberButton mSkuCountBtn;

    private ImageView mGoodsIconIv;

    private TextView mGoodsPriceTv;

    private TextView mGoodsCodeTv;

    private List<SkuView> mSkuViewList = new ArrayList<>();

    private LinearLayout mSkuView;

    @SuppressLint("ClickableViewAccessibility")
    public GoodsSkuPopView(Context mcontext){
        this.context = mcontext;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflater.inflate(R.layout.layout_sku_pop, null);
        mCloseIv = mRootView.findViewById(R.id.mCloseIv);
        mAddCartBtn = mRootView.findViewById(R.id.mAddCartBtn);
        mSkuCountBtn = mRootView.findViewById(R.id.mSkuCountBtn);
        mGoodsIconIv = mRootView.findViewById(R.id.mGoodsIconIv);
        mGoodsPriceTv = mRootView.findViewById(R.id.mGoodsPriceTv);
        mGoodsCodeTv = mRootView.findViewById(R.id.mGoodsCodeTv);
        mSkuView = mRootView.findViewById(R.id.mSkuView);

        initView();

        // 设置SelectPicPopupWindow的View
        this.setContentView(mRootView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        setBackgroundDrawable(context.getResources().getDrawable(R.color.common_black));
        getBackground().setAlpha(5);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = mRootView.findViewById(R.id.mPopView).getTop();
                int y = (int)motionEvent.getY();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }




    private void initView() {
        mCloseIv.setOnClickListener(this);
        mAddCartBtn.setOnClickListener(this);
        //mSkuCountBtn.setOnClickListener(this);

        mSkuCountBtn.setCurrentNumber(1);
        EditText editText = mSkuCountBtn.findViewById(R.id.text_count);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //EventBus发送数据
                EventBus.getDefault().post(new SkuChangedEvent());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    /**
        设置商品图标
     */
    public void setGoodsIcon(String text) {
        CommonExt.loadUrl(text,mGoodsIconIv);
    }

    /**
        设置商品价格
     */
    @SuppressLint("SetTextI18n")
    public void setGoodsPrice(Float text) {
        try {
            mGoodsPriceTv.setText("¥ "+YuanFenConverter.changeY2FSG(text));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")

    /*
        设置商品编号
     */
    public void setGoodsCode(String text) {
        mGoodsCodeTv.setText("商品编号:" + text);
    }

    /**
        设置商品SKU 分为多个信息
     */
    public void setSkuData(List<GoodsSku> list) {
        for (GoodsSku sku:list) {
            SkuView skuView =new  SkuView(context);
            skuView.setSkuData(sku);
            mSkuViewList.add(skuView);
            mSkuView.addView(skuView);
        }
    }

    /**
        获取选中的SKU
     */
    public String getSelectSku(){
        StringBuilder skuInfo = new StringBuilder();
        for (SkuView skuView:mSkuViewList) {
            skuInfo.append(skuView.getSkuInfo().split(GoodsConstant.SKU_SEPARATOR)[1]).append(GoodsConstant.SKU_SEPARATOR);
        }
        return skuInfo.substring(0,skuInfo.length() - 1);//刪除最后一个分隔
    }


    /**
       获取商品数量
    */
    public int getSelectCount(){
       return mSkuCountBtn.getNumber();
    }



    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.mCloseIv) {
            dismiss();
        }
        else if (i == R.id.mAddCartBtn) {
            //看是否已经登录
            if(CommonUtils.isLogined()) {
                //Event发送数据
                EventBus.getDefault().post(new AddCartEvent());
            }else {
                ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation();
            }
            dismiss();
        }
    }
}
