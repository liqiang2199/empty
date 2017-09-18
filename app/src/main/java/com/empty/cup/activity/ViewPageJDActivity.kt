package com.empty.cup.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.view.ViewPager
import android.widget.ImageView
import com.empty.cup.R
import com.empty.cup.activity.ViewPageJDActivity.OpenApk
import com.empty.cup.adapter.ViewpagerAdapterCreate
import com.empty.cup.wight.Rotate3dAnimation
import com.empty.cuplibrary.weight.tools.annotation.ContentView
import com.empty.cuplibrary.weight.tools.annotation.ViewInjectUtils
import kotlinx.android.synthetic.main.activity_view_page_jd.*
import com.empty.cup.down.DownloadUtils
import java.io.File


@ContentView(value = R.layout.activity_view_page_jd)
class ViewPageJDActivity : AppCompatActivity() {


    var objectAnimatin :ObjectAnimator? = null
    var objectAnimatin1 :ObjectAnimator? = null
    var animationset:AnimatorSet? = null
    var floatAni:Float = 0f
    var rotateAni: Rotate3dAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_view_page_jd)
        ViewInjectUtils.inject(this)
        Init()
    }

    fun Init(){



        var  adapter: ViewpagerAdapterCreate = ViewpagerAdapterCreate(this)
        image_viewpage.adapter = adapter
//        Animation_Rotation()
//        animationset = AnimatorSet()

        image_viewpage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                image!!.rotationY = 180f*positionOffset
////                text.rotationY = 180f*positionOffset
//                text.alpha = positionOffset
//                if (positionOffset == 0.0f){
//                    text.alpha = 1f
//                }
                image!!.animate().rotationY(180f*positionOffset)

//                floatAni = positionOffset
//                objectAnimatin = ObjectAnimator.ofFloat(image,"rotationY",180f*floatAni,180f)
//                objectAnimatin1 = ObjectAnimator.ofFloat(text,"rotationY",180f*floatAni,-180f)
//                animationset!!.duration = 500
//                objectAnimatin?.setFloatValues(180f*positionOffset,180f)
//                animationset!!.playTogether(objectAnimatin)
//                animationset!!.start()
//                rotateAni = Rotate3dAnimation(Rotate3dAnimation.ROLL_BY_X,360f*floatAni,360f)
//                rotateAni!!.duration = 500
//                rotateAni!!.fillAfter = true
//                image!!.startAnimation(rotateAni)

            }

            override fun onPageSelected(position: Int) {
                //判断滑动后选择的页面设置相应的RadioButton被选中



            }

        })
    }

    private fun Animation_Rotation(){
        objectAnimatin = ObjectAnimator.ofFloat(image,"rotationY",180f)
        objectAnimatin!!.duration = 500


    }


    interface OpenApk{
        fun openAPK(){}
    }

}
