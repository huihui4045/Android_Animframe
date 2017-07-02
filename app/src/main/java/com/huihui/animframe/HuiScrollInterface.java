package com.huihui.animframe;

/**
 * Created by molu_ on 2017/7/2.
 */

public interface HuiScrollInterface {

    /****
     * 当滑动时调用该方法，用来控制控件执行相应的动画
     * @param ratio 动画执行的百分比
     */
     void onDiscroll(float ratio);

    /****
     * 重置动画  让所有的view 所有的属性动画都恢复到原来的样子
     */
    void onResetDiscroll();

}
