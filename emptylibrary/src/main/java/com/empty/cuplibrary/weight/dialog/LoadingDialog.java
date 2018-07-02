package com.empty.cuplibrary.weight.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.empty.cuplibrary.R;


/**
 * Created by admin on 2016/11/15.
 * 加载对话框
 */
public class LoadingDialog {

    private Dialog mydialog;
    private TextView loding_tip;//文本提示
    private static LoadingDialog dialogCommon;
//    private ImageView dialog_pros;
    //IntentMange管理
    private LoadingDialog(){
    }
    public static LoadingDialog getInstance(){
        if(dialogCommon==null){
            synchronized (LoadingDialog.class){
                if (dialogCommon == null){
                    dialogCommon = new LoadingDialog();
                }
            }
        }
        return dialogCommon;
    }


    /***
     * 创建加载对话框
     *
     */
    public void CreateMyDialog(Context context) {
        mydialog = new Dialog(context, R.style.NobackDialog);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_loading, null);
        loding_tip = (TextView) dialogView.findViewById(R.id.loding_tip);
        ImageView dialog_pros = (ImageView) dialogView.findViewById(R.id.dialog_pros);

        dialog_pros.setImageResource(R.drawable.animal_dialog_loding);
        AnimationDrawable drawable = (AnimationDrawable) dialog_pros.getDrawable();
        drawable.start();
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setContentView(dialogView);
        mydialog.show();
    }
    public void setTextTip(String text){
        //提示框里面的文字提示
        if (loding_tip != null){
            loding_tip.setText(text);
        }
    }
    /***
     * 关闭加载对话框
     *
     */
    public void DialogCanle() {
        if (mydialog != null){
            mydialog.dismiss();
        }
    }

}
