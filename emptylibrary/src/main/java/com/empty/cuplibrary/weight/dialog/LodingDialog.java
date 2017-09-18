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
public class LodingDialog {

    private Dialog mydialog;
    private TextView loding_tip;//文本提示
    private static LodingDialog dialogCommon;
//    private ImageView dialog_pros;
    //IntentMange管理
    private LodingDialog(){

        dialogCommon = this;
    }

    public static LodingDialog getApplication(){
        return dialogCommon;
    }

    public static LodingDialog getIstance(){
        if(dialogCommon==null){
            synchronized (LodingDialog.class){
                if (dialogCommon == null){
                    dialogCommon = new LodingDialog();
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
