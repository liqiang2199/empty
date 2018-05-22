package com.empty.cup.wight;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by empty cup on 2018/1/30.
 *  获取 ScrollView  的子控件
 *  1 适合 高度相等子的控件
 */

public class ScalScrollView  extends ScrollView{


    private View mHeadView;
    private int mWidth;
    private int mHeigth;

    //添加 控件到数组  放大
    private ArrayList<View> textViewLists = new ArrayList<>();

    // 当前坐标值
    private float currentX = 0;
    private float currentY = 0;
    // 记录 移动坐标值
    private float distanceX = 0;
    private float distanceY = 0;

    //记录 最后坐标值
    private float lastX = 0;
    private float lastY = 0;
    // 上下滑动标记
    private boolean upDownSlide = false;
    // 是否正在下拉
    private boolean mIsPulling;
    // 是否是向上拉
    private boolean mIsUpPulling;
    // 记录首次按下位置
    private int mLastY;

    // 最大的放大倍数
    private float mScaleTimes = 1.5f;
    // 滑动放大系数：系数越大，滑动时放大程度越大
    private float mScaleRatio = 0.2f;
    // 回弹时间系数：系数越小，回弹越快
    private float mReplyRatio = 0.5f;


    //ScrollView  总体绘制高度
    private int meaSureHeight;
    private int meaSureWidth;


    public ScalScrollView(Context context) {
        super(context);
    }

    public ScalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //计算 ScrollView  总体绘制高度
        meaSureHeight = getHeight();
        meaSureWidth = getWidth();
//        Log.v("this","    ScrollView  总体绘制高度      "+meaSureHeight+"    ScrollView  总体绘制宽度     ");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOverScrollMode(1);
        View child = getChildAt(0);
        if (child != null&&child instanceof ViewGroup){
            // 获取默认第一个子View
            //获取 ScrollView 里面的所有的子控件  只有是TextView 时 就计算她的宽度 并放大
            ViewGroup viewGroup = (ViewGroup) getChildAt(0);
            Log.v("this","viewGroup.getChildAt(2) 获取子控件数量    "+viewGroup.getChildAt(2)
                    +"     中共数量     "+viewGroup.getChildCount());
            if (viewGroup.getChildAt(0) != null){
                mHeadView = viewGroup.getChildAt(0);
            }
            for (int i =0;i<viewGroup.getChildCount();i++){
                View view = viewGroup.getChildAt(i);
                if (view instanceof TextView){
                    textViewLists.add(view);
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取第一个控件的高度
        mWidth = mHeadView.getMeasuredWidth();
        mHeigth = mHeadView.getMeasuredHeight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        currentX = ev.getX();
        currentY = ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                //移动 记录坐标
                distanceX = currentX - lastX;
                distanceY = currentY - lastY;
                if (Math.abs(distanceX) < Math.abs(distanceY) && Math.abs(distanceY) > 10) {
                    upDownSlide = true;
                }
                break;
        }
        //记录最后一次XY 坐标
        lastX = currentX;
        lastY = currentY;

        if (upDownSlide && mHeadView != null) {
            commOnTouchEvent(ev);
        }

        return super.dispatchTouchEvent(ev);

    }

    private void commOnTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                // 手指离开后头部恢复
                if (mIsPulling&&!mIsUpPulling&&getScrollY() == 0){
                    //下拉
                    Log.v("this","     下拉  1211111  ");
                    mIsPulling = false;
                    replyView();
                    clear();
                }

                if (mIsUpPulling&&!mIsPulling&&getScrollY() >= meaSureHeight){
                    //上拉
                    Log.v("this","     上拉   131232131333333 ");
                    mIsUpPulling = false;
                    replyViewDown();
                    clear();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                //处理 下拉 和  上拉

//                Log.v("this",getScrollY()+"     ev.getY() - mLastY   手指滑动   "+(ev.getY()));
                int distance = (int) ((ev.getY() - mLastY) * mScaleRatio);


                Log.v("this","      手指滑动的距离  111    "+distance);

                // 当前位置比记录位置要小时正常返回
                if (distance < 0) {
                    //上拉
                    mIsPulling = false;
                    mIsUpPulling = true;
                    setZoomDown(distance);
//                    break;
                }else{
                    //下拉
                    if (!mIsPulling) {
                        // 第一次下拉 和 上拉
//                    Log.v("this","      手指滑动的距离      "+getScaleY());
                        if (getScrollY() == 0) {
                            // 滚动到顶部时记录位置，否则正常返回
                            mLastY = (int) ev.getY();
                        } else {

                            break;
                        }
                    }

                    mIsPulling = true;
                    mIsUpPulling = false;
                    setZoom(distance);
                }


                break;
        }
    }
    /**
     * @Description 向下拉缩放
     */
    private void setZoom(float s) {
        float scaleTimes = (float) ((mWidth + s) / (mWidth * 1.0));
        // 如超过最大放大倍数则直接返回

        if (scaleTimes > mScaleTimes) {
            return;
        }
        Log.v("this","      超过最大放大倍数      "+scaleTimes);
        ViewGroup.LayoutParams layoutParams = mHeadView.getLayoutParams();
        layoutParams.width = (int) (mWidth + s);
        layoutParams.height = (int) (mHeigth * ((mWidth + s) / mWidth));
        // 设置控件水平居中
        ((MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - mWidth) / 2, 0, 0, 0);
//        mHeadView.setLayoutParams(layoutParams);
        for (View textView :textViewLists){
            textView.setLayoutParams(layoutParams);
        }
    }

    /**
     * @Description 向上拉缩放
     */
    private void setZoomDown(float s) {
        float scaleTimes = (float) ((mWidth + s) / (mWidth * 1.0));
        // 如超过最大放大倍数则直接返回
//        Log.v("this","      向上拉缩放      "+scaleTimes+"    "+mWidth+"   "+s);
        if (scaleTimes > mScaleTimes) {
            return;
        }

        ViewGroup.LayoutParams layoutParams = mHeadView.getLayoutParams();
        layoutParams.width = (int) (mWidth - s);
        layoutParams.height = (int) (mHeigth * ((mWidth - s) / mWidth));
        // 设置控件水平居中
        ((MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - mWidth) / 2, 0, 0, 0);
//        mHeadView.setLayoutParams(layoutParams);
        for (View textView :textViewLists){
            textView.setLayoutParams(layoutParams);
        }
    }

    /**
     * @Description 回弹动画
     */
    private void replyView() {
        final float distance = mHeadView.getMeasuredWidth() - mHeigth;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * mReplyRatio));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoom((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    /**
     * @Description 回弹动画 (向上)
     */
    private void replyViewDown() {
        final float distance = mHeadView.getMeasuredWidth() - mHeigth;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * mReplyRatio));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoomDown((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    /**
     * @Description 清除属性值
     */
    private void clear() {
        lastX = 0;
        lastY = 0;
        distanceX = 0;
        distanceY = 0;
        upDownSlide = false;
    }

}
