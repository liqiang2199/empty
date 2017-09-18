package com.empty.cup.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.empty.cup.R

/**
 * Created by empty cup on 2017/8/30.
 */
class ViewpagerAdapterCreate(context: Context) : PagerAdapter() {

    var context: Context? = context
//    var listview :ArrayList<View>? = listview

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 3
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
//        super.destroyItem(container, position, `object`)
//        container!!.removeViewAt(position)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
//        container!!.addView(listview!![position])
        var imageview :ImageView = ImageView(context)
        imageview.setImageResource(R.mipmap.guang_gao2)
        container!!.addView(imageview)
        return imageview
    }


}