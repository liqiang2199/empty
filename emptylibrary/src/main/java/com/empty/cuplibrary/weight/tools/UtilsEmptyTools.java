package com.empty.cuplibrary.weight.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;

/**
 * Created by Administrator on 2017/7/7.
 */

public class UtilsEmptyTools {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //字符串为空判断
    public static boolean StringNull(String s) {
        return s == null || s.isEmpty() || s.equals("null");

    }
    /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
    /**
     * 验证手机格式
     *
     */
    public static boolean isMobileNO(String mobiles) {

        if (mobiles.length() > 0 && mobiles.length() <= 11) {
            //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
            String telRegex = "[1][34578]\\d{9}";
            return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
        } else {
            return false;
        }
    }
    public static boolean isMoblieNo(String mobiles,String regex) {
        if (StringNull(regex)) {
            return isMobileNO(mobiles);
        }
        return mobiles.length() > 0 && mobiles.length() <= 11 && !TextUtils.isEmpty(mobiles) && mobiles.matches(regex);
    }

    /**
     * 判断是否是汉字
     * @param inputChar
     * @return
     */
    public static boolean editChineseCharacters(String inputChar) {
        return !StringNull(inputChar) && inputChar.matches("[\u4e00-\u9fa5]+");
    }

    /**
     * 判断是否是数字
     * @param inputNum
     * @return
     */
    public static boolean editIsAllNum(String inputNum){
        return !StringNull(inputNum) && inputNum.matches("[0-9]+");
    }

    /**
     * 判断网络连接状况
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            assert mConnectivityManager != null;
            @SuppressLint("MissingPermission")
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}
