package com.empty.cup.wight;

/**
 * Created by empty cup on 2017/7/19.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.empty.cup.R;

public class Chess_Panel extends View{
    private int myPanelWidth ;        //棋盘宽度
    private float myLineHeight;    //行宽
    private int maxLine = 10;        //行数

    private Paint myPaint;         //画笔
    private Bitmap myWhitePice;    //白棋子
    private Bitmap myBlackPice;    //黑棋子
    private float ratioPieceOfLineHight = 3 * 1.0f / 4;  //棋子为行宽的3/4；

    private boolean isGemOver;        //游戏结束
    public static int WHITE_WIN = 0;  //胜利为白方标志
    public static int BLACK_WIN = 1;  //胜利为黑方标志
    private boolean isWhite = true;  //判断是否是白棋先手，或当前为白棋下子

    private List<Point> myWhiteArray = new ArrayList<Point>();  //白棋子位置信息
    private List<Point> myBlackArray = new ArrayList<Point>();  //黑棋子位置信息

    private onGameListener onGameListener;  //回调接口
    private int mUnder;        //dialog的Y坐标

    public Chess_Panel(Context context) {
        this(context, null);
    }

    public Chess_Panel(Context context ,AttributeSet attributeSet){            //构造函数
        super(context , attributeSet);

        init();
    }

    // 用于回调的接口
    public interface onGameListener {
        void onGameOver(int i);
    }

    //自定义接口，用于显示dialog
    public void setOnGameListener(Chess_Panel.onGameListener onGameListener) {
        this.onGameListener = onGameListener;
    }

    //初始化函数
    private void init() {
        myPaint = new Paint();
        myPaint.setColor(0X44ff0000);     //给画笔设置颜色
        myPaint.setAntiAlias(true);      //设置画笔是否使用抗锯齿
        myPaint.setDither(true);            //设置画笔是否防抖动
        myPaint.setStyle(Paint.Style.STROKE);        //设置画笔样式，这里使用描边

        myWhitePice = BitmapFactory.decodeResource(getResources(), R.mipmap.etk); //设置棋子图片
        myBlackPice = BitmapFactory.decodeResource(getResources(), R.mipmap.eve);

    }

    //触发事件
    public boolean onTouchEvent(MotionEvent event){
        if (isGemOver) {
            return false;
        }

        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {   //判断触摸动作，ACTION_UP为单点触摸离开
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point p = getVaLidPiont(x,y);

            if (myWhiteArray.contains(p)|| myBlackArray.contains(p)) {
                return false;
            }

            if (isWhite) {
                myWhiteArray.add(p);
            }else {
                myBlackArray.add(p);
            }
            invalidate();         //invalidate()是用来刷新View的，必须在UI线程中使用
            isWhite = !isWhite;
        }
        return true;
    }


    private Point getVaLidPiont(int x , int y){
        return new Point((int)(x/myLineHeight),(int)(y/myLineHeight));
    }

    //计算布局大小
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = Math.min(widthSize, heightSize);

        if (widthMode == MeasureSpec.UNSPECIFIED) {            //MeasureSpec.UNSPECIFIED表示未知大小
            width = heightSize;
        }else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }

        setMeasuredDimension(width, width);

    }

    protected void onSizeChanged(int w, int h ,int oldw , int oldh) {         //当View大小发生改变的时候会被系统自动回调
        super.onSizeChanged(w, h, oldw, oldh);
        myPanelWidth = w;
        myLineHeight = myPanelWidth*1.0f/maxLine;
        mUnder = h - (h - myPanelWidth) / 2;

        int pieceWidth = (int) (myLineHeight*ratioPieceOfLineHight);  //棋子大小占行宽的3/4
        myWhitePice = Bitmap.createScaledBitmap(myWhitePice, pieceWidth, pieceWidth, false);    //以src为原图，创建新的图像，指定新图像的高宽以及是否可变。
        myBlackPice = Bitmap.createScaledBitmap(myBlackPice, pieceWidth, pieceWidth, false);
    }

    protected void  onDraw(Canvas canvas) {       //Canvas类相当于一块画布
        super.onDraw(canvas);
        drawBroad(canvas);
        drawPiece(canvas);
        checkGameOver();
    }

    private void drawBroad(Canvas canvas){            //画出棋盘线
        int w = myPanelWidth;
        float lineHeight = myLineHeight;
        int startX = (int) (lineHeight/2);            //棋盘线起始X坐标
        int endX = (int)(w-lineHeight/2);            //棋盘终止X坐标
        for(int i = 0; i< maxLine; i++){
            int y = (int)((i+1.5)*lineHeight);        //y坐标

            canvas.drawLine(startX, y, endX, y, myPaint);        //画棋盘横向线
            canvas.drawLine(y, startX, y, endX, myPaint);        //画棋盘纵向线
        }
    }

    //画棋子
    private void drawPiece(Canvas canvas) {
        int n1 = myWhiteArray.size();
        int n2 = myBlackArray.size();
        for(int i =0; i< n1 ;i++){
            Point whitePoint = myWhiteArray.get(i);
            canvas.drawBitmap(myWhitePice, (whitePoint.x+(1-ratioPieceOfLineHight)/2)*myLineHeight,
                    (whitePoint.y+(1-ratioPieceOfLineHight)/2)*myLineHeight, null);
            //drawBitmap(Bitmap bitmap, float left, float top, Paint paint);Bitmap：图片对象，left:偏移左边的位置，top： 偏移顶部的位置
        }

        for(int i =0; i< n2 ;i++){
            Point blackPoint = myBlackArray.get(i);
            canvas.drawBitmap(myBlackPice, (blackPoint.x+(1-ratioPieceOfLineHight)/2)*myLineHeight,
                    (blackPoint.y+(1-ratioPieceOfLineHight)/2)*myLineHeight, null);
        }
    }

    //检测游戏是否结束
    private void checkGameOver(){
        boolean whiteWin = checkFiveInLine(myWhiteArray);
        boolean blackWin = checkFiveInLine(myBlackArray);

        if (whiteWin || blackWin) {
            isGemOver = true;
            if (onGameListener != null) {
                onGameListener.onGameOver(whiteWin ? WHITE_WIN : BLACK_WIN);
            }
        }
    }
    //回调一个int数据用于设置Dialog的位置
    public int getUnder() {

        return mUnder;
    }

    //检测是否存在五棋子相连的情况
    private boolean checkFiveInLine(List<Point> myArray){
        for(Point p : myArray){
            int x = p.x;
            int y = p.y;

            boolean win_flag =                             //判断是否存在五子相连情况
                    checkHorizontal(x , y ,myArray)||checkVertical(x,y,myArray)
                            ||checkLeftDiagonal(x,y,myArray)||checkRightDiagonal(x,y,myArray);
            if (win_flag) {
                return true;
            }
        }
        return false;
    }

    //横向检查是否满足五子相连
    private boolean checkHorizontal(int x ,int y ,List<Point> myArray){
        int count = 1;
        for(int i = 1;i < 5; i++){
            if (myArray.contains(new Point(x+i,y))) {
                count++;
            }else {
                break;
            }
        }
        if (count == 5) {
            return true;
        }
        for(int i = 1;i < 5; i++){
            if (myArray.contains(new Point(x-i,y))) {
                count++;
            }else {
                break;
            }


            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    //纵向检查是否满足五子相连
    private boolean checkVertical(int x ,int y ,List<Point> myArray){
        int count = 1;
        for(int i = 1;i < 5; i++){
            if (myArray.contains(new Point(x,y+i))) {
                count++;
            }else {
                break;
            }

        }
        if (count == 5) {
            return true;
        }
        for(int i = 1;i < 5; i++){
            if (myArray.contains(new Point(x,y-i))) {
                count++;
            }else {
                break;
            }
            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    //左斜向检查是否满足五子相连
    private boolean checkLeftDiagonal(int x ,int y ,List<Point> myArray){
        int count = 1;
        for(int i = 1;i < 5; i++){
            if (myArray.contains(new Point(x-i,y+i))) {
                count++;
            }else {
                break;
            }

        }
        if (count == 5) {
            return true;
        }
        for(int i = 1;i < 5; i++){
            if (myArray.contains(new Point(x+i,y-i))) {
                count++;
            }else {
                break;
            }
            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    //右斜向检查是否满足五子相连
    private boolean checkRightDiagonal(int x ,int y ,List<Point> myArray){
        int count = 1;
        for(int i = 1;i < 5; i++){            //切记，i = 1 开始，否则就会只检测到三个子相连就结束了
            if (myArray.contains(new Point(x-i,y-i))) {
                count++;
            }else {
                break;
            }
        }
        if (count == 5) {
            return true;
        }
        for(int i = 1;i < 5; i++){
            if (myArray.contains(new Point(x+i,y+i))) {
                count++;
            }else {
                break;
            }
            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    //重新开始游戏
    public void restartGame(){
        myWhiteArray.clear();
        myBlackArray.clear();
        isGemOver = false;
        isWhite = false;
        invalidate();
    }
}
