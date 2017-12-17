package com.empty.cuplibrary.weight.tools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by empty cup on 2017/7/28.
 */

public class ToastLogTools {

    public static boolean isLog = true;
    public static String logTag = "kxdebug";
    public static Toast mToast;
    public static void Toast_LongTip(Context context,String msg){

        if(mToast==null){
            mToast=Toast.makeText(context,msg,Toast.LENGTH_LONG);
        }else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }
    public static void Toast_ShortTip(Context context,String msg){
        if(mToast==null){
            mToast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    public static void LogTip_V(String msg){
        if (isLog){
            Log.v(logTag,msg);
        }
    }
}
