package com.empty.cuplibrary.weight.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.empty.cuplibrary.R;
import com.empty.cuplibrary.weight.tools.UtilsEmptyTools;


/**
 *
 *  2017/7/3.
 */

public class TipDialog {

    private LinearLayout lien_dialog_defferent;
    private View viewdifferent;
    private Dialog tipdialog;

    private static TipDialog defferent_dialog = new TipDialog();

    public static TipDialog getInsentce(){
        return defferent_dialog;
    }

    public void Defferent_CreateDialog(Context context){
        viewdifferent = LayoutInflater.from(context).inflate(R.layout.dialog_tip,null);
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        tipdialog = new Dialog(context,R.style.ActionSheetDialogStyle);
        tipdialog.getWindow().setContentView(viewdifferent);

        Window window = tipdialog.getWindow();
        WindowManager.LayoutParams  params = window.getAttributes();
        /**
         Dialog的width和height默认值为WRAP_CONTENT，正是因为如此，当屏幕中有足够的空间时，Dialog是不会被压缩的
         但是设置width和height为MATCH_PARENT的代价是无法设置gravity的值，这就无法调整Dialog中内容的位置，
         Dialog的内容会显示在屏幕左上角位置不过可以通过Padding来调节Dialog内容的位置。
         **/
        params.width = width- UtilsEmptyTools.dip2px(context,80);
        params.height = height/2;
        window.setAttributes(params);
        tipdialog.show();

    }
    public void TipCancel(){
        if (tipdialog != null){
            tipdialog.cancel();
        }
    }

}
