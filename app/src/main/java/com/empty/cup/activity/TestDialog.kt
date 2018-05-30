package com.empty.cup.activity

import android.app.Activity
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.empty.cup.R
import com.empty.cuplibrary.weight.dialog.VersionDialog
import com.empty.cuplibrary.weight.tools.TextTool
import com.empty.cuplibrary.weight.tools.kot
import kotlinx.android.synthetic.main.activity_view_page_jd.view.*

/**
 * Created by empty cup on 2018/5/22.
 */
class TestDialog :Activity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog)
        findViewById(R.id.textView).text




        findViewById(R.id.textView).setOnClickListener {

            val veersion = VersionDialog.Builder(this)

            veersion.setTitle("1.deeeeeeeeeee\r\n2.33333333333333333333333333333")
            veersion.setPositiveButton("取消",
                    DialogInterface.OnClickListener { dilog, arg1 ->
                        dilog.dismiss()
                        //取消

                    })

            veersion.setNegativeButton("确定",
                    DialogInterface.OnClickListener { dilog, arg1 ->
                        // TODO Auto-generated method stub
                        dilog.dismiss()
                        //确定
                    })
            veersion.create("1.deeeeeeeeeee\r\n2.33333333333333333333333333333").show()
        }
    }
}