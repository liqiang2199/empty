package com.empty.cuplibrary.weight.tools;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by Administrator on 2017/7/7.
 */

public class UtilsTools {

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
        if (s == null || s.isEmpty() || s.equals("")||s.equals("null")) {
            return false;
        }
        return true;

    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        if (mobiles.length() > 0 && mobiles.length() <= 11) {
            String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
            return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
        } else {
            return false;
        }
    }

    /**
     * 判断是否是汉字
     * @param inputchar
     * @return
     */
    public static boolean Edit_Chinesecharacters(String inputchar){
        if (!StringNull(inputchar)){
            return true;
        }
        if (!inputchar.toString().matches("[\u4e00-\u9fa5]+")) {
            return false;
        }
        return true;
    }
}
