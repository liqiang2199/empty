package com.empty.cuplibrary.weight.tools


import android.text.Html

/**
 * Created by empty cup on 2018/5/29.
 *
 */

/**
 * 设置颜色
 */
fun String.textHtmlcolor(color:String):String{

    return Html.fromHtml("<font color=\"$color\">"+
            if (UtilsTools.StringNull(this)){
                this
            }else{
                ""
            }+"</font>").toString()
}
/**
 * 设置颜色 有前缀
 */
fun String.textHtmlcolor(title:String,color:String):String{

    return Html.fromHtml("$title:<font color=\"$color\">"+if (UtilsTools.StringNull(this)){
        this
    }else{
        ""
    }+"</font>").toString()
}

/**
 * 有设置颜色 和大小
 */
fun String.textHtmlSizeColor(size:Int,color: String):String{

    return Html.fromHtml("<font size=\"$size\" color=\"$color\">"+
            if (UtilsTools.StringNull(this)){
                this
            }else{
                ""
            }+"</font>").toString()
}
/**
 * 有设置颜色 和大小 有前缀
 */
fun String.textHtmlTitleSizeColor(title:String,size:Int,color: String):String{

    return Html.fromHtml("$title:<font size=\"$size\" color=\"$color\">"+
            if (UtilsTools.StringNull(this)){
                this
            }else{
                ""
            }+"</font>").toString()
}