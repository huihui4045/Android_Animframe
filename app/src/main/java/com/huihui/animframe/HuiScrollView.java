package com.huihui.animframe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by molu_ on 2017/7/2.
 */

public class HuiScrollView extends ScrollView {

    private HuiLinearLayout mContent;

    public HuiScrollView(Context context) {
        super(context);
    }

    public HuiScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mContent = (HuiLinearLayout) getChildAt(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        View first = mContent.getChildAt(0);
        first.getLayoutParams().height = getHeight();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        int scrollViewHeight = getHeight();


        for (int i = 0; i < mContent.getChildCount(); i++) {


            View child = mContent.getChildAt(i);
            int childHeight = child.getHeight();


            if (!(child instanceof HuiScrollInterface)){
                continue;
            }


            HuiScrollInterface scrollInterface = (HuiScrollInterface) child;



            int childTop = child.getTop();

            int absoluteTop = childTop - t;

            if (absoluteTop <= scrollViewHeight) {

                int visibleGap = scrollViewHeight - absoluteTop;

                float radio = visibleGap / (float) childHeight;



                scrollInterface.onDiscroll(clamp(radio, 1f, 0f));
            } else {

                scrollInterface.onResetDiscroll();
            }


        }


    }

    //求三个数的中间大小的一个数。
    public static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, max), min);
    }
}
