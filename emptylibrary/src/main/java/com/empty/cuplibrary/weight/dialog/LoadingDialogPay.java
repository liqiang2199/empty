package com.empty.cuplibrary.weight.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.empty.cuplibrary.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by admin on 2016/11/15.
 * 加载支付完成对话框（轮询是显示）
 */
public class LoadingDialogPay {

    private Dialog mydialog;
    private LinearLayout lier_doc;//加入小数点
    private static LoadingDialogPay dialogCommon;

    private ImageView[] dots;//初始化原点
    private int dotcCurrt=0;
    private boolean flagtran=false;//是否让圆点移动
    private ScheduledExecutorService scheduledExecutorService;

    //IntentMange管理
    private LoadingDialogPay(){
    }

    public static LoadingDialogPay getInstance(){
        if(dialogCommon==null){
            synchronized (LoadingDialogPay.class){
                if (dialogCommon == null){
                    dialogCommon = new LoadingDialogPay();
                }
            }
        }
        return dialogCommon;
    }


    /***
     * 创建加载对话框
     *
     * @param /title
     */
    public void CreateMyDialogPay(Context context) {
        mydialog = new Dialog(context, R.style.dialog);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.activity_show_load_dialog, null);
        lier_doc = (LinearLayout) dialogView.findViewById(R.id.lier_doc);
        initDots(context);
        flagtran = true;
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setContentView(dialogView);

        mydialog.show();
        setCurrentDot(dotcCurrt);
        startTask();

    }
    //更新界面显示
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (dotcCurrt>=dots.length){
                dotcCurrt = 0;
            }
            setCurrentDot(dotcCurrt);
        }
    };


    /**
     * 开启定时任务
     */
    private void startTask() {
        // TODO Auto-generated method stub
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 1,
                TimeUnit.SECONDS);
    }



    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (lier_doc) {
                if (flagtran){
                    dotcCurrt = dotcCurrt+1;
                    handler.obtainMessage().sendToTarget();
                }

            }
        }

    }

    /**
     * 停止定时任务
     */
    private void stopTask() {
        // TODO Auto-generated method stub
        flagtran = false;
        scheduledExecutorService.shutdownNow();
    }

    /***
     * 关闭加载对话框
     *
     */
    public void MyDialogCanlePay() {
        flagtran = false;
        stopTask();
        if (mydialog != null)
            mydialog.dismiss();
    }
    /**
     * 底部圆点初始化
     */
    private void initDots(Context context)
    {

        dots = new ImageView[3];

        for (int i = 0; i < 3; i++)
        {
            dots[i] = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 15;
            dots[i].setLayoutParams(params);
            dots[i].setBackgroundResource(R.drawable.page_imag_dialog);
            // 设置为灰色
            dots[i].setEnabled(true);
            lier_doc.addView(dots[i]);
        }
    }

    private void setCurrentDot(int position)
    {

        if (position < 0 || position > dots.length - 1)
        {
            return;
        }
        dots[position].setEnabled(false);
        for(int i=0;i<dots.length;i++){
            if(i!=position){
                dots[i].setEnabled(true);
            }
        }

    }
}
