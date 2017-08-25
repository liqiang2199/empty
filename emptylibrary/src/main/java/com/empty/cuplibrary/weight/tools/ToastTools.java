package com.empty.cuplibrary.weight.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by empty cup on 2017/7/28.
 */

public class ToastTools {

    public static void Toast_LongTip(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
    public static void Toast_ShortTip(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
