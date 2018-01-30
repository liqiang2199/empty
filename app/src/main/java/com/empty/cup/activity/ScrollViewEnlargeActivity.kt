package com.empty.cup.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import com.empty.cup.R

@SuppressLint("Registered")
/**
 * Created by empty cup on 2018/1/30.
 *  防小米的 子控件 高度自动增加
 */
class ScrollViewEnlargeActivity :Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrollview)
    }
}