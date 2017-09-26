package com.empty.cup.wight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.empty.cup.R;

/**
 * Created by empty cup on 2017/9/22.
 * 练习画图
 */

public class PaintPracticeView extends View {

    private Paint mPaint;
    private Bitmap bitmap;
    private Bitmap bitmappass;
    private int index=-40;
    private static final int NEED_INVALIDATE=0x23;

    private int width;
    private int bitmapHeight;

    private int bitmapWidth;
    private int height;

    private Path mPath;//画出图片路径


    public PaintPracticeView(Context context) {
        super(context);
        Init_Paint();
    }

    public PaintPracticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init_Paint();
    }

    public PaintPracticeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init_Paint();
    }

    //初始化画笔
    private void Init_Paint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mPaint.setDither(true);
//        mPaint.setTextSize(100);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setShadowLayer(2, 3, 3, Color.rgb(180, 180, 180));

        bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_vector_home_normal);//创建Bitmap对象
        //第二个图片
        bitmappass= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_vector_home_pressed);//bitmappass

        bitmapWidth=bitmap.getWidth();//得到图片的宽
        bitmapHeight=bitmap.getHeight();//得到图片的高

        mPath = new Path();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmappass,400,400,mPaint);
        //画一个相同大小的图片

        canvas.drawBitmap(bitmap,400,400,mPaint);


    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }



}
