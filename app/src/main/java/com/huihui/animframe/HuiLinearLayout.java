package com.huihui.animframe;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by molu_ on 2017/7/2.
 */

public class HuiLinearLayout extends LinearLayout {



    public HuiLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        HuiLayoutParams lp= (HuiLayoutParams) params;

        if (!isDiscrollvable(lp)){//判断是否有自定义属性，没有则不包裹一层容器
            super.addView(child,params);
        }else {

            HuiFrameLayout frameLayout=new HuiFrameLayout(getContext());

            frameLayout.addView(child);

            frameLayout.setmDiscrollveAlpha(lp.mDiscrollveAlpha);
            frameLayout.setmDiscrollveFromBgColor(lp.mDiscrollveFromBgColor);
            frameLayout.setmDiscrollveToBgColor(lp.mDiscrollveToBgColor);
            frameLayout.setmDiscrollveScaleX(lp.mDiscrollveScaleX);
            frameLayout.setmDisCrollveTranslation(lp.mDisCrollveTranslation);


            super.addView(frameLayout, params);
        }




    }

    private boolean isDiscrollvable(HuiLayoutParams p){
        return p.mDiscrollveAlpha||
                p.mDiscrollveScaleX||
                p.mDiscrollveScaleY||
                p.mDisCrollveTranslation!=-1||
                (p.mDiscrollveFromBgColor!=-1&&
                        p.mDiscrollveToBgColor!=-1);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new HuiLayoutParams(getContext(),attrs);
    }


    public  class HuiLayoutParams extends LinearLayout.LayoutParams{

        public int mDiscrollveFromBgColor;//背景颜色变化开始值
        public int mDiscrollveToBgColor;//背景颜色变化结束值
        public boolean mDiscrollveAlpha;//是否需要透明度动画
        public int mDisCrollveTranslation;//平移值
        public boolean mDiscrollveScaleX;//是否需要x轴方向缩放
        public boolean mDiscrollveScaleY;//是否需要y轴方向缩放

        public HuiLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            //解析attrs得到自定义的属性，保存
            TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.DiscrollView_LayoutParams);
            mDiscrollveAlpha = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha, false);
            mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
            mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
            mDisCrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
            mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
            mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
            a.recycle();
        }
    }
}
